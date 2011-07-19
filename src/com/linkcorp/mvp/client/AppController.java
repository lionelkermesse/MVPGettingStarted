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
package com.linkcorp.mvp.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.linkcorp.mvp.client.event.AddConctactEventHandler;
import com.linkcorp.mvp.client.event.AddContactEvent;
import com.linkcorp.mvp.client.event.UpdatedContactEvent;
import com.linkcorp.mvp.client.event.UpdatedContactEventHandler;
import com.linkcorp.mvp.client.event.EditContactEvent;
import com.linkcorp.mvp.client.event.EditContactEventHandler;
import com.linkcorp.mvp.client.presenter.ContactsPresenter;
import com.linkcorp.mvp.client.presenter.EditContactPresenter;
import com.linkcorp.mvp.client.presenter.Presenter;
import com.linkcorp.mvp.client.service.ContactServiceAsync;
import com.linkcorp.mvp.client.view.ContactsView;
import com.linkcorp.mvp.client.view.EditContactView;
import com.linkcorp.mvp.shared.ContactDTO;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 19:10:19
 */
public class AppController implements Presenter, ValueChangeHandler<String>{
	private final HandlerManager eventBus;
	private final ContactServiceAsync rpcService;
	private HasWidgets container;
	private ContactDTO contact;

	public AppController(ContactServiceAsync rpcService, HandlerManager eventBus){
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		bind();
	}
	
	@Override
	public void go(final HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("list");
		}
		else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void bind() {
		History.addValueChangeHandler(this);
		
		this.eventBus.addHandler(AddContactEvent.TYPE, new AddConctactEventHandler() {
			@Override
			public void onAddContactEvent(AddContactEvent event) {
				doAddNewContact();
			}
		});
		
		this.eventBus.addHandler(EditContactEvent.TYPE, new EditContactEventHandler() {
			@Override
			public void onEditContact(EditContactEvent event) {
				contact = event.getContact();
				doEditContact(event.getContact());
			}
		});
		
		this.eventBus.addHandler(UpdatedContactEvent.TYPE, new UpdatedContactEventHandler() {
			
			public void onUpdatedContact(UpdatedContactEvent event) {
				doUpdatedContact();
			}
		});
	}
	
	private void doAddNewContact(){
		History.newItem("add");
	}
	
	private void doEditContact(ContactDTO contact){
		History.newItem("edit", false);
		Presenter presenter = new EditContactPresenter(rpcService, this.eventBus, new EditContactView(), contact);
		presenter.go(this.container);
	}
	
	private void doUpdatedContact(){
		History.newItem("list");
	}
	
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null){
			Presenter presenter = null;
		
			if(token.equals("list")){
				presenter = new ContactsPresenter(rpcService, eventBus, new ContactsView());
			}
			else if(token.equals("add")){
				presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView(), new ContactDTO());
			}
			else if(token.equals("edit")){
				presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView(), contact);
			}
			
			if(presenter != null){
				presenter.go(container);
			}
		}
	}

	public AppController asWidget(){
		return this;
	}
}
