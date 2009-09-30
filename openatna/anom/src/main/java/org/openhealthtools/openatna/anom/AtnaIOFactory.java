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

package org.openhealthtools.openatna.anom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Sep 30, 2009: 1:51:10 PM
 * @date $Date:$ modified by $Author:$
 */

public abstract class AtnaIOFactory {


    public abstract AtnaMessage read(InputStream in) throws AtnaException, IOException;

    public abstract void write(AtnaMessage message, OutputStream out) throws AtnaException, IOException;


    // this should move to a higher level.
    protected void validate(AtnaMessage message) throws AtnaException {

        AtnaCode evt = message.getEventCode();
        if (evt.getCode() == null) {
            throw new AtnaException("invalid event code", AtnaException.AtnaError.NO_EVENT_CODE);
        }
        if (message.getEventOutcome() == null) {
            throw new AtnaException("invalid event outcome", AtnaException.AtnaError.NO_EVENT_OUTCOME);
        }
        if (message.getEventDateTime() == null) {
            throw new AtnaException("invalid time stamp", AtnaException.AtnaError.INVALID_EVENT_TIMESTAMP);
        }
        List<AtnaCode> codes = message.getEventTypeCodes();
        for (AtnaCode code : codes) {
            if (code.getCode() == null) {
                throw new AtnaException("no active participant user id defined", AtnaException.AtnaError.INVALID_CODE);
            }
        }
        List<AtnaSource> sources = message.getSources();
        if (sources.size() == 0) {
            throw new AtnaException("no audit source defined", AtnaException.AtnaError.NO_AUDIT_SOURCE);
        }
        for (AtnaSource source : sources) {
            if (source.getSourceId() == null) {
                throw new AtnaException("no audit source id defined", AtnaException.AtnaError.NO_AUDIT_SOURCE_ID);
            }
            codes = source.getSourceTypeCodes();
            for (AtnaCode code : codes) {
                if (code.getCode() == null) {
                    throw new AtnaException("no active participant user id defined", AtnaException.AtnaError.INVALID_CODE);
                }
            }
        }
        List<AtnaMessageParticipant> participants = message.getParticipants();
        if (participants.size() == 0) {
            throw new AtnaException("no participants defined", AtnaException.AtnaError.NO_ACTIVE_PARTICIPANT);
        }
        for (AtnaMessageParticipant participant : participants) {
            if (participant.getParticipant().getUserId() == null) {
                throw new AtnaException("no active participant user id defined", AtnaException.AtnaError.NO_ACTIVE_PARTICIPANT_ID);
            }
            codes = participant.getParticipant().getRoleIDCodes();
            for (AtnaCode code : codes) {
                if (code.getCode() == null) {
                    throw new AtnaException("no active participant user id defined", AtnaException.AtnaError.INVALID_CODE);
                }
            }
        }
        List<AtnaMessageObject> objects = message.getObjects();
        for (AtnaMessageObject object : objects) {
            if (object.getObject().getObjectId() == null) {
                throw new AtnaException("no participant object id defined", AtnaException.AtnaError.NO_PARTICIPANT_OBJECT_ID);
            }
            if (object.getObject().getObjectIdTypeCode() == null || object.getObject().getObjectIdTypeCode().getCode() == null) {
                throw new AtnaException("invalid object id type code", AtnaException.AtnaError.NO_PARTICIPANT_OBJECT_ID_TYPE_CODE);
            }
        }
    }
}