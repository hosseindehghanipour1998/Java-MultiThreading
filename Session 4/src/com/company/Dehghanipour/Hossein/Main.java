package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    //==============CONTROL PANEL ==========================
    private static final int producerSemaphorePermits = 3 ;
    private static final int consumerSemaphorePermits = 0 ;
    public static int stackSize = 3 ;
    public static final int consumer_producerLoopCounter = 10 ;
    private static final int consumerNumbers = 5 ;
    public static final int producerNumbers = 5 ;

    public static final int adderLoopCounter = 10 ;
    private static  int numberOfAdderThreads ;
    private static int[] threadNumbersWithoutLock = {1,2} ;
    private static int threadNumbersWithLock = 2 ;
    //============ PART II Vars ============================
    public static ReentrantLock locker = new ReentrantLock() ;
    public static Semaphore producerSemaphore = new Semaphore(producerSemaphorePermits) ;
    public static Semaphore consumerSemaphore = new Semaphore(consumerSemaphorePermits) ;
    public static Stack bowls = new Stack();



    public static void main(String[] args) {




        //============================ ADDER No Lock =========================================
        for ( int numberOfThreads : threadNumbersWithoutLock){
            System.out.println("Running for #Threads(" + numberOfThreads + ")");
            numberOfAdderThreads = numberOfThreads ;
            ArrayList<Integer> allSummations = new ArrayList<>();
            ArrayList<Long> eachThreadTimes = new ArrayList<>() ;
            AdderNoLock.frequencyMonitorInitializer(numberOfAdderThreads);
            System.out.println("=========== Adder Without LOCK begins.===============");
            for ( int i = 0 ; i < adderLoopCounter ; i++){
                AdderNoLock.initializeThreadPool(numberOfAdderThreads);
                Long time = ThreadTools.AdderNoLockThread();
                System.out.println("("+(i+1)+"):Summation : " + AdderNoLock.getSUMMATION() + "   Time : " + time);
                eachThreadTimes.add(time);
                allSummations.add(AdderNoLock.getSUMMATION());
                AdderNoLock.frequencyIncrement(AdderNoLock.getSUMMATION());
                AdderNoLock.terminateThreadPool();
            }
            System.out.println("====== Results ======");
            System.out.println("Average Time : " + ThreadTools.caluclateAverageTime(eachThreadTimes));
            AdderNoLock.printFrequency();
            eachThreadTimes.clear();
            System.out.println("***");
        }
        //========================== ADDER With Lock ============================================
        numberOfAdderThreads = threadNumbersWithLock;
        ArrayList<Integer> allSummations = new ArrayList<>();
        ArrayList<Long> eachThreadTimes = new ArrayList<>() ;
        AdderLock.frequencyMonitorInitializer(numberOfAdderThreads);
        System.out.println("=========== Adder With LOCK begins.===============");
        for ( int i = 0 ; i < adderLoopCounter ; i++){
            AdderLock.initializeThreadPool(numberOfAdderThreads);
            Long time = ThreadTools.AdderLockThread();
            System.out.println("("+(i+1)+"):Summation : " + AdderLock.getSUMMATION() + "   Time : " + time);
            eachThreadTimes.add(time);
            allSummations.add(AdderLock.getSUMMATION());
            AdderLock.frequencyIncrement(AdderLock.getSUMMATION());
            AdderLock.terminateThreadPool();
        }
        System.out.println("====== Results ======");
        System.out.println("Average Time : " + ThreadTools.caluclateAverageTime(eachThreadTimes));
        AdderLock.printFrequency();
        eachThreadTimes.clear();
        System.out.println("***");


// ==================== CONSUMER & PRODUCER ==============================
        System.out.println("=========== Consumer Producer Part.===============");
        Consumer.createThreadPool(consumerNumbers);
        Producer.createThreadPool(producerNumbers);

        //ThreadTools.producerRun();
        //System.out.println("Producer Produced !");
        //ThreadTools.consumerRun();
        //System.out.println("Consumer Consumed !");
        ThreadTools.runThreads();

    }



}
