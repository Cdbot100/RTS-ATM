public class Account {
    public float Balance = 0;
    public float IntRate;
    public int accountNumber, pin;
    public DebitCard Card;
    
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

    boolean debit(float amount){
        if (amount < Balance){
            Balance -= amount;
            return true;
        }
        else return false;
    }

    int getAccountNumber(){
        return accountNumber;
    }

    void addIntrest(float rate){
        IntRate= rate;
    }

}