

class server extends Thread {

    public OurMessage request = new OurMessage();
    public OurMessage response = new OurMessage();
    
    private Accounts currentAccounts = new Accounts();
    private connector connector1;

    private DebitCard debitCardCopy = new DebitCard();

// accountType 1 = Savings
// accountType 2 = Checking
    
    private int accountType;

// accountFlag = false: account not found
// accountFlag = true: account found

    private boolean accountFlag = false;


    private CheckBalanceTransaction balanceCheck = new CheckBalanceTransaction();
    private WithdrawlTransaction withdraw = new WithdrawlTransaction();
    private TransferTransaction transfer = new TransferTransaction();

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

// Loop through Debit Card Records and find Card associated with inputed debitCardId
                    
                    for (int i=0; i<5; i=i+1)
                    {
                        if (currentAccounts.debitCards[i].cardId == request.debitId)
                        {
                            accountFlag = true;
                            debitCardCopy = currentAccounts.debitCards[i];
                           
                            if (debitCardCopy.accountNumber < 2000)
                                accountType = 2;
                            else
                                accountType = 1; 
                        }
                    }
                             
// Return account error if Debit Card not found
                        
                    if (accountFlag == false)
                    {   
                        response.requestType = 11;
                        connector1.reply(response);
                    }

// Check PIN entered with Debit Card Data and return response to Client through Connector

                    else if (accountFlag = true)

                        if (debitCardCopy.pin == request.pin)
                        {
                            response.requestType = 6;
                            connector1.reply(response);
                        }
                        else
                        {
                            response.requestType = 7;
                            connector1.reply(response);
                        }
                break;

// Check Balance
                case 1:
                 
                    response.Balance = balanceCheck.CheckBalance(request.debitId, request.pin, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);  
                    
                    if (response.Balance == -1)
                        response.requestType = 7;
                        
                    connector1.reply(response);

                break;

// Deposit
                case 2:

                    if (accountType == 1)
                    {
                         for (int i=0; i<5; i=i+1)
                        {
                            if (debitCardCopy.accountNumber == currentAccounts.savingsAccounts[i].accountNumber)
                                currentAccounts.savingsAccounts[i].credit(request.transactionAmount);
                        }

                        response.Balance = balanceCheck.CheckBalance(request.debitId, request.pin, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);  
                    
                        if (response.Balance == -1)
                            response.requestType = 7;
                    }

                    else
                    {
                        for (int i=0; i<5; i=i+1)
                        {
                            if (debitCardCopy.accountNumber == currentAccounts.checkingAccounts[i].accountNumber)
                                currentAccounts.checkingAccounts[i].credit(request.transactionAmount);
                        }
                        
                        response.Balance = balanceCheck.CheckBalance(request.debitId, request.pin, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);  
                        
                        if (response.Balance == -1)
                            response.requestType = 7;
                    }
                    
                    connector1.reply(response);

                break;

// Withdrawal
                case 3:

                    response.requestType = withdraw.WithdrawFunds(request.debitId, request.pin, request.transactionAmount, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);
                    response.Balance = balanceCheck.CheckBalance(request.debitId, request.pin, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);  
                    
                    connector1.reply(response);
                    
                break;
            
// Transfer            
                case 4:

                    response.requestType = transfer.transferFunds(request.debitId, request.pin, request.transactionAmount, request.transferAccount, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);
                    response.Balance = balanceCheck.CheckBalance(request.debitId, request.pin, currentAccounts.checkingAccounts, currentAccounts.savingsAccounts, currentAccounts.debitCards);  

                    connector1.reply(response);

                break;
            }
        }
    }
}