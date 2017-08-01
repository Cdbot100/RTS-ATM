
public class CheckBalanceTransaction{

private CheckingAccount checkingAccounts[];
private savingsaccount savingsAccounts[];
private DebitCard debitCards[];

private int debitCardIndex;

    float CheckBalance(int cardID, int pin, CheckingAccount checkingAccounts[], savingsaccount savingsAccounts[], DebitCard debitCards[])
    {
// Loop through Debit Card Records and find Card associated with cardID

        for (int i=0; i<5; i=i+1)
        {
            if (debitCards[i].cardId == cardID)
                debitCardIndex = i;
        }

// Return error (-1) if PIN does not match CardId

        if (debitCards[debitCardIndex].validatePin(pin) == 0)
            return -1;

// Return balance of appropriate account

        if (debitCards[debitCardIndex].accountNumber < 2000)
        {
            for (int i=0; i<5; i=i+1)
            {
                if (debitCards[debitCardIndex].accountNumber == checkingAccounts[i].accountNumber)
                    return checkingAccounts[i].Balance;
            }
        }
            
        else 
        {
            for (int i=0; i<5; i=i+1)
            {
                if (debitCards[debitCardIndex].accountNumber == savingsAccounts[i].accountNumber)
                    return savingsAccounts[i].Balance;
            }
        }

// Account not found error return

        return -2;
    }
}