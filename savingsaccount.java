import java.io.*;
public class savingsaccount extends account{

    float cumIntrest = 0;
    int debitCount =0;
    int maxFreeDebits = 3;
    double bankcharge = 1.50;
    double intrestRate = .05; 

    float readCumulitiveIntrest() {
        return cumIntrest;
    }

    void clearDebitCount(){
        debitCount = 0;
    }

    void addIntrest(float rate){
        Balance *= intrestRate;
    }
    
}