package com.linkcorp.mvp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.linkcorp.mvp.client.service.ContactService;
import com.linkcorp.mvp.client.service.ContactServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MVPGettingStarted implements EntryPoint {
	private ContactServiceAsync rpcService;
	private HandlerManager eventBus;
	private AppController appViewer;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		rpcService = GWT.create(ContactService.class);
		eventBus = new HandlerManager(null);
		appViewer = new AppController(rpcService, eventBus);
		appViewer.go(RootPanel.get("initView"));
	}
}
