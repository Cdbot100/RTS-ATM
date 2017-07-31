import java.util.*;
class client extends Thread{
    public static Scanner cin = new Scanner(System.in);
    public int account;
    public int pin;
    public float balance;
    public int selection;
    public float transactionAmount;
    public int transferAccount;
    
    connector connector1;

    public void setConnector (connector connector){
        connector1 = connector;
    }

    public void run(){ 
        boolean ValidAccount = false;
        int selection = -1;
        OurMessage request = new OurMessage();
        OurMessage response = new OurMessage();

        System.out.println("******************************************");
        System.out.println("           Welcome to MSU-ATM");
        System.out.println("******************************************");
        while(!ValidAccount){
            System.out.println("Please Enter your account Number:");
            account = Integer.parseInt(cin.next());
            System.out.println("Please Enter your pin:");
            pin = Integer.parseInt(cin.next());
            
            request.pin = pin;
            request.Account = account;
            request.requestType = 0;
            
            response =  connector1.send(request);            
            if (response.requestType == 5){
                System.out.println("Error: PIN is incorrect");
                ValidAccount = false;
            }
            else if (response.requestType == 6){
                ValidAccount = true;
            }   
        }
        while(selection != 5){
            System.out.println("******************************************");
            System.out.println("Please make your selection:"
                + "\n 1. Check Balance \n 2. Deposit"
                + "\n 3. Withdraw \n 4. Transfer\n 5. Quit\n");
            selection = cin.nextInt();
            if (selection <=0 || selection > 5){
                System.out.println("Error");
            }
            switch(selection){
                case 1:
                    System.out.println("Fetching account information:");
                    request.requestType = 1; 
                    response =  connector1.send(request);
                    System.out.printf("\nCurrent Balance is $%.2f\n", response.Balance);
                break;
                case 2:
                    System.out.println("Please Enter Amount for Deposit:");
                    transactionAmount = cin.nextFloat();
                    request.transactionAmount = transactionAmount;
                    request.requestType = 2; 
                    System.out.println("Fetching account information:");                 
                    response =  connector1.send(request);
                    System.out.printf("Your New Balance is $%.2f\n",  response.Balance);
                break;
                case 3:
                    System.out.println("Please Enter Amount for Withdraw:");
                    transactionAmount = cin.nextFloat();
                    request.transactionAmount = transactionAmount;
                    System.out.println("Fetching account information:");
                    request.requestType = 3; 
                    response =  connector1.send(request);
                    if (response.requestType == 5){
                        System.out.println("Error: Insuffcient Funds");
                    }
                    System.out.printf("Your New Balance is $%.2f\n",  response.Balance);
                break;
                case 4:
                    System.out.println("Please Enter Account information:");
                    transferAccount = cin.nextInt();
                    request.transferAccount = transferAccount;
                    System.out.println("Please Enter Amount:");
                    transactionAmount = cin.nextFloat();
                    request.transactionAmount= transactionAmount;
                    System.out.println("Fetching account information:");
                    request.requestType = 4; 
                    response =  connector1.send(request);
                    if (response.requestType == 5){
                        System.out.println("Error Transferring funds, Check Balance");
                    }
                    System.out.printf("Sucessfully Transferred, Your New Balance is $%.2f\n",  response.Balance);
                break;
            }
        }
        System.out.print("\nThank For Using our ATM.");
        System.exit(0);
    }
}


