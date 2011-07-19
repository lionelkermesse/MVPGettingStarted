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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.linkcorp.mvp.client.service.ContactService;
import com.linkcorp.mvp.shared.ContactDTO;
import com.linkcorp.mvp.shared.ContactDetailsDTO;

/**
 * @author Nelson Lionel KEMINSE
 * 19 juil. 2011 14:56:22
 */
@SuppressWarnings("serial")
public class ContactServiceImpl2 extends RemoteServiceServlet implements
		ContactService {

	private static final String[] contactsFirstNameData = new String[] {
	      "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
	      "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
	      "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush", 
	      "Abigail", "Chad", "Terry", "Bell"};
	  
	  private final String[] contactsLastNameData = new String[] {
	      "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
	      "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
	      "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard", 
	      "Louis", "Andrews", "English", "Snedden"};
	  
	  private final String[] contactsEmailData = new String[] {
	      "mark@example.com", "hollie@example.com", "boticario@example.com",
	      "emerson@example.com", "healy@example.com", "brigitte@example.com",
	      "elba@example.com", "claudio@example.com", "dena@example.com",
	      "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
	      "qetlyxxogg@example.com", "antenas_sul@example.com",
	      "cblake@example.com", "gailh@example.com", "orville@example.com",
	      "post_master@example.com", "rchilders@example.com", "buster@example.com",
	      "user31065@example.com", "ftsgeolbx@example.com"};
	      
	  private final HashMap<String, ContactDTO> contacts = new HashMap<String, ContactDTO>();

	  public ContactServiceImpl2() {
	    initContacts();
	  }
	  
	  private void initContacts() {
	    // TODO: Create a real UID for each contact
	    //
	    for (int i = 0; i < contactsFirstNameData.length && i < contactsLastNameData.length && i < contactsEmailData.length; ++i) {
	      ContactDTO contact = new ContactDTO(String.valueOf(i), contactsFirstNameData[i], contactsLastNameData[i], contactsEmailData[i]);
	      contacts.put(contact.getId(), contact); 
	    }
	  }
	  
	  public ContactDTO addContact(ContactDTO contact) {
	    contact.setId(String.valueOf(contacts.size()));
	    contacts.put(contact.getId(), contact); 
	    return contact;
	  }

	  public ContactDTO updateContact(ContactDTO contact) {
	    contacts.remove(contact.getId());
	    contacts.put(contact.getId(), contact); 
	    return contact;
	  }

	  public ContactDTO deleteContact(String id) {
		  ContactDTO contact = contacts.get(id);
	    contacts.remove(id);
	    return contact;
	  }
	  
	  public List<ContactDetailsDTO> deleteContacts(List<String> ids){

	    for (int i = 0; i < ids.size(); ++i) {
	      deleteContact(ids.get(i));
	    }
	    
	    return getContactDetails();
	  }
	  
	  public ArrayList<ContactDetailsDTO> getContactDetails() {
	    ArrayList<ContactDetailsDTO> contactDetails = new ArrayList<ContactDetailsDTO>();
	    
	    Iterator<String> it = contacts.keySet().iterator();
	    while(it.hasNext()) { 
	      ContactDTO contact = contacts.get(it.next());          
	      contactDetails.add(contact.getLightWeightContact());
	    }
	    
	    return contactDetails;
	  }

	  public ContactDTO getContact(String id) {
	    return contacts.get(id);
	  }

	  public List<ContactDTO> getContacts() {
		  List<ContactDTO> acontacts = new ArrayList<ContactDTO>();
		    
		    Iterator<String> it = contacts.keySet().iterator();
		    while(it.hasNext()) { 
		      acontacts.add(contacts.get(it.next()));
		    }
		    
		    return acontacts;
	}
}
