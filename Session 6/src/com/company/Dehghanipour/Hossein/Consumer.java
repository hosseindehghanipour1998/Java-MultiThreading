package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread {
    int id = 0 ;

    public static ArrayList<Consumer> threadPool = new ArrayList<>() ;
    private static int idCounter = 0 ;
    private static int THREAD_POOL_NUMBERS ;
    public static void createThreadPool(int threadPoolSize){
        THREAD_POOL_NUMBERS = threadPoolSize ;
        for ( int i = 0 ; i < threadPoolSize ; i++){
            Consumer.threadPool.add(new Consumer(idCounter++)) ;
        }
    }

    public Consumer(int id){
        this.id = id ;
    }

    @Override
    public void run() {
        super.run();
        try {
            for ( int i = 0 ; i < Main.consumer_producerLoopCounter ; i++){
                Main.consumerSemaphore.acquire();
                Main.locker.lock();
                int food = (int) Main.bowls.pop();
                System.out.println("ID : " + this.id + " |  POPPED : " + food) ;
                IODevice.writePerLine(Main.FILE_NAME , "ID : " + this.id + " |  POPPED : " + food);
                Main.locker.unlock();
                Main.producerSemaphore.release();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
