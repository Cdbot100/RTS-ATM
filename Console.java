


public class console {
    public static void main(String args[]) {
        
        client c = new client();
        server s = new server();

        c.start();
        s.start();

    }
}