import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barber extends Thread {
    public  static Semaphore working = new Semaphore(0);
    private static Lock locker = new ReentrantLock() ;

    @Override
    public void run() {
        super.run();
        while (true){
            locker.lock();
            if ( BarberShop.clientsQueue.isEmpty() == false ){
                try {
                    Client pickedClient =  BarberShop.clientsQueue.poll() ;
                    System.out.println("Barber is working on client : " + pickedClient.id);
                    this.working.acquire();
                    Thread.sleep(5);
                    System.out.println("Barber is done with client : " + pickedClient.id);
                    pickedClient.waiting.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                locker.unlock();
            }
            else{
                try {
                    locker.unlock();
                    System.out.println("No Client ... Sleep Zzzzzzzz");
                    working.acquire();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}
