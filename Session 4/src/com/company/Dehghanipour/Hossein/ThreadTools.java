package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadTools  {

    public static long producerRun(){
        System.out.println("In Producer");
        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( Producer mp : Producer.threadPool){
            System.out.println("Starting Threads");
            mp.start();
        }
        try {
            int i = 0 ;
            for ( Producer mp :Producer.threadPool ){
                System.out.println("Joining Threads  " + (i++));
                mp.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("In Catch");
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //Terminate Thread Pool.
        System.out.println("Done Joining ! ");
        return (totalTime/MILLION) ;

    }
    public static long consumerRun(){
        System.out.println("In Consumer");
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
        //Terminate Thread Pool.
        return (totalTime/MILLION) ;
    }

    public static long AdderThread(){

        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
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
