
// Return values:
//      7 - Invalid PIN Number
//      8 - Daily Debit Limit Error
//      9 - Account Balance Error
//      10 - Request Success
//      11 - Invalid Account
//      12 - Self Transfer Error

public class TransferTransaction{

    private int debitCardIndex;
    private int accountIndex;
    private int recieveAccountIndex;

// recieveAccountType 1 = checking account
// recieveAccountType 2 = savings account
    private int recieveAccountType;

// accountFlag = false: account not found
// accountFlag = true: account found
    private boolean accountFlag;

    private CheckingAccount checkingAccounts[];
    private savingsaccount savingsAccounts[];
    private DebitCard debitCards[];

    int transferFunds(int cardID, int pin, float amount, int toAccount, CheckingAccount checkingAccounts[], savingsaccount savingsAccounts[], DebitCard debitCards[])
    {
// set accountFlag
        accountFlag = false;

// Loop through Debit Card Records and find Card associated with cardID

        for (int i=0; i<5; i=i+1)
        {
            if (debitCards[i].cardId == cardID)
                debitCardIndex = i;
        }

// Return error if transfer account is the same as origin account

        if (toAccount == debitCards[debitCardIndex].accountNumber)
            return 12;

// Loop through checking or savings accounts to find account to recieve funds

        if (toAccount < 2000)
            for (int i=0; i<5; i=i+1)
            {
                if (checkingAccounts[i].accountNumber == toAccount)
                {
                    recieveAccountIndex = i;
                    recieveAccountType = 1;
                    accountFlag = true;
                }
            }

        else
            for (int i=0; i<5; i=i+1)
            {
                if (savingsAccounts[i].accountNumber == toAccount)
                {
                    recieveAccountIndex = i;
                    recieveAccountType = 2;
                    accountFlag = true;
                }
            }

// Return error if no transfer account exists

        if (!accountFlag)
            return 11;

// check match of CardID and PIN
    

        if (debitCards[debitCardIndex].validatePin(pin) == 0)
            return 7;
        else
        {

// check account balance for sufficient funds, either return error or process transfer

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
                        checkingAccounts[accountIndex].debit(amount);
                        if (recieveAccountType == 1)
                        {
                            checkingAccounts[recieveAccountIndex].credit(amount);
                            return 10;
                        }
                        else
                        {
                            savingsAccounts[recieveAccountIndex].credit(amount);
                            return 10;
                        }
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
                    savingsAccounts[accountIndex].debit(amount);
                    if (recieveAccountType == 1)
                    {
                        checkingAccounts[recieveAccountIndex].credit(amount);
                        return 10;
                    }
                    else
                    {
                        savingsAccounts[recieveAccountIndex].credit(amount);
                        return 10;
                    }
                }
            }
        }
    }
}