/** 
 *   MVPGettingStarted Application.
 *
 *   Copyright 2011 Harmonic-Pharma
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   See http://www.harmonic-pharma.com/ for more information 
 *   about this app.
 */
package com.linkcorp.mvp.server.model;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 16:44:11
 */

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ContactDetails {
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;
	
	@Persistent
	private String displayName;
	
	public ContactDetails() {}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

}
