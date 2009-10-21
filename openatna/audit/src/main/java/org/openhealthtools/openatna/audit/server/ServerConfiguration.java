/*
 * Copyright (c) 2009 University of Cardiff and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Cardiff University - intial API and implementation
 */

package org.openhealthtools.openatna.audit.server;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openhealthtools.openatna.persistence.dao.PersistencePolicies;
import org.openhealthtools.openatna.persistence.util.PersistencePoliciesIO;
import org.openhealthtools.openatna.syslog.LogMessage;

/**
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Oct 21, 2009: 10:24:00 AM
 * @date $Date:$ modified by $Author:$
 */

public class ServerConfiguration {

    static Log log = LogFactory.getLog("org.openhealthtools.openatna.audit.config.ServerConfiguration");

    private static ServerConfiguration config = new ServerConfiguration();

    private Set<Object> actors = new HashSet<Object>();

    private ServerConfiguration() {

    }

    public static ServerConfiguration getInstance() {
        return config;
    }

    @SuppressWarnings("unchecked")
    public <T> T getActor(Class<? extends T> cls) {
        for (Object o : actors) {
            if (cls.isAssignableFrom(o.getClass())) {
                return (T) o;
            }
        }

        return null;
    }


    public boolean loadActors(File configFile) {
        boolean okay = true;
        Document configuration = null;
        try {
            configuration = createDocument(configFile);
        } catch (Exception e) {
            throw new RuntimeException("Error loading config file:" +
                    configFile.getAbsolutePath(), e);
        }
        // Get the list of XML elements in the configuration file
        NodeList configurationElements = configuration.getDocumentElement().getChildNodes();
        // Load all the connection definitions first
        for (int elementIndex = 0; elementIndex < configurationElements.getLength(); elementIndex++) {
            Node element = configurationElements.item(elementIndex);
            if (element instanceof Element) {
                // See what type of element it is
                String name = element.getNodeName();
                if (name.equalsIgnoreCase("CONNECTIONFILE")) {
                    // An included connection file, load it
                    if (!processConnectionFile((Element) element, configFile)) okay = false;
                } else if (name.equalsIgnoreCase("SECURECONNECTION") || name.equalsIgnoreCase("STANDARDCONNECTION")) {
                    // An included connection, load it
                    if (!ConnectionFactory.loadConnectionDescriptionsFromXmlNode(element, configFile)) {
                        throw new RuntimeException("Error loading configuration file \"" + configFile.getAbsolutePath() +
                                "\" in configFile:" + configFile.getAbsolutePath());
                    }
                }
            }
        }
        // If all the connection files loaded okay, define the various actors
        if (okay) {
            for (int elementIndex = 0; elementIndex < configurationElements.getLength(); elementIndex++) {
                Node element = configurationElements.item(elementIndex);
                if (element instanceof Element) {
                    // See what type of element it is
                    String name = element.getNodeName();
                    if (name.equalsIgnoreCase("ACTORFILE")) {
                        if (!processActorFile((Element) element, configFile)) okay = false;
                    } else if (name.equalsIgnoreCase("ACTOR")) {
                        // An IHE actor definition
                        if (!processActorDefinition((Element) element)) okay = false;
                    }
                }
            }
        }
        return okay;
    }

    private boolean processActorDefinition(Element parent) {
        boolean okay;
        String type = parent.getAttribute("type");
        String name = parent.getAttribute("name");
        if (name == null || type == null) {
            throw new RuntimeException("No name or type for actor defined!");
        }
        if ("SECURENODE".equalsIgnoreCase(type) && "ARR".equalsIgnoreCase(name)) {
            okay = processArr(parent);
        } else {
            log.warn("Unknown actor type or name. Expecting name=arr and type=SecureNode but got name=" +
                    name + " type=" + type);
            okay = false;
        }
        return okay;
    }

