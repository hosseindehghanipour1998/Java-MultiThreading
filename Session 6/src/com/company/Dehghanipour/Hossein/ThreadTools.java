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
        // Here I assumed the number of producers and consumers are equal.
        for (int i = 0 ; i < Main.producerNumbers ; i++ ){
            Consumer.threadPool.get(i).start();
            Producer.threadPool.get(i).start();
        }

        try {
            // Here I assumed the number of producers and consumers are equal.
            for ( int i = 0 ; i < Main.producerNumbers ; i++ ){
                Producer.threadPool.get(i).join();
                //Consumer.threadPool.get(i).join();
            }
            for(int i = 0 ; i < Main.producerNumbers ; i++ ){
                Consumer.threadPool.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}
