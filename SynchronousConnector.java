class SynchronousConnector{
private boolean messageBufferFull = false;
private boolean responseBufferFull = false;

    public void send (in message, out response){
        //place message in buffer;
        messageBufferFull = true;
        signal();
        while (!responseBufferFull){
            wait();
        }
        //remove response from response buffer;
        responseBufferFull = false;
    }

    public void recieve (out message){
        while (!messageBufferFull){
            wait();
        } 
        //remove message from buffer;
        messageBufferFull = false;
    }

    public void reply (in response){
        //Place response in response buffer;
        responseBufferFull = true;
        signal();
    }

    public void isMessage (out result){

    }
}


