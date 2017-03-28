//FILE: StartupWindow.java
//PROG: Marshall Chase Steely
//PURP: Opens and allows the user to choose between several options and opens up needed window to execute action.

package edu.tridenttech.CPT237.Steely.Bank.View;

import edu.tridenttech.CPT237.Steely.Bank.Model.Bank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StartupWindow implements EventHandler<ActionEvent> {
	private Stage myStage;
	private TransactionWindow transaction;
	private AccountCreation create;
	private Transfer tFunds;
	private ComboBox<String> accountsCB = new ComboBox<>();
	Button show;
	Button open;
	Button transfer;
	Button done;

	public StartupWindow(Stage stage) {
		transaction = new TransactionWindow();
		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = stage;
		myStage.setScene(scene);
		myStage.setTitle("StartUp");

		pane.getChildren().add(accountsCB);
		accountsCB.getItems().setAll(Bank.getInstance().getAllAccountNumbers());
		create = new AccountCreation(accountsCB);
		show = new Button("Show Account");
		pane.getChildren().add(show);
		show.setOnAction(this);

		open = new Button("Open Account");
		pane.getChildren().add(open);
		open.setOnAction(this);

		transfer = new Button("Transfer Funds");
		pane.getChildren().add(transfer);
		transfer.setOnAction(this);

		tFunds = new Transfer();

		done = new Button("Done");
		pane.getChildren().add(done);
		done.setOnAction(this);

	}

	public void handle(ActionEvent event) {

		Button button = (Button) (event.getSource());

		if (button == show) {
			if (!transaction.isShowing()) {
				transaction.show(accountsCB.getValue());
			} else {
				transaction.toFront();
			}
		} // END show account

		if (button == open) {
			if (!create.isShowing()) {
				create.show();
			} else {
				create.toFront();
			}
		}

		if (button == transfer) {
			if (!tFunds.isShowing()) {
				tFunds.show();
			} else {
				tFunds.toFront();
			}
		}

		if (button == done) {
			myStage.close();
		}

	}// END Handle

	public void show() {
		myStage.show();
	}

}
