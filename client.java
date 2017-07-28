import java.util.*;

class client extends Thread{
    public static Scanner cin = new Scanner(System.in);
    public int account;
    public int pin;
    public double balance;
    public int selection;
    connector connector1;

    //public connector connector1 = new connector();

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
           
            if (request.requestType == 0){
                ValidAccount = true;
            }   
        }
        while(selection != 5){
            System.out.println("******************************************");
            System.out.println("           Please make your selection:"
                + "\n 1. Check Balance \n 2. Deposit"
                + "\n 3. Withdraw \n 4. Transfer\n  5. Quit\n");
            selection = cin.nextInt();
            if (selection < -1 || selection > 5){
                System.out.println("Error");
            }
            switch(selection){
                case 1:
                    request.requestType = 1; 
                    response =  connector1.send(request);
                    System.out.printf("\nCurrent Balance is $%.2f\n", response.Balance);
                    

                break;
                case 2:
                    response.requestType = 2; 
                    //connector1.send(response,response);
                    System.out.printf("Your New Balance is $%.2f\n",  response.Balance);
                break;
                case 3:
                    response.requestType = 3; 
                    //connector1.send(response,response);
                    System.out.printf("Your New Balance is $%.2f\n",  response.Balance);
                break;
                case 4:
                    response.requestType = 4; 
                    //connector1.send(response,response);
                    System.out.printf("Sucessfully Transferred, Your New Balance is $%.2f\n",  response.Balance);
                break;
            }
            System.out.print("\nThank For Using our ATM.");
            System.exit(0);
        }
    }
}


