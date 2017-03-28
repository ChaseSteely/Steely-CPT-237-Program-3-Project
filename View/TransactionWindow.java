//FILE: TransactionWindow.java
//PROG: Marshall Chase Steely
//PURP: Displays account selected in Startuo and allows deposits and withdrawals.

package edu.tridenttech.CPT237.Steely.Bank.View;

import java.text.DecimalFormat;

import edu.tridenttech.CPT237.Steely.Bank.Model.Account;
import edu.tridenttech.CPT237.Steely.Bank.Model.Bank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TransactionWindow implements EventHandler<ActionEvent> {

	public Stage myStage;

	// Button load;
	// private TextField accNum;
	private TextField amt;
	private TextArea ta;
	Button dep;
	Button w;
	Button done;
	// Button exit;
	Bank bank = Bank.getInstance();
	Account acc;
	DecimalFormat dec = new DecimalFormat("0.00");

	public TransactionWindow() {
		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = new Stage();
		myStage.setScene(scene);
		myStage.setTitle("Transaction");

		/*
		 * accNum = new TextField(); accNum.setText("Enter Account #");
		 * pane.getChildren().add(accNum);
		 */

		/*
		 * load = new Button("Load"); pane.getChildren().add(load);
		 * load.setOnAction(this);
		 */

		amt = new TextField();
		amt.setText("Enter Amount");
		pane.getChildren().add(amt);
		amt.setDisable(true);

		dep = new Button("Deposit");
		pane.getChildren().add(dep);
		dep.setOnAction(this);
		dep.setDisable(true);

		w = new Button("Withdrawal");
		pane.getChildren().add(w);
		w.setOnAction(this);
		w.setDisable(true);

		done = new Button("Done");
		pane.getChildren().add(done);
		done.setOnAction(this);
		done.setDisable(true);

		/*
		 * exit = new Button("Exit"); pane.getChildren().add(exit);
		 * exit.setOnAction(this);
		 */

		ta = new TextArea();
		pane.getChildren().add(ta);

	}// END TransactionWindow

	public void show(String aNum) {

		acc = bank.findAccountByNum(aNum);
		if (acc != null) {
			ta.setText("Account # " + acc.getAccountNumber() + "\n" + "Account Type: " + acc.getAccountType() + "\n"
					+ "Balance: $" + dec.format(acc.getBalance()));
			dep.setDisable(false);
			w.setDisable(false);
			amt.setDisable(false);
			done.setDisable(false);
			myStage.show();
			myStage.toFront();
		}

		if (acc == null) {
			myStage.close();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Account Number Not Found");
			alert.show();
		}

	}// END show method

	public void toFront() {
		myStage.toFront();
	}

	public boolean isShowing() {
		return myStage.isShowing();
	}

	public void handle(ActionEvent event) {

		// String aNum;
		// aNum = accNum.getText();
		String amount;
		amount = amt.getText();
		Double a;// parsed amount
		Button button = (Button) (event.getSource());
		// Bank bank = Bank.getInstance();
		// Account acc = bank.findAccountByNum(aNum);
		// DecimalFormat dec = new DecimalFormat("0.00");

		/*
		 * if (button == load) { if (acc != null) { ta.setText("Account # " +
		 * acc.getAccountNumber() + "\n" + "Account Type: " +
		 * acc.getAccountType() + "\n" + "Balance: $" +
		 * dec.format(acc.getBalance())); dep.setDisable(false);
		 * w.setDisable(false); amt.setDisable(false); done.setDisable(false);
		 * load.setDisable(true); accNum.setDisable(true);
		 * 
		 * }
		 * 
		 * if (acc == null) { Alert alert = new Alert(AlertType.ERROR);
		 * alert.setTitle("Account Number Not Found"); alert.show();
		 * accNum.clear(); } } // END Load
		 */

		if (button == dep) {

			a = Double.parseDouble(amount);
			acc.deposit(a);
			ta.setText("Account # " + acc.getAccountNumber() + "\n" + "Account Type: " + acc.getAccountType() + "\n"
					+ "Balance: $" + dec.format(acc.getBalance()));

		} // END deposit

		if (button == w) {
			a = Double.parseDouble(amount);
			if (a > (acc.getBalance())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Transaction Failed");
				alert.show();
			} else
				acc.withdraw(a);
			ta.setText("Account # " + acc.getAccountNumber() + "\n" + "Account Type: " + acc.getAccountType() + "\n"
					+ "Balance: $" + dec.format(acc.getBalance()));

		} // END withdrawal

		if (button == done) {
			ta.clear();
			// accNum.clear();
			amt.clear();
			// accNum.setDisable(false);
			// load.setDisable(false);
			dep.setDisable(true);
			w.setDisable(true);
			amt.setDisable(true);
			myStage.close();
		} // END done

		// if (button == exit) {
		// myStage.close();
		// }//END Exit

	}// END handle method

}// END class
