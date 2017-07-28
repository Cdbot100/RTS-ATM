import java.util.*;

class client extends Thread{
    public static Scanner cin = new Scanner(System.in);
    public int account;
    public int pin;
    public double balance;
    public int selection;
    //SynchronousConnector Connector1 = new SynchronousConnector();
    public void client(/*SynchronousConnector C*/){
        //Connector1 = C;
    }
    public void run(){ 
        boolean ValidAccount = false;
        int selection = -1;
        OurMessage message1 = new OurMessage();
        OurMessage response = new OurMessage();

        System.out.println("******************************************");
        System.out.println("           Welcome to MSU-ATM");
        System.out.println("******************************************");
        while(!ValidAccount){
            System.out.println("Please Enter your account Number:");
            account = Integer.parseInt(cin.next());
            System.out.println("Please Enter your pin:");
            pin = Integer.parseInt(cin.next());
            message1.SetAccount(account, pin);
            //connector1.send(message1,response);
            if (response.Type == 0){
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
                    message1.Type = 1; 
                    //connector1.send(message1,response);
                    System.out.printf("\nCurrent Balance is $%.2f\n", message1.Balance);
                break;
                case 2:
                    message1.Type = 2; 
                    //connector1.send(message1,response);
                    System.out.printf("Your New Balance is $%.2f\n",  message1.Balance);
                break;
                case 3:
                    message1.Type = 3; 
                    //connector1.send(message1,response);
                    System.out.printf("Your New Balance is $%.2f\n",  message1.Balance);
                break;
                case 4:
                    message1.Type = 4; 
                    //connector1.send(message1,response);
                    System.out.printf("Sucessfully Transferred, Your New Balance is $%.2f\n",  message1.Balance);
                break;
            }
            System.out.print("\nThank For Using our ATM.");
            System.exit(0);
        }
    }
}
