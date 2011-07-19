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
package com.linkcorp.mvp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.linkcorp.mvp.shared.ContactDTO;
import com.linkcorp.mvp.shared.ContactDetailsDTO;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 16:55:19
 */
public interface ContactServiceAsync {

	void addContact(ContactDTO contact, AsyncCallback<ContactDTO> callback);

	void deleteContact(String id, AsyncCallback<ContactDTO> callback);

	void updateContact(ContactDTO contact, AsyncCallback<ContactDTO> callback);

	void getContacts(AsyncCallback<List<ContactDTO>> callback);

	void getContactDetails(AsyncCallback<List<ContactDetailsDTO>> callback);

	void deleteContacts(List<String> ids,
			AsyncCallback<List<ContactDetailsDTO>> callback);

}
