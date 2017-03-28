package edu.tridenttech.CPT237.Steely.Bank.Model;
/**
 * @author Anna Huston
 */
public class CheckingAccount extends Account
{
	private final double MIN_BALANCE = 100.00;
	private final double SERV_FEE = .13;

	public CheckingAccount(String number, double initialBalance)
	{
		super(number, initialBalance,Account.AccountType.CHECKING);
	}

	@Override
	public boolean withdraw(double amount)
	{
		boolean result = super.withdraw(amount);
		if (result) {
			if (super.getBalance() < MIN_BALANCE) {
				super.serviceFee(SERV_FEE);
			}
		}

		return result;
	}
}
