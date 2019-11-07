package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadTools  {
    private static final int MILLION = 1000000;
    private static final int THOUSAND = 1000 ;

    public static long producerRun(){
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( Producer mp : Producer.threadPool){
            mp.start();
        }
        try {
            for ( Producer mp :Producer.threadPool ){
                mp.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return (totalTime/MILLION) ;

    }
    public static long consumerRun(){
        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( Consumer mp : Consumer.threadPool){
            mp.start();
        }
        try {
            for ( Consumer mp :Consumer.threadPool ){
                mp.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return (totalTime/MILLION) ;
    }

    public static long AdderThread(){
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( Adder A : Adder.getThreadPool()){
            A.start();
        }
        try {
            for ( Adder A :Adder.getThreadPool()){
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
