package edu.tridenttech.CPT237.Steely.Bank.Model;

/**
 * @author Anna Huston
 * @author Anderson Jackson
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account
{
	static public enum AccountType { CHECKING, SAVINGS }
	
	//type of object array list will hold inside arrows
	private ArrayList<Transaction> transactions = new ArrayList<>(); 
	
	private AccountType accountType;
	private double balance = 0;
	private final String accountNumber;

	public double getBalance()
	{
		return balance;
	}

	public AccountType getAccountType()
	{
		return accountType;
	}

	public String getAccountNumber()
	{
		return accountNumber;
	}

	public Account(String accountNumber, double balance, AccountType type)
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = type;
		Transaction o = new Transaction(Transaction.Type.OPEN_ACCNT, balance, balance);
		transactions.add(o);			
	}

	public boolean deposit(double amount)
	{
		balance += amount;
		Transaction t = new Transaction(Transaction.Type.DEPOSIT,amount, balance);
		transactions.add(t);
		return true;
	}

	public boolean withdraw(double amount)
	{
		if (amount <= balance) {
			balance -= amount;
			Transaction w =new Transaction(Transaction.Type.WITHDRAW, amount, balance);
			transactions.add(w);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean serviceFee(double amount)
	{
		if (balance >= amount) {
			balance-=amount;
			Transaction s = new Transaction(Transaction.Type.SERV_FEE, amount, balance);
			transactions.add(s);
		}
		return true;
	}
	
	public List<Transaction> getTransactions()
	{
		return Collections.unmodifiableList(transactions); 
	}
}
