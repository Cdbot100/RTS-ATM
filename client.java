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
            response.SetAccount(account, pin);
            //connector1.send(response,response);
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
                    response.Type = 1; 
                    response =  connector1.send(request);
                    System.out.printf("\nCurrent Balance is $%.2f\n", response.Balance);
                    

                break;
                case 2:
                    response.Type = 2; 
                    //connector1.send(response,response);
                    System.out.printf("Your New Balance is $%.2f\n",  response.Balance);
                break;
                case 3:
                    response.Type = 3; 
                    //connector1.send(response,response);
                    System.out.printf("Your New Balance is $%.2f\n",  response.Balance);
                break;
                case 4:
                    response.Type = 4; 
                    //connector1.send(response,response);
                    System.out.printf("Sucessfully Transferred, Your New Balance is $%.2f\n",  response.Balance);
                break;
            }
            System.out.print("\nThank For Using our ATM.");
            System.exit(0);
        }
    }
}




// public void run(): is used to perform action for a thread.
// public void start(): starts the execution of the thread.JVM calls the run() method on the thread.
// public void sleep(long miliseconds): Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds.
// public void join(): waits for a thread to die.
// public void join(long miliseconds): waits for a thread to die for the specified miliseconds.
// public int getPriority(): returns the priority of the thread.
// public int setPriority(int priority): changes the priority of the thread.
// public String getName(): returns the name of the thread.
// public void setName(String name): changes the name of the thread.
// public Thread currentThread(): returns the reference of currently executing thread.
// public int getId(): returns the id of the thread.
// public Thread.State getState(): returns the state of the thread.
// public boolean isAlive(): tests if the thread is alive.
// public void yield(): causes the currently executing thread object to temporarily pause and allow other threads to execute.
// public void suspend(): is used to suspend the thread(depricated).
// public void resume(): is used to resume the suspended thread(depricated).
// public void stop(): is used to stop the thread(depricated).
// public boolean isDaemon(): tests if the thread is a daemon thread.
// public void setDaemon(boolean b): marks the thread as daemon or user thread.
// public void interrupt(): interrupts the thread.
// public boolean isInterrupted(): tests if the thread has been interrupted.
// public static boolean interrupted(): tests if the current thread has been interrupted.