    private boolean processArr(Element parent) {
        IConnectionDescription tcp = null;
        IConnectionDescription udp = null;
        PersistencePolicies pp = null;
        Class<? extends LogMessage> logClass = null;
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            if (n instanceof Element) {
                Element el = (Element) n;
                if (el.getTagName().equalsIgnoreCase("TCP")) {
                    String conn = el.getAttribute("connection");
                    if (conn == null) {
                        throw new RuntimeException("No connection defined for Audit Record Repository");
                    }
                    tcp = ConnectionFactory.getConnectionDescription(conn);
                    if (tcp == null) {
                        throw new RuntimeException("No connection defined for Audit Record Repository");
                    }

                } else if (el.getTagName().equalsIgnoreCase("UDP")) {
                    String conn = el.getAttribute("connection");
                    if (conn == null) {
                        throw new RuntimeException("No connection defined for Audit Record Repository");
                    }
                    udp = ConnectionFactory.getConnectionDescription(conn);
                    if (udp == null) {
                        throw new RuntimeException("No connection defined for Audit Record Repository");
                    }

                } else if (el.getTagName().equalsIgnoreCase("PERSISTENCEPOLICIES")) {
                    try {
                        pp = PersistencePoliciesIO.read(el);
                    } catch (IOException e) {
                        log.warn("exception thrown trying to read persistence policies", e);
                    }
                } else if (el.getTagName().equalsIgnoreCase("LOGMESSAGE")) {
                    String logType = el.getTextContent().trim();
                    if (logType == null || logType.length() == 0) {
                        throw new RuntimeException("No log message implementation specified. This is required.");
                    }
                    try {
                        logClass = (Class<? extends LogMessage>) getClass().getClassLoader().loadClass(logType);
                    } catch (Exception e) {
                        throw new RuntimeException("Unable to load log message implementation.", e);
                    }

                }
            }
        }
        if (tcp == null && udp == null) {
            throw new RuntimeException("No connections defined for Audit Record Repository. It cannot serve");
        }
        if (logClass == null) {
            throw new RuntimeException("No Log Message class specified. This is required.");
        }
        AtnaServer server = new AtnaServer(tcp, udp);
        server.setLogMessageClass(logClass);
        if (pp != null) {
            server.setPersistencePolicies(pp);
        }
        actors.add(server);
        return true;
    }

    private boolean processConnectionFile(Element element, File configFile) {
        boolean okay = false;
        // Get out the file name
        String filename = element.getAttribute("file");
        if (filename == null) filename = element.getAttribute("name");
        if (filename == null) filename = element.getTextContent().trim();
        if (filename != null) {
            // Got the connection file name, load it
            File includeFile = new File(configFile.getParentFile(), filename);
            if (ConnectionFactory.loadConnectionDescriptionsFromFile(includeFile)) {
                okay = true;
            } else {
                throw new RuntimeException("Error loading connection file \"" +
                        filename + "\" from config file:" + configFile.getAbsolutePath());
            }
        } else {
            // No connection file name given
            throw new RuntimeException("No connection file specified in config file:" + configFile.getAbsolutePath());
        }
        // Done
        return okay;
    }

    private boolean processActorFile(Element element, File configFile) {
        boolean okay = false;
        // Get out the file name
        String filename = element.getAttribute("file");
        if (filename == null) filename = element.getAttribute("name");
        if (filename == null) filename = element.getTextContent().trim();
        if (filename != null) {
            // Got the actor file name, load it
            File includeFile = new File(configFile.getParentFile(), filename);
            if (loadActors(includeFile)) {
                okay = true;
            } else {
                throw new RuntimeException("Error loading actor file \"" +
                        filename + "\" in config file:" + configFile.getAbsolutePath());
            }
        } else {
            // No connection file name given
            throw new RuntimeException("No connection file specified in config file:" + configFile.getAbsolutePath());
        }
        // Done
        return okay;
    }

    private Document createDocument(File configFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        return factory.newDocumentBuilder().parse(configFile);
    }


}
