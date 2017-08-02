public class connector 
{

    private boolean requestBufferFull;
    private boolean responseBufferFull; 
    private OurMessage requestBuffer = new OurMessage();
    private OurMessage responseBuffer = new OurMessage();
    private client client; 
    private server server; 

    public void connector()
    {
        requestBufferFull = false;
        responseBufferFull= false;

    }

    public void setClient (client newClient){
        client = newClient;

    }

    public void setServer (server newServer){
        server = newServer;

    }

    public OurMessage send (OurMessage request)
    {
        
        requestBuffer = request;
        requestBufferFull = true;
    
        synchronized (server)
        {
            server.notify();
        }

              
       synchronized (client)
       { 
            try
            {
                while (!responseBufferFull)
                {
                    client.wait();
                }
            }
            catch(InterruptedException c)
            {}
       }

        responseBufferFull = false;
        return responseBuffer;
    }

    public OurMessage receive ()
    {
        synchronized (server)
        {
            try
            {
                while (!requestBufferFull)
                {
                    server.wait();
                }
            }
            catch(InterruptedException e){}
         
        }

        
        requestBufferFull = false;
        return requestBuffer;
    }

    public void reply (OurMessage response)
    {
        
        responseBuffer = response;
        responseBufferFull = true;
        synchronized (client)
        {
            client.notify();
    
        }
    }

}





