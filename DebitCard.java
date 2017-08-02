public class DebitCard {

    public int cardId;
    public int pin;
    public int accountNumber;
    private float dailyDebitTotal;
    private float dailyDebitLimit = 300;
void newDebitCard(int newCardID, int newPIN, int newAccountNumber, float newDailyDebitTotal)
    {
        cardId = newCardID;
        pin = newPIN;
        accountNumber = newAccountNumber;
        dailyDebitTotal = newDailyDebitTotal;   
    }

    int validatePin(int pinInput)
    {
    
        if (this.pin == pinInput){
        return this.accountNumber;
        }
        else return 0;
    }

    void updateDailyDebitTotal(float amount){
        this.dailyDebitTotal += amount;
    }

    boolean checkDailyDebitLimit(float amount){
        if ((dailyDebitTotal + amount) > dailyDebitLimit)
            return false;
        else
            return true; 
    }
}