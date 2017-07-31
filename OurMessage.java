// requestType: Types of Requests sent from Client to Server
//      0 - Debit Card PIN Check
//      1 - Check Balance
//      2 - Deposit
//      3 - Withdraw
//      4 - Transfer
//      *5 - Error Response
//      *6 - Valid PIN Response 
//      

public class OurMessage {
    public int requestType, Account, pin;
    public float Balance;
    public float transactionAmount;
    

}