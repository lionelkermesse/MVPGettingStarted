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
package com.linkcorp.mvp.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.linkcorp.mvp.client.event.AddContactEvent;
import com.linkcorp.mvp.client.event.EditContactEvent;
import com.linkcorp.mvp.client.service.ContactServiceAsync;
import com.linkcorp.mvp.client.view.EditContactView;
import com.linkcorp.mvp.shared.ContactDTO;
import com.linkcorp.mvp.shared.ContactDetailsDTO;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 17:32:43
 */
public class ContactsPresenter implements Presenter {
	public interface Display extends HasValue<List<String>>{
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getEditButton();
		HasClickHandlers getContactTable();
		
		void setData(List<String> data);
		int getClickRow(ClickEvent event);
		List<Integer> getSelectedRows();
		Widget asWidget();
	}
	
	private final ContactServiceAsync rpcService;
	private final Display view;
	private final HandlerManager eventBus;
	private List<ContactDTO> contacts;
	private List<ContactDetailsDTO> contactsDetails;
	
	public ContactsPresenter(ContactServiceAsync rpcService, HandlerManager eventBus, Display view){
		this.rpcService = rpcService;
		this.view = view;
		this.eventBus = eventBus;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(this.view.asWidget());
		fetchContactsDetails();
	}
	
	@Override
	public void bind() {
		this.view.getEditButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EditContactView.get().getDialogEditBox().setText("Edit contact");
				EditContactView.get().getDialogEditBox().show();
			}
		});
		
		this.view.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddContactEvent());
			}
		});
		
		this.view.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedContacts();
			}
		});
		
		this.view.getContactTable().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = view.getClickRow(event);
				if(selectedRow >= 0){
					String id = contacts.get(selectedRow).getId();
					eventBus.fireEvent(new EditContactEvent(getContact(id)));
				}
			}
		});
	}
	
	private void fetchContactsDetails(){
		rpcService.getContactDetails(new AsyncCallback<List<ContactDetailsDTO>>() {
			public void onFailure(Throwable caught) {}

			public void onSuccess(List<ContactDetailsDTO> result) {
				contactsDetails = result;
				List<String> data = new ArrayList<String>();
				for (ContactDetailsDTO contact : result) {
					data.add(contact.getDisplayName());
				}
				view.setData(data);
			}
		});
	}

	
	public void deleteSelectedContacts(){
		List<Integer> selectedRows = view.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();
		for (int i = 0; i < selectedRows.size(); i++) {
			ids.add(contactsDetails.get(selectedRows.get(i)).getId());
		}
		
		rpcService.deleteContacts(ids, new AsyncCallback<List<ContactDetailsDTO>>() {
			public void onSuccess(List<ContactDetailsDTO> result) {
				contactsDetails = result;
				List<String> data = new ArrayList<String>();
				for (ContactDetailsDTO contact : result) {
					data.add(contact.getDisplayName());
				}
				view.setData(data);
			}
			
			public void onFailure(Throwable caught) {}
		});
	}
	
	public void sortContacts(){
		List<String> contacts = new ArrayList<String>();
		List<ContactDetailsDTO> cdto = new ArrayList<ContactDetailsDTO>();
		for (ContactDetailsDTO contact : contactsDetails) {
			contacts.add(contact.getDisplayName());
		}
		
		java.util.Collections.sort(contacts);
		
		for (String name : contacts) {
			for (int j = 0; j < contactsDetails.size(); j++) {
				if(contactsDetails.get(j).getDisplayName().equals(name)){
					cdto.add(contactsDetails.get(j));
					break;
				}
			}
		}
		
		this.contactsDetails = cdto;
		view.setData(contacts);
	}
	
	/**
	 * @return the contacts
	 */
	public List<ContactDetailsDTO> getContactsDetails() {
		return this.contactsDetails;
	}
	
	public void setContactsDetails(List<ContactDetailsDTO> contactsDetails) {
		this.contactsDetails =  contactsDetails;
	}

	private ContactDTO getContact(String id){
		ContactDTO contact = null;
		for (ContactDTO contactDTO : this.contacts) {
			if(contactDTO.getId().equals(id)){
				contact = contactDTO;
				break;
			}
		}
		return contact;
		
	}
}
