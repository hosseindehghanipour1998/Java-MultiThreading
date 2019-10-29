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


    public static void printArrayofSummations(){
        for(int i = 0 ; i < freqArraySize ; i++){
            if ( frequencyKeeper[i] != 0 ){
                System.out.println("i : " + frequencyKeeper[i]);
            }
        }
    }

    private static void initializeThreadPool(int threadNumbers , int frequncyArraySize) {
        NUMBER_OF_THREADS = threadNumbers ;
        freqArraySize = frequncyArraySize ;
        frequencyKeeper = new int[frequncyArraySize];
        for(int i = 0 ; i < threadNumbers ; i++){
            threadPool.add(new Adder(COUNTER_ID++));
        }
    }

    private static void reset(){
        SUMMATION = COUNTER_ID = NUMBER_OF_THREADS = 0 ;
        for(int i = 0 ; i < freqArraySize ; i++){
            frequencyKeeper[i] = 0 ;
        }
        threadPool.clear();
    }

    public static void warmUp(int poolSize , int arraySize){
        initializeThreadPool(poolSize,poolSize*arraySize + 1);
    }

    public static void coolDown(){
        reset();
    }


    @Override
    public void run() {
        super.run();

        try {
            for ( int i = 0 ; i < freqArraySize ; i++){
                Thread.sleep(100);
                SUMMATION++ ;
            }

        } catch (InterruptedException e) {
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
}
