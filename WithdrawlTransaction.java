public class WithdrawlTransaction{

    private int accountIndex;
    private DebitCard debitCardCopy;
    private CheckingAccount checkingAccounts[];
    private savingsaccount savingsAccounts[];
    private DebitCard debitCards[];

    void WithdrawFunds(int cardID, int pin, float amount, CheckingAccount checkingAccounts[], savingsaccount savingsAccounts[], DebitCard debitCards[])   
    {

// Loop through Debit Card Records and find Card associated with cardID

        for (int i=0; i<5; i=i+1)
        {
            if (debitCards[i].cardId == cardID)
                accountIndex = i;
        }

// check match of CardID and PIN

        if (debitCards[accountIndex].validatePin(pin) == 0)
            System.out.println("Withdraw Error: Invalid PIN Number");
        else
        {
// check daily debit limit
            if (!(debitCards[accountIndex].checkDailyDebitLimit(amount)))
                System.out.println("Withdraw Error: Amount greater than Daily Debit Limit");
            else
            {
// check account balance
                if (debitCards[accountIndex].accountNumber < 2000)
                    if (checkingAccounts[accountIndex].Balance < amount)
                        System.out.println("Withdraw Error: Amount greater than Account Balance");
                    else 
                    {
                        debitCards[accountIndex].updateDailyDebitTotal(amount);
                        checkingAccounts[accountIndex].debit(amount);
                    }
                else
                    if (savingsAccounts[accountIndex].Balance < amount)
                        System.out.println("Withdraw Error: Amount greater than Account Balance");
                    else 
                    {
                        debitCards[accountIndex].updateDailyDebitTotal(amount);
                        savingsAccounts[accountIndex].debit(amount);
                    }
            }        
        }            

    }
}

