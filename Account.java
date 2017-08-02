public class Account {
    public float Balance = 0;
    public int accountNumber, pin;
    
    void open (int newAccountNumber)
    {
       accountNumber = newAccountNumber;
    }

    float readBalance ()
    {
        return Balance;
    }

    void credit(float amount){
        Balance += amount;
    }

    void debit(float amount){
        Balance -= amount;
  
    }

    int getAccountNumber(){
        return accountNumber;
    }


}