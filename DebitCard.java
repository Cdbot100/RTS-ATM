import java.io.*;
public class debitcard extends account{
    public int cardid;
    
    void debitcard(){
        cardid = ((int)Math.random());
    }

    boolean validatepin(){
        if (this.account == this.pin){
        return true;
        }
        else return false;
    }

    void updateDailyDebitTotal(int amt){
        this.dailydebitlimit -= amt;
    }

    int checkDailyDebitLimit(){
        return this.dailydebitlimit;
    }
}