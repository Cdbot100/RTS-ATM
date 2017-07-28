private class Accounts {

private CheckingAccount checkingAccounts[] = new CheckingAccount[5];
private savingsaccount savingsAccount[] = new savingsaccount[5];
private DebitCard debitCards[] = new DebitCard[5]; 

    void Accounts()
    {
        checkingAccount1 = new CheckingAccount();
        checkingAccount2 = new CheckingAccount();
        checkingAccount3 = new CheckingAccount();
        checkingAccount4 = new CheckingAccount();
        checkingAccount5 = new CheckingAccount();

        checkingAccount1.open (1001);
        checkingAccount2.open (1002);
        checkingAccount3.open (1003);
        checkingAccount4.open (1004);
        checkingAccount5.open (1005);

        checkingAccount1.credit (200);
        checkingAccount2.credit (200);
        checkingAccount3.credit (300);
        checkingAccount4.credit (400);
        checkingAccount5.credit (500);

        checkingAccounts[0] = checkingAccount1;
        checkingAccounts[1] = checkingAccount2;
        checkingAccounts[2] = checkingAccount3;
        checkingAccounts[3] = checkingAccount4;
        checkingAccounts[4] = checkingAccount5;

        
    }

}