import java.io.*;
public class WithdrawlTransaction{
    int WithdrawFunds(account a, double amt){
        if (a.account == a.pin){
            if (amt < a.dailydebitlimit){
                a.debit(amt);
            }
        }
        else{
            return 0;
        }
        return a.Balance;
    }
}

