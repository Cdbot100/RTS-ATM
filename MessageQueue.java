import java.io.*;

class MessageQueue extends SynchronousConnector{

private int maxCount;
private int messageCount;

    public void send (message in){
        while (messageCount == maxCount){
            //wait();
        } 
        //place message in buffer;
        messageCount++;
        if (messageCount == 1) {
            //signal();
        }
    }

    public void recieve (message out){
        while (messageCount == 0) {
           // wait();
        }
        //remove message from buffer;
        messageCount--;
        if (messageCount == maxCount-1){
            //signal();
        } 
    }

    public void isMessage(message result){

    }

}