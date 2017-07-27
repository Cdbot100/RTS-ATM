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
            Client.wait();
            }catch(InterruptedException e) {
                //something goes here 
            }
        }
        responseBuffer.remove(out);
        responseBufferFull = false;
    }
    public void recieve (message out){
        while (!messageBufferFull){
            try{
            Server.wait();
            }catch(InterruptedException e) {
            //something goes here 
            }
        } 
        messageBuffer.remove(out);
        server.RecieveServiceRequest(out);
        messageBufferFull = false;
    }

    public void reply (message response){
        responseBuffer.add(response);
        responseBufferFull = true;
        Client.signal();
    }

    public void isMessage (message result){
        if (messageBufferFull){
            messageBuffer.remove(result);
        }
    }
}


