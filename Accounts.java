public class Accounts {

    public CheckingAccount checkingAccounts[] = new CheckingAccount[5];
    public savingsaccount savingsAccounts[] = new savingsaccount[5];
    public DebitCard debitCards[] = new DebitCard[5]; 

    private CheckingAccount checkingAccount1 = new CheckingAccount();
    private CheckingAccount checkingAccount2 = new CheckingAccount();
    private CheckingAccount checkingAccount3 = new CheckingAccount();
    private CheckingAccount checkingAccount4 = new CheckingAccount();
    private CheckingAccount checkingAccount5 = new CheckingAccount();

    private savingsaccount savingsAccount1 = new savingsaccount();
    private savingsaccount savingsAccount2 = new savingsaccount();
    private savingsaccount savingsAccount3 = new savingsaccount();
    private savingsaccount savingsAccount4 = new savingsaccount();
    private savingsaccount savingsAccount5 = new savingsaccount();

    private DebitCard debitCard1 = new DebitCard();
    private DebitCard debitCard2 = new DebitCard();
    private DebitCard debitCard3 = new DebitCard();
    private DebitCard debitCard4 = new DebitCard();
    private DebitCard debitCard5 = new DebitCard();
    

    public Accounts()
    {

// Checking Accounts: Account Number
        checkingAccount1.open (1001);
        checkingAccount2.open (1002);
        checkingAccount3.open (1003);
        checkingAccount4.open (1004);
        checkingAccount5.open (1005);

// Checking Accounts: Starting Balance

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

// Savings Accounts: Account Number

        savingsAccount1.open (2001);
        savingsAccount2.open (2002);
        savingsAccount3.open (2003);
        savingsAccount4.open (2004);
        savingsAccount5.open (2005);

// Savings Accounts: Starting Balance

        savingsAccount1.credit (1100);
        savingsAccount2.credit (1200);
        savingsAccount3.credit (1300);
        savingsAccount4.credit (1400);
        savingsAccount5.credit (1500);

        savingsAccounts[0] = savingsAccount1;
        savingsAccounts[1] = savingsAccount2;
        savingsAccounts[2] = savingsAccount3;
        savingsAccounts[3] = savingsAccount4;
        savingsAccounts[4] = savingsAccount5;

// Debit Card: CardID, PIN, Account #, dailyDebitTotal

        debitCard1.newDebitCard(3001, 3001, 1001, 200);
        debitCard2.newDebitCard(3002, 3002, 1003, 250);
        debitCard3.newDebitCard(3003, 3003, 1005, 0);
        debitCard4.newDebitCard(3004, 3004, 2002, 100);
        debitCard5.newDebitCard(3005, 3005, 2003, 150);

        debitCards[0] = debitCard1;
        debitCards[1] = debitCard2;
        debitCards[2] = debitCard3;
        debitCards[3] = debitCard4;
        debitCards[4] = debitCard5;
   
    }

}