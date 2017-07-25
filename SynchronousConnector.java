import java.io.*;
import java.util.*;
class SynchronousConnector extends message{
private boolean messageBufferFull = false;
private boolean responseBufferFull = false;
private ArrayList <message> messageBuffer = new ArrayList<message>();


    public void send (message in, message out){
        //place message in buffer;
        messageBufferFull = true;
        signal();
        while (!responseBufferFull){
            wait();
        }
        //remove response from response buffer;
        responseBufferFull = false;
    }
    public void recieve (message out){
        while (!messageBufferFull){
            wait();
        } 
        //remove message from buffer;
        messageBufferFull = false;
    }

    public void reply (message response){
        //Place response in response buffer;
        responseBufferFull = true;
        signal();
    }

    public void isMessage (message result){

    }
}


