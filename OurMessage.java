// requestType: Message Types
//  Requests from Client
//      0 - Debit Card PIN Check
//      1 - Check Balance
//      2 - Deposit
//      3 - Withdraw
//      4 - Transfer
//
//  Responses from Server
//      5 - General Error Response
//      6 - Valid PIN Response 
//      7 - Invalid PIN Number
//      8 - Daily Debit Limit Error
//      9 - Account Balance Error
//      10 - Request Success
//      11 - Invalid Account
//      12 - Self Transfer Error

public class OurMessage {
    public int requestType, debitId, Account, pin, transferAccount;
    public float Balance;
    public float transactionAmount;
}