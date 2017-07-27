import java.io.*;
import com.sun.org.apache.xpath.internal.operations.And;

public class TransferTransaction {

    void transfer(account from, account to, double amt){
        if (from.account == from.pin && to.pin == to.account){
            if (from.Balance > amt){
                from.debit(amt);
                to.credit(amt);
            }
        }
    }
}

    
