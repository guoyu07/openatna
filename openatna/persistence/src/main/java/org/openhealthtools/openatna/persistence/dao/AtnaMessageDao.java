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

package org.openhealthtools.openatna.persistence.dao;

import org.openhealthtools.openatna.persistence.model.AtnaMessageEntity;
import org.openhealthtools.openatna.persistence.model.codes.*;
import org.openhealthtools.openatna.persistence.dao.Dao;
import org.openhealthtools.openatna.persistence.AtnaPersistenceException;

import java.util.List;

/**
 * Class Description Here...
 *
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Sep 4, 2009: 5:39:35 PM
 * @date $Date:$ modified by $Author:$
 */
public interface AtnaMessageDao extends Dao {

    public AtnaMessageEntity getById(Long id) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getAll() throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByEventId(EventIdCodeEntity idEntity) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByEventType(EventTypeCodeEntity typeEntity) throws AtnaPersistenceException;



    public List<? extends AtnaMessageEntity> getByParticipantUserId(String id) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByParticipantAltUserId(String id) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByParticipantCode(ParticipantCodeEntity codeEntity) throws AtnaPersistenceException;




    public List<? extends AtnaMessageEntity> getByAuditSourceId(String id) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByAuditSourceEnterpriseId(String id) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByAuditSourceCode(SourceCodeEntity codeEntity) throws AtnaPersistenceException;



    public List<? extends AtnaMessageEntity> getByObjectId(String id) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByObjectIdTypeCode(ObjectIdTypeCodeEntity codeEntity) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByObjectTypeCode(Short code) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByObjectTypeCodeRole(Short code) throws AtnaPersistenceException;

    public List<? extends AtnaMessageEntity> getByObjectSensitivity(String sensitivity) throws AtnaPersistenceException;



    
    public void save(AtnaMessageEntity messageEntity) throws AtnaPersistenceException;

    public void delete(AtnaMessageEntity messageEntity) throws AtnaPersistenceException;

    public void normalize(AtnaMessageEntity messageEntity) throws AtnaPersistenceException;

}
