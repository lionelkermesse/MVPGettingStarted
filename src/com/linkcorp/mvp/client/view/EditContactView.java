/**
 * 
 */
package com.linkcorp.mvp.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.linkcorp.mvp.client.presenter.EditContactPresenter;

/**
 * @author LionelKermesse
 *
 */
public class EditContactView extends Composite implements EditContactPresenter.Display{
	private DialogBox dialogEditBox;
	private Button saveButton;
	private Button cancelButton;
	private TextBox firstNameTextBox;
	private TextBox lastNameTextBox;
	private TextBox emailTextBox;
	private VerticalPanel mainPanel;
	private HorizontalPanel buttonsPanel;
	private static EditContactView instance = null;
	
	public EditContactView() {
		initUi();
		instance = this;
	}
	
	private void initUi(){
		dialogEditBox = new DialogBox();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		firstNameTextBox = new TextBox();
		lastNameTextBox = new TextBox();
		emailTextBox = new TextBox();
		mainPanel = new VerticalPanel();;
		buttonsPanel = new HorizontalPanel();
		
		dialogEditBox.setText("Edit Contact");
		mainPanel.add(firstNameTextBox);
		mainPanel.add(lastNameTextBox);
		mainPanel.add(emailTextBox);
		
		buttonsPanel.add(saveButton);
		buttonsPanel.add(cancelButton);
		buttonsPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
		mainPanel.add(buttonsPanel);
		
		dialogEditBox.add(mainPanel);
		
	}
	
	public DialogBox getDialogEditBox(){
		return dialogEditBox;
	}

	@Override
	public HasText getFirstNameTextBox() {
		return firstNameTextBox;
	}

	@Override
	public HasText getLastNameTextBox() {
		return lastNameTextBox;
	}

	@Override
	public HasText getEmailTextBox() {
		return emailTextBox;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}
	
	public Widget asWidget(){
		return this;
	}
	
	public static EditContactView get(){
		return instance;
	}
	
}
