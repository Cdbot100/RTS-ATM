public class account {
    public double Balance;
    public int account;
    public int pin;
    public int LastDepositAmount;
    public int dailydebitlimit = 300;

    void account(float b, int a, int p){
        Balance = Math.abs(b);
        account = a;
        pin = p;
        LastDepositAmount= 0;
    }
    void credit(double amount){
        Balance += amount;
    }

    void debit(double amount){
        Balance -= amount;

    }

    int getAccountNumber(){
        return account;
    }

}