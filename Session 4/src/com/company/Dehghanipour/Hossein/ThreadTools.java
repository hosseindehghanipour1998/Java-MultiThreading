package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;

public class ThreadTools  {
    private static final int MILLION = 1000000;
    private static final int THOUSAND = 1000 ;

    public static float caluclateAverageTime(ArrayList<Long> times){
        long sum = 0 ;
        for ( long l : times){
            sum += l ;
        }
        return ( sum / times.size());
    }

    public static void runThreads(){

        for (int i = 0 ; i < Main.producerNumbers ; i++ ){
            Consumer.threadPool.get(i).start();
            Producer.threadPool.get(i).start();
        }

        try {
            for ( int i = 0 ; i < Main.producerNumbers ; i++ ){
                Consumer.threadPool.get(i).join();
                Producer.threadPool.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static long AdderNoLockThread(){
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( AdderNoLock A : AdderNoLock.getThreadPool()){
            A.start();
        }
        try {
            for ( AdderNoLock A : AdderNoLock.getThreadPool()){
                A.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return (totalTime/THOUSAND) ;

    }

    public static long AdderLockThread(){
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( AdderLock A : AdderLock.getThreadPool()){
            A.start();
        }
        try {
            for ( AdderLock A : AdderLock.getThreadPool()){
                A.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return (totalTime/THOUSAND) ;

    }
}
