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

import org.openhealthtools.openatna.persistence.model.SourceEntity;
import org.openhealthtools.openatna.persistence.model.AtnaSourceEntity;
import org.openhealthtools.openatna.persistence.model.codes.SourceCodeEntity;
import org.openhealthtools.openatna.persistence.dao.Dao;
import org.openhealthtools.openatna.persistence.AtnaPersistenceException;

import java.util.List;

/**
 * Class Description Here...
 *
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Sep 4, 2009: 6:19:55 PM
 * @date $Date:$ modified by $Author:$
 */
public interface AtnaSourceDao extends Dao {

    public AtnaSourceEntity getById(Long id) throws AtnaPersistenceException;

    public AtnaSourceEntity getBySourceId(String sourceId) throws AtnaPersistenceException;

    public AtnaSourceEntity getByEnterpriseSiteId(String enterpriseSiteId) throws AtnaPersistenceException;

    public List<? extends AtnaSourceEntity> getByCode(SourceCodeEntity codeEntity) throws AtnaPersistenceException;

    public List<? extends AtnaSourceEntity> getAll() throws AtnaPersistenceException;

    public void save(AtnaSourceEntity as) throws AtnaPersistenceException;

    public void delete(AtnaSourceEntity as) throws AtnaPersistenceException;

    public void normalize(AtnaSourceEntity as) throws AtnaPersistenceException;

}