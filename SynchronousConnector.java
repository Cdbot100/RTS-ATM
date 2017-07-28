import java.io.*;
import java.util.*;

class SynchronousConnector extends message{
private boolean messageBufferFull = false;
private boolean responseBufferFull = false;
private ArrayList <message> messageBuffer = new ArrayList<message>();
private ArrayList <message> responseBuffer = new ArrayList<message>();
client Client = new client(); 
server Server = new server();

    public void SynchronousConnector(client ClientObject, server ServerObject){
        this.Client = ClientObject;
        this.Server = ServerObject;
    }
    public void send (message in, message out){
        messageBuffer.add(in);
        messageBufferFull = true;
        Server.signal();
        while (!responseBufferFull){
            try{
            this.wait();
            }catch(InterruptedException e) {
                //something goes here 
            }
        }
        responseBuffer.remove(out);
        responseBufferFull = false;
    }
    public message recieve (message out){
        while (!messageBufferFull){
            try{
            this.wait();
            }catch(InterruptedException e) {
            //something goes here 
            }
        } 
        messageBuffer.remove(out);
        messageBufferFull = false;
        return out;
    }

    public void reply (message response){
        responseBuffer.add(response);
        responseBufferFull = true;
        Client.signal();
    }

    public void isMessage (message result){
        if (messageBufferFull){
            return true;
        }
        else return false;
    }
}


