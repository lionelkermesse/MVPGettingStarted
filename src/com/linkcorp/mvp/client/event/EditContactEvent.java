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
package com.linkcorp.mvp.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.linkcorp.mvp.shared.ContactDTO;
/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 19:08:58
 */
public class EditContactEvent extends GwtEvent<EditContactEventHandler> {
	public final static Type<EditContactEventHandler> TYPE = new Type<EditContactEventHandler>();
	private ContactDTO contact;

	public EditContactEvent(ContactDTO contact) {
		this.contact = contact;
	}

	@Override
	public Type<EditContactEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditContactEventHandler handler) {
		handler.onEditContact(this);
	}

	/**
	 * @return the id
	 */
	public ContactDTO getContact() {
		return contact;
	}

	/**
	 * @param id the id to set
	 */
	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

}
