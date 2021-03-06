/**
 *  Copyright (c) 2009-2011 University of Cardiff and others
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 *
 *  Contributors:
 *    University of Cardiff - initial API and implementation
 *    -
 */

package org.openhealthtools.openatna.audit.log;

/**
 * Addded to a Logger, these get called when the log() method of the logger gets called.
 *
 * @author Andrew Harrison
 * @version $Revision:$
 * @created Oct 1, 2009: 9:30:31 AM
 * @date $Date:$ modified by $Author:$
 */
public interface ErrorHandler<T extends Throwable> {

    public void handle(T t);

}
