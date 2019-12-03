import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client extends Thread {
    int id = 0 ;
    private static int idCounter = 0;
    private static Lock locker = new ReentrantLock() ;
    public static ArrayList<Client> clients = new ArrayList<>() ;
    public Semaphore waiting = new Semaphore(0) ;

    public static void createThreadPool(int clientsNumber){
        for(int i = 0 ; i < clientsNumber ; i++){
            clients.add(new Client(idCounter++)) ;
        }

    }

    private Client(int idCounter){
        this.id = idCounter ;
    }

    @Override
    public void run() {
        super.run();
        locker.lock();
        if ( BarberShop.clientsQueue.size() < BarberShop.chairNumbers ){
            Barber.working.release();
            System.out.println("Client No : " + id + " calls the barber");
            BarberShop.clientsQueue.add(this);
            System.out.println("Client No : " + id + " Sits. size("+BarberShop.clientsQueue.size()+")");
            locker.unlock();
            try {
                this.waiting.acquire();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            locker.unlock();
            System.out.println("Client No : " + id + " is Killed");
        }
    }
}
