import javax.net.ssl.ExtendedSSLSession;

public class TransferTransaction{

    private int accountIndex;
    private int recieveAccountIndex;
    // recieveAccountType 1 = checking account
    // recieveAccountType 2 = savings account
    private int recieveAccountType;
    private CheckingAccount checkingAccounts[];
    private savingsaccount savingsAccounts[];
    private DebitCard debitCards[];

    void transferFunds(int cardID, int pin, float amount, int toAccount, CheckingAccount checkingAccounts[], savingsaccount savingsAccounts[], DebitCard debitCards[])
    {
// Loop through Debit Card Records and find Card associated with cardID

        for (int i=0; i<5; i=i+1)
        {
            if (debitCards[i].cardId == cardID)
                accountIndex = i;
        }

// Loop through checking or savings accounts to find account to recieve funds

        if (toAccount < 2000)
            for (int i=0; i<5; i=i+1)
            {
                if (checkingAccounts[i].accountNumber == toAccount)
                    recieveAccountIndex = i;
                    recieveAccountType = 1;
            }
        else
            for (int i=0; i<5; i=i+1)
            {
                if (savingsAccounts[i].accountNumber == toAccount)
                    recieveAccountIndex = i;
                    recieveAccountType = 2;
            }

// check match of CardID and PIN

        if (debitCards[accountIndex].validatePin(pin) == 0)
            System.out.println("Withdraw Error: Invalid PIN Number");
        else
        {

// check account balance for sufficient funds, either return error or process transfer
            if (debitCards[accountIndex].accountNumber < 2000)
                    if (checkingAccounts[accountIndex].Balance < amount)
                        System.out.println("Withdraw Error: Amount greater than Account Balance");
                    else 
                    {
                        checkingAccounts[accountIndex].debit(amount);
                        if (recieveAccountType == 1)
                            checkingAccounts[recieveAccountIndex].credit(amount);
                        else
                            savingsAccounts[recieveAccountIndex].credit(amount);
                    }
                else
                    if (savingsAccounts[accountIndex].Balance < amount)
                        System.out.println("Withdraw Error: Amount greater than Account Balance");
                    else 
                    {
                        savingsAccounts[accountIndex].debit(amount);
                        if (recieveAccountType == 1)
                            checkingAccounts[recieveAccountIndex].credit(amount);
                        else
                            savingsAccounts[recieveAccountIndex].credit(amount);
                    }


        }




    } 




    
}