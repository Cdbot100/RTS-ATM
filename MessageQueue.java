monitor MessageQueue{

private Interger maxCount ;
private Interger messageCount = 0;

    public send (in message){
        while (messageCount = maxCount){
            wait();
        } 
        //place message in buffer;
        //increment messageCount;
        if (messageCount = 1) {
            signal();
        }
    }

    public recieve (out message){
        while (messageCount = 0) {
            wait();
        }
        //remove message from buffer;
        messageCount--;
        if (messageCount = maxCount-1){
            signal();
        } 
    }

    public isMessage(out result){

    }

}