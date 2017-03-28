package edu.tridenttech.CPT237.Steely.Bank.Model;

//Filename	: MainClass.java
//Programmer: Anna Huston
//Purpose	: Allows a customer to view account info and interact with account.
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainClass
{
	static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException
	{
		Bank b = Bank.getInstance();
		b.loadTransactions("Transactions.csv");
		customerInput();
	}

	private static void customerInput()
	{
		String accountNumber = "";
		System.out.println("Welcome to Johnson's Inn Security Bank.");
		System.out.println("When you have finished using the program, enter quit to exit the program");
		Bank bank = Bank.getInstance();

		while (!accountNumber.equalsIgnoreCase("quit"))
		{
			System.out.println("Please enter your account number to continue.");
			accountNumber = console.nextLine();

			Account account = bank.findAccountByNum(accountNumber);

			String action = "";

			if (account != null)
			{
				System.out.printf("%s account number: %s%n",
						account.getAccountType() == Account.AccountType.CHECKING ? "Checking" : "Savings",
						account.getAccountNumber());

				System.out.println("(D)eposit/n");
				System.out.println("(W)ithdraw/n");
				System.out.println("(S)how Balance/n");
				action = console.nextLine();

				switch (action.charAt(0))
				{
					case 'D':
					case 'd':
					{

						double amount = 0;
						System.out.println("Please enter an amount to deposit.");
						amount = console.nextDouble();
						console.nextLine();
						account.deposit(amount);
					}
						break;
					case 'W':
					case 'w':
					{
						double amount = 0;
						System.out.println("Please enter an amount to withdraw");
						amount = console.nextDouble();
						console.nextLine();
						account.withdraw(amount);

					}
						break;
					case 'S':
					case 's':
					{
						double balance = account.getBalance();
						System.out.printf("Your current balance: %.2f\n", balance);
					}
						break;
					default:
					{
						System.out.println("Please enter a character for the action you wish to perform.");

					}

				}

			}

		}
	}
}
