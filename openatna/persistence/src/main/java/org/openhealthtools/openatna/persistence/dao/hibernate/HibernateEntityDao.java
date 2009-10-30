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

package org.openhealthtools.openatna.persistence.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.openhealthtools.openatna.persistence.AtnaPersistenceException;
import org.openhealthtools.openatna.persistence.dao.EntityDao;
import org.openhealthtools.openatna.persistence.model.PersistentEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Sep 5, 2009: 1:26:08 PM
 * @date $Date:$ modified by $Author:$
 */

@Transactional(rollbackFor = AtnaPersistenceException.class)
public class HibernateEntityDao extends AbstractHibernateDao<PersistentEntity> implements EntityDao {

    public HibernateEntityDao(SessionFactory sessionFactory) {
        super(PersistentEntity.class, sessionFactory);
    }

    public List<? extends PersistentEntity> query(String query) {
        return list(createQuery(query));
    }

    public String[] getSupportedQueryDialects() {
        return new String[]{"HQL"};
    }
}