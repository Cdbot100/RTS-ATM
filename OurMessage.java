public class OurMessage {
    public int Type, Account, pin;
    public String Contents;
    double Balance;
    //message type1 

    void OurMessage(){

    }

    void Set(int T, String C){
        this.Type  = T;
        this.Contents = C;
    }

    void SetAccount(int a, int p){
        this.Account = a;
        this.pin = p;
    }
}
