package com.linkcorp.mvp.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.linkcorp.mvp.client.service.ContactServiceAsync;
import com.linkcorp.mvp.client.view.ContactsView;
import com.linkcorp.mvp.shared.ContactDetailsDTO;

public class ContactsPresenterTest extends GWTTestCase {
	private ContactsPresenter contactsPresenter;
	private HandlerManager eventBus;
	private ContactServiceAsync rpcService;
	private ContactsPresenter.Display view;
	
	public void gwtSetUp(){
		rpcService = GWT.create(ContactServiceAsync.class);
		eventBus = new  HandlerManager(null);
		view = new ContactsView();
		contactsPresenter = new ContactsPresenter(rpcService, eventBus, view); 
	}

	public final void testSortContacts() {
		List<ContactDetailsDTO> contactsDetails = new ArrayList<ContactDetailsDTO>();
		contactsDetails.add(new ContactDetailsDTO("0", "c_contact"));
		contactsDetails.add(new ContactDetailsDTO("1", "a_contact"));
		contactsDetails.add(new ContactDetailsDTO("2", "b_contact"));
		contactsPresenter.setContactsDetails(contactsDetails);
		contactsPresenter.sortContacts();
		assertEquals("a_contact", contactsPresenter.getContactsDetails().get(0).getDisplayName());
		assertEquals("b_contact", contactsPresenter.getContactsDetails().get(0).getDisplayName());
		assertEquals("c_contact", contactsPresenter.getContactsDetails().get(0).getDisplayName());
	}

	@Override
	public String getModuleName() {
		return "com.linkcorp.mvp.MVPGettingStarted";
	}

}
