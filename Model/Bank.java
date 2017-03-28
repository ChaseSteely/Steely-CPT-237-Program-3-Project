package edu.tridenttech.CPT237.Steely.Bank.Model;

import java.io.File;
import java.io.FileNotFoundException;
/**
 * @author andersonjackson
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Bank
{
	private static Bank instance = new Bank();

	private ArrayList<Account> accounts = new ArrayList<>();

	/**
	 * Gets the Singleton Bank instance
	 * @return Returns the singleton Bank instance
	 */
	public static Bank getInstance()
	{
		return instance;
	}

	/**
	 * Open a new savings account and place it in the list of bank accounts.
	 * 
	 * @param accntNum the number of the new account
	 * @param initialBal the initial balance
	 * @return Returns <i>true</i> if an account is created; <i>false</i> if the account already exists or the balance is invalid
	 */
	public boolean openSavingsAccount(String accntNum, double initialBal)
	{
		if (findAccountByNum(accntNum) != null || initialBal < 0) {
			return false;
		}

		SavingsAccount savings = new SavingsAccount(accntNum, initialBal);

		return accounts.add(savings);
	}

	/**
	 * Open a new checking account and place it in the list of bank accounts.
	 * 
	 * @param accntNum the number of the new account
	 * @param initialBal the initial balance
	 * @return Returns <i>true</i> if an account is created; <i>false</i> if the account already exists or the balance is invalid
	 */
	public boolean openCheckingAccount(String accntNum, double initialBal, double minBalance)
	{
		if (findAccountByNum(accntNum) != null || initialBal < 0) {
			return false;
		}

		CheckingAccount checking = new CheckingAccount(accntNum, initialBal);

		return accounts.add(checking);
	}
	
	/**
	 * Finds the account specified by the given account number
	 * @param accntNum the number of the account to be found
	 * @return Returns the account matching the number if found; <i>null</i> if the account is not found
	 */
	public Account findAccountByNum(String accntNum)
	{
		Account acnt = null;
		Optional<Account> match = accounts.stream().filter(e -> e.getAccountNumber().equals(accntNum)).findFirst();
		if (match.isPresent()) {
			acnt = match.get();
		}
		return acnt;
	}

	/**
	 * Transfers the specified amount from the fromAccount to the toAccount.  This method can fail if either
	 * of the account numbers is invalid, or if the fromAccount has insufficient funds to make the transfer.
	 * @param fromAccountNum The account number of the account from which the money is to be withdrawn.
	 * @param toAccountNum The account number of the account to which the money is to be deposited.
	 * @param amount The amount to be transfered.
	 * @return Returns <i>true</i> if the transfer was successful, <i>false</i> otherwise
	 */
	public boolean makeTransfer(String fromAccountNum, String toAccountNum, double amount)
	{
		Account fromAccnt;
		Account toAccnt;

		fromAccnt = findAccountByNum(fromAccountNum);
		toAccnt = findAccountByNum(toAccountNum);

		if (fromAccnt == null || toAccnt == null) {
			return false;
		}

		if (fromAccnt.withdraw(amount)) {
			toAccnt.deposit(amount);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Pulls all of the account numbers from the accounts and returns them as a list of strings.
	 * @return The list of account numbers.
	 */
	public List<String> getAllAccountNumbers()
	{
		ArrayList<String> accountNums = new ArrayList<>();
		accounts.stream().forEach(e -> accountNums.add(e.getAccountNumber()));
		return accountNums;
	}
	
	/**
	 * Loads the transactions from the specified comma separated values file.  The format of the file is as follows:
	 *     O,num,type,amount
	 *     D,num,type,amount
	 *     W,num,type,amount
	 *     T,from,to,amount
	 * @param filePath Path to the file containing the transactions
	 * @throws FileNotFoundException
	 */
	public void loadTransactions(String filePath) throws FileNotFoundException
	{
		Scanner input;
		input = new Scanner(new File(filePath));

		while (input.hasNext())
		{
			String line = input.nextLine();
			// creates an string array called fields and populates each item
			// splitting by comma.
			String[] fields = line.split(",");
			// System.out.println("number of fields: " + fields.length);
			// first field and first character
			switch (fields[0].charAt(0)) {
				case 'O':
				case 'o': {
					double minBalance = 0;
					// open a new account
					String accntNum = fields[1];
					String type = fields[2];
					double initialBalance = Double.parseDouble(fields[3]);
					if (fields.length == 5)
					{
						minBalance = Double.parseDouble(fields[4]);
					}

					createAccount(accntNum, type, initialBalance, minBalance);
				} break;
				case 'D':
				case 'd': {
					// deposit into an account
					String accntNum = fields[1];
					String type = fields[2];
					double amount = Double.parseDouble(fields[3]);

					Account account = findAccountByNum(accntNum);
					account.deposit(amount);

				} break;
				case 'W':
				case 'w': {
					String accntNum = fields[1];
					String type = fields[2];
					double amount = Double.parseDouble(fields[3]);
					Account account = findAccountByNum(accntNum);
					account.withdraw(amount);
				} break;
				case 'T':
				case 't': {
					String fromAccount = fields[1];
					String toAccount = fields[2];
					double amount = Double.parseDouble(fields[3]);
					makeTransfer(fromAccount, toAccount, amount);
				} break;
				default: {
					System.out.println("Does not meet requirements");

				}

			}
		}
		input.close();
	}

	private void createAccount(String accntNum, String type, double initialBalance, double minBalance)
	{
		switch (type.charAt(0)) {
			case 's':
			case 'S': {
				openSavingsAccount(accntNum, initialBalance);
			} break;

			case 'c':
			case 'C': {
				openCheckingAccount(accntNum, initialBalance, minBalance);
			} break;
		}
	}
}
