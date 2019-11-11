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
    public static final String FILE_NAME = "Results.txt";
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
            //Start WriteInFile
            System.out.println("Running for #Threads(" + numberOfThreads + ")");
            IODevice.writePerLine(FILE_NAME , "Running for #Threads(" + numberOfThreads + ")");
            //End of WriteInFile
            numberOfAdderThreads = numberOfThreads ;
            ArrayList<Integer> allSummations = new ArrayList<>();
            ArrayList<Long> eachThreadTimes = new ArrayList<>() ;
            AdderNoLock.frequencyMonitorInitializer(numberOfAdderThreads);
            //write in file
            System.out.println("=========== Adder Without LOCK begins.===============");
            IODevice.writePerLine(FILE_NAME , "=========== Adder Without LOCK begins.===============");
            //
            for ( int i = 0 ; i < adderLoopCounter ; i++){
                AdderNoLock.initializeThreadPool(numberOfAdderThreads);
                Long time = ThreadTools.AdderNoLockThread();
                //write in file
                System.out.println("("+(i+1)+"):Summation : " + AdderNoLock.getSUMMATION() + "   Time : " + time);
                IODevice.writePerLine(FILE_NAME , "("+(i+1)+"):Summation : " + AdderNoLock.getSUMMATION() + "   Time : " + time);
                //
                eachThreadTimes.add(time);
                allSummations.add(AdderNoLock.getSUMMATION());
                AdderNoLock.frequencyIncrement(AdderNoLock.getSUMMATION());
                AdderNoLock.terminateThreadPool();
            }
            //
            System.out.println("====== Results ======");
            IODevice.writePerLine(FILE_NAME , "====== Results ======");
            System.out.println("Average Time : " + ThreadTools.caluclateAverageTime(eachThreadTimes));
            IODevice.writePerLine(FILE_NAME , "Average Time : " + ThreadTools.caluclateAverageTime(eachThreadTimes));
            //
            AdderNoLock.printFrequency();
            eachThreadTimes.clear();
            //
            System.out.println("***");
            IODevice.writePerLine(FILE_NAME , "***");
            //
        }
        //========================== ADDER With Lock ============================================
        numberOfAdderThreads = threadNumbersWithLock;
        ArrayList<Integer> allSummations = new ArrayList<>();
        ArrayList<Long> eachThreadTimes = new ArrayList<>() ;
        AdderLock.frequencyMonitorInitializer(numberOfAdderThreads);
        //
        System.out.println("=========== Adder With LOCK begins.===============");
        IODevice.writePerLine(FILE_NAME , "=========== Adder With LOCK begins.===============");
        //
        for ( int i = 0 ; i < adderLoopCounter ; i++){
            AdderLock.initializeThreadPool(numberOfAdderThreads);
            Long time = ThreadTools.AdderLockThread();
            //
            System.out.println("("+(i+1)+"):Summation : " + AdderLock.getSUMMATION() + "   Time : " + time);
            IODevice.writePerLine(FILE_NAME , "("+(i+1)+"):Summation : " + AdderLock.getSUMMATION() + "   Time : " + time);
            //
            eachThreadTimes.add(time);
            allSummations.add(AdderLock.getSUMMATION());
            AdderLock.frequencyIncrement(AdderLock.getSUMMATION());
            AdderLock.terminateThreadPool();
        }
        //
        System.out.println("====== Results ======");
        IODevice.writePerLine(FILE_NAME , "====== Results ======");
        System.out.println("Average Time : " + ThreadTools.caluclateAverageTime(eachThreadTimes));
        IODevice.writePerLine(FILE_NAME , "Average Time : " + ThreadTools.caluclateAverageTime(eachThreadTimes));
        //
        AdderLock.printFrequency();
        eachThreadTimes.clear();
        //
        System.out.println("***");
        IODevice.writePerLine(FILE_NAME , "***");
        //

        // ==================== CONSUMER & PRODUCER ==============================
        //
        System.out.println("=========== Consumer Producer Part.===============");
        IODevice.writePerLine(FILE_NAME , "=========== Consumer Producer Part.===============");
        //
        Consumer.createThreadPool(consumerNumbers);
        Producer.createThreadPool(producerNumbers);
        ThreadTools.runThreads();

    }



}
