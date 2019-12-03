import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        Barber bob = new Barber();
        int numberOfClients = 500 ;
        Client.createThreadPool(numberOfClients);

        // Start
        bob.start();
        for (int i = 0 ; i <numberOfClients ; i++ ){
            Client.clients.get(i).start();
        }

    }



}
