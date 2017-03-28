//FILE: Transfer.java
//PROG: Marshall Chase Steely
//PURP: Opens an account and adds it into the ComboBox.

package edu.tridenttech.CPT237.Steely.Bank.View;

import edu.tridenttech.CPT237.Steely.Bank.Model.Bank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AccountCreation implements EventHandler<ActionEvent> {

	public Stage myStage;
	RadioButton check;
	RadioButton save;
	TextField aNum;
	Button ok;
	Button cancel;
	TextField oBal;
	Bank bank = Bank.getInstance();
	ComboBox<String> accountsCB;

	public AccountCreation(ComboBox<String> c) {
		accountsCB = c;
		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = new Stage();
		myStage.setScene(scene);
		myStage.setTitle("Account Creation");

		ToggleGroup group = new ToggleGroup();

		check = new RadioButton("Checking");
		save = new RadioButton("Savings");
		check.setToggleGroup(group);
		save.setToggleGroup(group);
		pane.getChildren().add(check);
		pane.getChildren().add(save);

		aNum = new TextField();
		aNum.setPromptText("Enter Account Number");
		pane.getChildren().add(aNum);

		oBal = new TextField();
		oBal.setPromptText("Enter Opening Balance");
		pane.getChildren().add(oBal);

		ok = new Button("OK");
		pane.getChildren().add(ok);
		ok.setOnAction(this);

		cancel = new Button("Cancel");
		pane.getChildren().add(cancel);
		cancel.setOnAction(this);

	}

	public void toFront() {
		myStage.toFront();
	}

	public boolean isShowing() {
		return myStage.isShowing();
	}

	public void show() {

		myStage.show();

	}

	@Override
	public void handle(ActionEvent event) {

		Button button = (Button) (event.getSource());

		if (button == ok) {
			String accNum = aNum.getText();
			String openBal = oBal.getText();
			Double bal = Double.parseDouble(openBal);
			boolean isCheckSelected = check.isSelected();

			if (isCheckSelected) {
				bank.openCheckingAccount(accNum, bal, 0);

			} else {
				bank.openSavingsAccount(accNum, bal);
			}

			accountsCB.getItems().setAll(Bank.getInstance().getAllAccountNumbers());
			myStage.close();

		} // END ok

		if (button == cancel) {
			myStage.close();
		}

	}// END handle

}
