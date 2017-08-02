
public class savingsaccount extends Account{

    float cumIntrest = 0;
    int debitCount =0;
    int maxFreeDebits = 3;
    double bankcharge = 1.50;
    double intrestRate = .05;
    float interest = 0; 

    float readCumulitiveIntrest() {
        return cumIntrest;
    }

    void clearDebitCount(){
        debitCount = 0;
    }

    void addIntrest(float rate){
        interest *= intrestRate;
        Balance += interest;
    }
    
}