/*
 * Copyright (c) 2010 University of Cardiff and others.
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

package org.openhealthtools.openatna.audit.persistence;


/**
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Oct 6, 2009: 6:03:28 PM
 * @date $Date:$ modified by $Author:$
 */

public class PersistencePolicies {

    private boolean allowNewCodes;
    private boolean allowNewNetworkAccessPoints;
    private boolean allowNewParticipants;
    private boolean allowNewSources;
    private boolean allowNewObjects;
    private boolean allowUnknownDetailTypes;
    private boolean allowModifyMessages;
    private boolean errorOnDuplicateInsert;
    private boolean allowModifyParticipants;
    private boolean allowModifySources;
    private boolean allowModifyObjects;

    public boolean isAllowNewCodes() {
        return allowNewCodes;
    }

    public void setAllowNewCodes(boolean allowNewCodes) {
        this.allowNewCodes = allowNewCodes;
    }

    public boolean isAllowNewNetworkAccessPoints() {
        return allowNewNetworkAccessPoints;
    }

    public void setAllowNewNetworkAccessPoints(boolean allowNewNetworkAccessPoints) {
        this.allowNewNetworkAccessPoints = allowNewNetworkAccessPoints;
    }

    public boolean isAllowNewParticipants() {
        return allowNewParticipants;
    }

    public void setAllowNewParticipants(boolean allowNewParticipants) {
        this.allowNewParticipants = allowNewParticipants;
    }

    public boolean isAllowNewSources() {
        return allowNewSources;
    }

    public void setAllowNewSources(boolean allowNewSources) {
        this.allowNewSources = allowNewSources;
    }

    public boolean isAllowNewObjects() {
        return allowNewObjects;
    }

    public void setAllowNewObjects(boolean allowNewObjects) {
        this.allowNewObjects = allowNewObjects;
    }

    public boolean isAllowUnknownDetailTypes() {
        return allowUnknownDetailTypes;
    }

    public void setAllowUnknownDetailTypes(boolean allowUnknownDetailTypes) {
        this.allowUnknownDetailTypes = allowUnknownDetailTypes;
    }

    public boolean isAllowModifyMessages() {
        return allowModifyMessages;
    }

    public void setAllowModifyMessages(boolean allowModifyMessages) {
        this.allowModifyMessages = allowModifyMessages;
    }

    public boolean isErrorOnDuplicateInsert() {
        return errorOnDuplicateInsert;
    }

    public void setErrorOnDuplicateInsert(boolean errorOnDuplicateInsert) {
        this.errorOnDuplicateInsert = errorOnDuplicateInsert;
    }

    public boolean isAllowModifyParticipants() {
        return allowModifyParticipants;
    }

    public void setAllowModifyParticipants(boolean allowModifyParticipants) {
        this.allowModifyParticipants = allowModifyParticipants;
    }

    public boolean isAllowModifySources() {
        return allowModifySources;
    }

    public void setAllowModifySources(boolean allowModifySources) {
        this.allowModifySources = allowModifySources;
    }

    public boolean isAllowModifyObjects() {
        return allowModifyObjects;
    }

    public void setAllowModifyObjects(boolean allowModifyObjects) {
        this.allowModifyObjects = allowModifyObjects;
    }

}
