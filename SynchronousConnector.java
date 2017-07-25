import java.io.*;
import java.util.*;
class SynchronousConnector extends message{
private boolean messageBufferFull = false;
private boolean responseBufferFull = false;
private ArrayList <message> messageBuffer = new ArrayList<message>();
private ArrayList <message> responseBuffer = new ArrayList<message>();

    public void send (message in, message out){
        messageBuffer.add(in);
        messageBufferFull = true;
        //signal();
        while (!responseBufferFull){
           // wait();
        }
        responseBuffer.remove(out);
        responseBufferFull = false;
    }
    public void recieve (message out){
        while (!messageBufferFull){
            //wait();
        } 
        messageBuffer.remove(out);
        messageBufferFull = false;
    }

    public void reply (message response){
        responseBuffer.add(response);
        responseBufferFull = true;
        //signal();
    }

    public void isMessage (message result){
        if (messageBufferFull){
            messageBuffer.remove(result);
        }
    }
}


