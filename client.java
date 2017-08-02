import java.util.*;


class client extends Thread{
    public static Scanner cin = new Scanner(System.in);
    public int debitId;
    public int pin;
    public float balance;                                           //inital variables
    public int selection;
    public float transactionAmount;
    public int transferAccount;
    public String newline = System.getProperty("line.separator");
    
    connector connector1;                                           //our connector 

    public void setConnector (connector connector){                 //method to set connector 
        connector1 = connector;
    }

    public void run(){                                              //main method
        boolean ValidAccount = false;
        int selection = -1;                                         //init selections
        OurMessage request = new OurMessage();
        OurMessage response = new OurMessage();                     //two messages, one for request, one for response

        
        while(!ValidAccount){                                       //while account not valid
            System.out.println("******************************************");
            System.out.println("           Welcome to MSU-ATM");        //user output 
            System.out.println("******************************************" + newline);
            System.out.println("Please Enter your Debit Card ID and press <Enter>:");
            debitId = Integer.parseInt(cin.next());
            System.out.println("Please Enter your PIN and press <Enter>:");
            pin = Integer.parseInt(cin.next());                     //request account and pin
            
            request.pin = pin;
            request.debitId = debitId;
            request.requestType = 0;
            
            response =  connector1.send(request);                   //have server verify account
            if (response.requestType == 7){
                System.out.println("Error: Invalid PIN" + newline);      //if PIN is invalid
                ValidAccount = false;
            }
            if (response.requestType == 11){                        // if account is invalid
                System.out.println("Error: No such account" + newline);
                ValidAccount = false;
            }
            else if (response.requestType == 6){                    //if account and PIN are valid
                ValidAccount = true;
            }
        }
        while(selection != 5){                                      //loop for services
            System.out.println(newline + "******************************************");
            System.out.println("Please make your selection:"        //user makes selection
                + "\n 1. Check Balance \n 2. Deposit"
                + "\n 3. Withdraw \n 4. Transfer\n 5. Quit\n");
            selection = cin.nextInt();
            if (selection <=0 || selection > 5){                    //if incorrect selection
                System.out.println("Selection Error");
            }
            switch(selection){                                      //check balance transaction
                case 1:
                    System.out.println(newline + "Fetching account information:");
                    request.requestType = 1; 
                    response =  connector1.send(request);
                    if (response.requestType == 7)
                        System.out.println("Error: Invalid PIN");
                    else
                        System.out.printf("\nCurrent Balance is $%.2f\n", response.Balance);
                break;
                case 2:                                             //Deposit Transaction
                    System.out.println("Please Enter Amount for Deposit:");
                    transactionAmount = cin.nextFloat();
                    request.transactionAmount = transactionAmount;
                    request.requestType = 2; 
                    System.out.println(newline + "Fetching account information:");                 
                    response =  connector1.send(request);
                    if (response.requestType == 7)
                        System.out.println("Error: Invalid PIN");
                    else System.out.printf("Deposit successful, your New Balance is $%.2f\n",  response.Balance);
                break;
                case 3:                                             //Withdraw Transaction
                    System.out.println("Please Enter Amount for Withdraw:");
                    transactionAmount = cin.nextFloat();
                    request.transactionAmount = transactionAmount;
                    System.out.println(newline + "Fetching account information:");
                    request.requestType = 3; 
                    response =  connector1.send(request);

                    switch (response.requestType){
                        case 7:
                            System.out.println("Transaction Error: Invalid PIN");
                        break;
                        case 8:
                            System.out.println("Transaction Error: Withdrawal amount exceeds Daily Debit Limit");
                        break;
                        case 9:
                            System.out.println("Transaction Error: Withdrawal amount exceeds total Account Balance");
                        break;
                        case 10:
                            System.out.println("Transaction Successful!");
                        break;
                    }

                    System.out.printf("Your Current Balance is $%.2f\n",  response.Balance);

                break;
                case 4:                                             //transfer funds 
                    System.out.println("Please Enter Account information:");
                    transferAccount = cin.nextInt();
                    request.transferAccount = transferAccount;
                    System.out.println("Please Enter Amount:");
                    transactionAmount = cin.nextFloat();
                    request.transactionAmount= transactionAmount;
                    System.out.println(newline + "Fetching account information:");
                    request.requestType = 4; 
                    response =  connector1.send(request);

                    switch (response.requestType){
                        case 7:
                            System.out.println("Transaction Error: Invalid PIN");
                        break;
                        case 9:
                            System.out.println("Transaction Error: Withdrawal amount exceeds total Account Balance");
                        break;
                        case 10:
                            System.out.println("Transaction Successful!");
                        break;
                        case 11:
                            System.out.println("Transaction Error: No such account exists.");
                        break;
                        case 12:
                            System.out.println("Transaction Error: Can't transfer funds to origin account");
                        break;
                    }

                    System.out.printf("Your Current Balance is $%.2f\n",  response.Balance);
                break;
                }
            }
        System.out.print(newline + "Thank For Using our ATM.");             //fin
        System.exit(0);
        }
    }


