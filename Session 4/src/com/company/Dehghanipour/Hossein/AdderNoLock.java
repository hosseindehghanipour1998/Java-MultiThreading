package com.company.Dehghanipour.Hossein;


import java.util.ArrayList;

public class AdderNoLock extends Thread {
    private static int idCounter = 0 ;
    private static final int THOUSAND = 1000 ;
    private static int NUMBER_OF_THREADS = 0 ;
    private static int SUMMATION = 0 ;
    private static int loopCounter = THOUSAND * THOUSAND ;
    private int id = 0 ;
    private static ArrayList<AdderNoLock> threadPool = new ArrayList<>() ;
    private static int[] frequencyMonitor;



    public static void initializeThreadPool(int threadPoolSize) {
        NUMBER_OF_THREADS = threadPoolSize ;
        SUMMATION = 0 ;

        for(int i = 0 ; i < NUMBER_OF_THREADS ; i++){
            threadPool.add(new AdderNoLock(idCounter++));
        }
    }

    public static void frequencyIncrement(int index){
        frequencyMonitor[index] ++ ;
    }

    public static void printFrequency(){
        for(int i = 0 ; i < frequencyMonitor.length ; i++){
            if(frequencyMonitor[i] != 0){
                System.out.println("Index  (" + i +") : " +   frequencyMonitor[i]);
                IODevice.writePerLine(Main.FILE_NAME , "Index  (" + i +") : " +   frequencyMonitor[i]);
            }
        }
    }

    public static void terminateThreadPool(){
        threadPool.clear();
        SUMMATION = 0 ;
        idCounter = 0 ;
    }


    public static void frequencyMonitorInitializer(int numberOfThreads){
        NUMBER_OF_THREADS = numberOfThreads ;
        frequencyMonitor = new int[NUMBER_OF_THREADS*loopCounter + 1 ];
        for(int i = 0 ; i < loopCounter ; i++){
            frequencyMonitor[i] = 0 ;
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            for ( int i = 0 ; i < loopCounter ; i++){
                //Main.locker.lock();
                SUMMATION++ ;
                //Main.locker.unlock();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public AdderNoLock(int id){
        this.id = id ;
    }

    public static ArrayList<AdderNoLock> getThreadPool() {
        return threadPool;
    }

    public static int getSUMMATION() {
        return SUMMATION;
    }

    public static void setSUMMATION(int SUMMATION) {
        AdderNoLock.SUMMATION = SUMMATION;
    }
}
