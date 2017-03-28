//FILE: Transfer.java
//PROG: Marshall Chase Steely
//PURP: Transfers amount from a selected account to another account selected from a ComboBox

package edu.tridenttech.CPT237.Steely.Bank.View;

import edu.tridenttech.CPT237.Steely.Bank.Model.Account;
import edu.tridenttech.CPT237.Steely.Bank.Model.Bank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Transfer implements EventHandler<ActionEvent> {

	public Stage myStage;
	Bank bank = Bank.getInstance();
	private ComboBox<String> accountFrom = new ComboBox<>();
	private ComboBox<String> accountTo = new ComboBox<>();
	private TextField amt;
	Button ok;
	Account accFrom;
	Account accTo;

	public Transfer() {
		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = new Stage();
		myStage.setScene(scene);
		myStage.setTitle("Transfer");

		pane.getChildren().add(accountFrom);
		accountFrom.getItems().setAll(Bank.getInstance().getAllAccountNumbers());

		pane.getChildren().add(accountTo);
		accountTo.getItems().setAll(Bank.getInstance().getAllAccountNumbers());

		amt = new TextField();
		amt.setPromptText("Enter Amount");
		pane.getChildren().add(amt);

		ok = new Button("OK");
		pane.getChildren().add(ok);
		ok.setOnAction(this);

	}

	public void toFront() {
		myStage.toFront();
	}

	public boolean isShowing() {
		return myStage.isShowing();
	}

	@Override
	public void handle(ActionEvent event) {

		Button button = (Button) (event.getSource());

		if (button == ok) {

			String amount;
			amount = amt.getText();
			Double a;
			a = Double.parseDouble(amount);
			String from = accountFrom.getValue();
			accFrom = bank.findAccountByNum(from);
			String to = accountTo.getValue();
			accTo = bank.findAccountByNum(to);
			if (a <= accFrom.getBalance()) {
				bank.makeTransfer(from, to, a);
				amt.clear();
				accountFrom.getSelectionModel().clearSelection();
				accountTo.getSelectionModel().clearSelection();
				myStage.close();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Transfer Failed");
				alert.show();
			}
		}

	}

	public void show() {

		myStage.show();
	}

}
