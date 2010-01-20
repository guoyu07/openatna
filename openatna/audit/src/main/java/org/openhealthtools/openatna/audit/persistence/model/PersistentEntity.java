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

package org.openhealthtools.openatna.audit.persistence.model;

import java.io.Serializable;

/**
 * force entities to implement these methods and be serializable
 *
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Sep 4, 2009: 9:18:46 AM
 * @date $Date:$ modified by $Author:$
 */
public abstract class PersistentEntity implements Serializable {

    public abstract int hashCode();

    public abstract boolean equals(Object other);

    public abstract String toString();


}
