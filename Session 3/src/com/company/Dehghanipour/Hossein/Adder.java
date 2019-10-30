package com.company.Dehghanipour.Hossein;


import java.util.ArrayList;

public class Adder extends Thread {
    private static int COUNTER_ID = 0 ;
    private static int NUMBER_OF_THREADS ;
    private static int SUMMATION = 0 ;
    private int id = 0 ;
    private static ArrayList<Adder> threadPool = new ArrayList<>() ;
    public static int[] frequencyKeeper ;
    private static int freqArraySize = 0 ;
    private static int loopCounter = 0 ;


    public static void initializeThreadPool() {
        for(int i = 0 ; i < NUMBER_OF_THREADS ; i++){
            threadPool.add(new Adder(COUNTER_ID++));
        }
    }

    public static void setBasicInfo(int threadNumbers , int loopCounter){
        NUMBER_OF_THREADS = threadNumbers ;
        freqArraySize = loopCounter*threadNumbers + 1 ;
        Adder.loopCounter = loopCounter ;
    }

    private static void coolDown(){
        SUMMATION = COUNTER_ID  = 0 ;
    }


    public static void initializeFrequencyKeeper(){
        frequencyKeeper = new int[freqArraySize];
        for(int i = 0 ; i < freqArraySize ; i++){
            frequencyKeeper[i] = 0 ;
        }
    }

    public static void terminateThreadPool(){
        coolDown();
        threadPool.clear();
    }



    @Override
    public void run() {
        super.run();
        try {
            for ( int i = 0 ; i <  Adder.loopCounter ; i++){
               Thread.sleep(0,320);
                SUMMATION++ ;
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
