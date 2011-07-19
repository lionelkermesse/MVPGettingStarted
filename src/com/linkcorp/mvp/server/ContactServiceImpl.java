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
package com.linkcorp.mvp.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.linkcorp.mvp.client.service.ContactService;
import com.linkcorp.mvp.shared.ContactDTO;
import com.linkcorp.mvp.shared.ContactDetailsDTO;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 16:56:55
 */
@SuppressWarnings("serial")
public class ContactServiceImpl extends RemoteServiceServlet implements
		ContactService {

	public ContactServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public ContactDTO addContact(ContactDTO contact) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContactDTO deleteContact(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContactDTO updateContact(ContactDTO contact) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.linkcorp.mvp.client.service.ContactService#getContacts()
	 */
	@Override
	public List<ContactDTO> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.linkcorp.mvp.client.service.ContactService#getContactDetails()
	 */
	@Override
	public List<ContactDetailsDTO> getContactDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.linkcorp.mvp.client.service.ContactService#deleteContacts(java.util.List)
	 */
	@Override
	public List<ContactDetailsDTO> deleteContacts(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
