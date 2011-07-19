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
package com.linkcorp.mvp.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.Widget;
import com.linkcorp.mvp.client.presenter.ContactsPresenter;

/**
 * @author Nelson Lionel KEMINSE
 * 18 juil. 2011 16:58:56
 */
public class ContactsView extends Composite implements ContactsPresenter.Display{

	private static ContactsViewUiBinder uiBinder = GWT.create(ContactsViewUiBinder.class);

	interface ContactsViewUiBinder extends UiBinder<Widget, ContactsView> {}
	
	@UiField
	Button editContactButton;
	@UiField
	Button deleteContactButton;
	@UiField
	Button addContactButton;
	@UiField
	FlexTable contactTable;

	public ContactsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public HasClickHandlers getAddButton() {
		return addContactButton;
	}

	@Override
	public HasClickHandlers getEditButton() {
		return editContactButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return deleteContactButton;
	}

	@Override
	public HasClickHandlers getContactTable() {
		return contactTable;
	}

	@Override
	public void setData(List<String> data) {
		this.contactTable.removeAllRows();
		for (int i = 0; i < data.size(); i++) {
			this.contactTable.setWidget(i, 0, new CheckBox());
			this.contactTable.setText(i, 1, data.get(i));
		}
	}

	@Override
	public int getClickRow(ClickEvent event) {
		int selectedRow = -1;
		Cell cell = this.contactTable.getCellForEvent(event);
		
		if(cell != null)
			selectedRow = cell.getRowIndex();
		
		return selectedRow;
	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();
		int rowCount = this.contactTable.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			CheckBox chkBox = (CheckBox) this.contactTable.getWidget(i, 0);
			if(chkBox.getValue())
				selectedRows.add(i);
		}
		
		return selectedRows;
	}

	@Override
	public Widget asWidget(){
		return this;
	}

	@Override
	public List<String> getValue() { return null; }
	@Override
	public void setValue(List<String> value) {}
	@Override
	public void setValue(List<String> value, boolean fireEvents) {}
	@Override
	public HandlerRegistration addValueChangeHandler( ValueChangeHandler<List<String>> handler) { return null; }

}
