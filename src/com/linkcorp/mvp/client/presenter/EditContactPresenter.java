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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.linkcorp.mvp.client.service.ContactServiceAsync;
import com.linkcorp.mvp.shared.ContactDTO;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 19:23:40
 */
public class EditContactPresenter implements Presenter {
	public interface Display{
		HasText getFirstNameTextBox();
		HasText getLastNameTextBox();
		HasText getEmailTextBox();
		
		HasClickHandlers getSaveButton();
		HasClickHandlers getCancelButton();
		abstract Widget asWidget();
	}
	
	private final ContactDTO contact;
	private ContactServiceAsync rpcService;
	private Display view;
	private final HandlerManager eventBus;
	
	public EditContactPresenter(ContactServiceAsync rpcService, HandlerManager eventBus, Display view, ContactDTO contact){
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.view = view;
		this.contact = contact;
		this.view.getFirstNameTextBox().setText(this.contact.getFirstname());
		this.view.getLastNameTextBox().setText(this.contact.getLastname());
		this.view.getEmailTextBox().setText(this.contact.getEmail());
	}
	
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(this.view.asWidget());
	}
	
	@Override
	public void bind() {
		this.view.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
				eventBus.fireEvent(event);
			}
		});
	}

	public void doSave(){
		this.contact.setFirstname(view.getFirstNameTextBox().getText());
		this.contact.setLastname(view.getLastNameTextBox().getText());
		this.contact.setEmail(view.getEmailTextBox().getText());
		rpcService.updateContact(contact, new AsyncCallback<ContactDTO>() {
			
			public void onFailure(Throwable caught) {
				Window.alert("Verify data");
			}

			@Override
			public void onSuccess(ContactDTO result) {
				Window.alert("Contact Save!!");
			}
		});
	}
	
	public void doCancel(){
		
	}

}
