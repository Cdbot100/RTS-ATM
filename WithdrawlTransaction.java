// Return values:
//      7 - Invalid PIN Number
//      8 - Daily Debit Limit Error
//      9 - Account Balance Error
//      10 - Request Success

public class WithdrawlTransaction{

    private int accountIndex;
    private int debitCardIndex;
    private CheckingAccount checkingAccounts[];
    private savingsaccount savingsAccounts[];
    private DebitCard debitCards[];

    int WithdrawFunds(int cardID, int pin, float amount, CheckingAccount checkingAccounts[], savingsaccount savingsAccounts[], DebitCard debitCards[])   
    {

// Loop through Debit Card Records and find Card associated with cardID

        for (int i=0; i<5; i=i+1)
        {
            if (debitCards[i].cardId == cardID)
                debitCardIndex = i;
        }

// check match of CardID and PIN

        if (debitCards[debitCardIndex].validatePin(pin) == 0)
            return 7;
        else
        {
// check daily debit limit
            if (!(debitCards[debitCardIndex].checkDailyDebitLimit(amount)))
                return 8;
            else
            {
// check account balance
                if (debitCards[debitCardIndex].accountNumber < 2000)
                {
                    for (int i=0; i<5; i=i+1)
                    {
                        if (debitCards[debitCardIndex].accountNumber == checkingAccounts[i].accountNumber)
                            accountIndex = i;
                    }

                    if (checkingAccounts[accountIndex].Balance < amount)
                        return 9;
                    else 
                    {
                        debitCards[debitCardIndex].updateDailyDebitTotal(amount);
                        checkingAccounts[accountIndex].debit(amount);
                        return 10;
                    }
                }
                else
                {
                    for (int i=0; i<5; i=i+1)
                    {
                        if (debitCards[debitCardIndex].accountNumber == savingsAccounts[i].accountNumber)
                            accountIndex = i;
                    }
                    
                    if (savingsAccounts[accountIndex].Balance < amount)
                        return 9;
                    else 
                    {
                        debitCards[debitCardIndex].updateDailyDebitTotal(amount);
                        savingsAccounts[accountIndex].debit(amount);
                        return 10;
                    }
                }
            }        
        }            

    }
}

