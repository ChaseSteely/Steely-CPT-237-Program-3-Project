package edu.tridenttech.CPT237.Steely.Bank.Model;

/**
 * @author Anna Huston
 * @author Anderson Jackson
 */

public class SavingsAccount extends Account 
{
    private final int MAX_WITHDRAWALS=7;
    private int numWithdrawals=0;
    private final double SERV_FEE=.23;
    
    public SavingsAccount(String number, double initialBalance) 
    {
        super(number, initialBalance, Account.AccountType.SAVINGS);
    }
    
    @Override
    public boolean withdraw(double amount)
    {
        boolean result=false;
        
        result=super.withdraw(amount);
        if(result)
        {
            numWithdrawals++;
            if(numWithdrawals>MAX_WITHDRAWALS)
            {
                super.serviceFee(SERV_FEE);
            }
        }
        
        return result;
     }

    public void resetWithdrawalCount()
    {
        numWithdrawals=0;
    }
}
