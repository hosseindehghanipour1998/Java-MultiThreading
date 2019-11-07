package com.company.Dehghanipour.Hossein;


import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Adder extends Thread {
    private static int idCounter = 0 ;
    private static int NUMBER_OF_THREADS = 0 ;
    private static int SUMMATION = 0 ;
    private static int loopCounter = 500 ;
    private int id = 0 ;
    private static ArrayList<Adder> threadPool = new ArrayList<>() ;



    public static void initializeThreadPool(int threadPoolSize) {
        NUMBER_OF_THREADS = threadPoolSize ;
        for(int i = 0 ; i < NUMBER_OF_THREADS ; i++){
            threadPool.add(new Adder(idCounter++));
        }
    }

    public static void terminateThreadPool(){
        threadPool.clear();
        SUMMATION = 0 ;
        idCounter = 0 ;
    }




    @Override
    public void run() {
        super.run();
        try {
            for ( int i = 0 ; i < loopCounter ; i++){
                Main.locker.lock();
                SUMMATION++ ;
                Main.locker.unlock();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Adder(int id){
        this.id = id ;
    }

    public static ArrayList<Adder> getThreadPool() {
        return threadPool;
    }

    public static int getSUMMATION() {
        return SUMMATION;
    }

    public static void setSUMMATION(int SUMMATION) {
        Adder.SUMMATION = SUMMATION;
    }
}
