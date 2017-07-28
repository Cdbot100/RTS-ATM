


public class console {
    public static void main(String args[]) {
        
        connector connector = new connector();
        client c = new client();
        server s = new server();
        
        c.setConnector(connector);
        s.setConnector(connector);
        connector.setClient(c);
        connector.setServer(s);

        c.start();
        s.start();
        

      
    }
}