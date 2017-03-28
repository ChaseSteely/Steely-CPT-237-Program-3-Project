package edu.tridenttech.CPT237.Steely.Bank.Model;
//Filename	: SavingsAccount.java
//Programmer: Anna Huston
//Purpose	: Manages the transaction information.

public class Transaction
{
	static public enum Type {
		OPEN_ACCNT,
		DEPOSIT,
		WITHDRAW,
		SERV_FEE
	}
	
	private Type transType;
	private double transAmount=0;
	private double transBalance=0;
	
	public Transaction (Type type, double amount, double balance)
	{
		 transType= type;
		 transAmount=amount;	 
		 transBalance=balance;
	}

	public Type getType()
	{
		return transType;
	}

	public double getTransAmount()
	{
		return transAmount;
	}
	public double getBalance()
	{
		return transBalance;
	}
}
