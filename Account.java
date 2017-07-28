public class Account {
    public float Balance = 0;
    public int accountNumber;
        
    
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
// Need to make sure return error if tries to pull out more than is in the account - no negative balance
        Balance -= amount;

    }

    int getAccountNumber(){
        return accountNumber;
    }

}