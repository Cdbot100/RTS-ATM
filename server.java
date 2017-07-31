

class server extends Thread {

    public OurMessage request = new OurMessage();
    public OurMessage response = new OurMessage();
    
    private Accounts currentAccounts = new Accounts();
    private connector connector1;

    private DebitCard debitCardCopy = new DebitCard();
    private int accountIndex;

    // accountType 1 = Savings
    // accountType 2 = Checking
    
    private int accountType;

    private CheckBalanceTransaction balanceCheck = new CheckBalanceTransaction();
    private WithdrawlTransaction withdraw = new WithdrawlTransaction();

    public void setConnector (connector connector){
        connector1 = connector;
    }

   
     public void run()
     {

        while (true)
        {
// Server waits until notified of request

            synchronized (this)
            {
                try 
                    {
                        this.wait();
                    }
                catch(InterruptedException e){}
            }

// Receive Request from Client through Connector

            request = connector1.receive();
        
            switch (request.requestType)
            {
// Debit Card PIN check
                case 0:
// Loop through Debit Card Records and find Card associated with inputed Account Number
                    
                    for (int i=0; i<5; i=i+1)
                    {
                        if (currentAccounts.debitCards[i].accountNumber == request.Account)
                        {
                            debitCardCopy = currentAccounts.debitCards[i];
                            accountIndex = i;
                            if (request.Account < 2000)
                                accountType = 2;
                            else
                                accountType = 1; 
                        }
                    }
// Check PIN entered with Debit Card Data and return response to Client through Connector

                    if (debitCardCopy.pin == request.pin)
                        response.requestType = 6;
                    else
                        response.requestType = 5;
                
                    connector1.reply(response);
                break;

// Check Balance
                case 1:
                    if (accountType == 1)
                        response.Balance = balanceCheck.CheckBalance(currentAccounts.savingsAccounts[accountIndex]);
                    else
                        response.Balance = balanceCheck.CheckBalance(currentAccounts.checkingAccounts[accountIndex]);
                    
                    connector1.reply(response);

                break;

// Deposit
                case 2:

                    if (accountType == 1)
                    {
                        currentAccounts.savingsAccounts[accountIndex].credit(request.transactionAmount);
                        response.Balance = balanceCheck.CheckBalance(currentAccounts.savingsAccounts[accountIndex]);
                    }
                    else
                    {
                        currentAccounts.checkingAccounts[accountIndex].credit(request.transactionAmount);
                        response.Balance = balanceCheck.CheckBalance(currentAccounts.checkingAccounts[accountIndex]);
                    }
                    
                    connector1.reply(response);

                break;

// Withdrawal
                case 3:

                    withdraw.WithdrawFunds(currentAccounts.debitCards[accountIndex].cardId, request.pin, request.transactionAmount, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);

                     if (accountType == 1)                   
                        response.Balance = balanceCheck.CheckBalance(currentAccounts.savingsAccounts[accountIndex]);
                    
                    else                   
                        response.Balance = balanceCheck.CheckBalance(currentAccounts.checkingAccounts[accountIndex]);
                                        
                    connector1.reply(response);
                    
                break;
            
// Transfer            
                case 4:
                break;
            }
        }
    }
}