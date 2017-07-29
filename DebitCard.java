public class DebitCard {

    private int cardId;
    private int pin;
    private int accountNumber;
    private float dailyDebitTotal;
    private float dailyDebitLimit = 300;
    
    void DebitCard(int newCardID, int newPIN, int newAccountNumber, float newDailyDebitTotal)
    {
        cardId = newCardID;
        pin = newPIN;
        accountNumber = newAccountNumber;
        dailyDebitTotal = newDailyDebitTotal;   
    }

    boolean validatePin(int pinInput)
    {
    
        if (this.pin == pinInput){
        return true;
        }
        else return false;
    }

    void updateDailyDebitTotal(float amount){
        this.dailyDebitTotal += amount;
// Needs to check to make sure it doesnt go over limit?
    }

    float checkDailyDebitLimit(){
        return this.dailyDebitLimit;
    }
}