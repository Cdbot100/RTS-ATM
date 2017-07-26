import java.io.*;
import java.util.*;

class SynchronousConnector extends message{
private boolean messageBufferFull = false;
private boolean responseBufferFull = false;
private ArrayList <message> messageBuffer = new ArrayList<message>();
private ArrayList <message> responseBuffer = new ArrayList<message>();
client ClientObject = new client(); 
    public void SynchronousConnector(){
        
        //server serverobject = new server();
    }
    public void send (message in, message out){
        messageBuffer.add(in);
        messageBufferFull = true;
        //serverobject.signal();
        while (!responseBufferFull){
            try{
            ClientObject.wait();
            }catch(InterruptedException e) {
                //something goes here 
            }
        }
        responseBuffer.remove(out);
        responseBufferFull = false;
    }
    public void recieve (message out){
        while (!messageBufferFull){
            // try{
            // ServerObject.wait();
            // }catch(InterruptedException e) {
            // //something goes here 
            // }
        } 
        messageBuffer.remove(out);
        messageBufferFull = false;
    }

    public void reply (message response){
        responseBuffer.add(response);
        responseBufferFull = true;
        ClientObject.signal();
    }

    public void isMessage (message result){
        if (messageBufferFull){
            messageBuffer.remove(result);
        }
    }
}


