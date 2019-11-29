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
    public static MySemaphore producerSemaphore = new MySemaphore(producerSemaphorePermits) ;
    public static MySemaphore consumerSemaphore = new MySemaphore(consumerSemaphorePermits) ;
    public static Stack bowls = new Stack();

    public static void main(String[] args) {

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
