

class server extends Thread {

    private OurMessage request = new OurMessage();
    public OurMessage response = new OurMessage();
    connector connector1;

    public void setConnector (connector connector){
        connector1 = connector;
    }

   
     public void run(){
         
// insert Server and account initializations  
       
        synchronized (this)
        {
            try 
                {
                    this.wait();
                }
            catch(InterruptedException e){}
        }

        request = connector1.receive();
        response = request;

// Do some action on accounts based on request message
// Ex:response.Balance = 500.00;

        connector1.reply(response);


    }


}




