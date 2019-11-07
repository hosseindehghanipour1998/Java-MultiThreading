package com.company.Dehghanipour.Hossein;

import javafx.scene.effect.Bloom;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    //==============CONTROL PANEL ==========================
    private static final int producerSemaphorePermits = 4 ;
    private static final int consumerSemaphorePermits = 0 ;
    public static int stackSize = 3 ;
    public static final int consumer_producerLoopCounter = 10 ;
    private static final int consumerNumbers = 5 ;
    private static final int producerNumbers = 5 ;

    public static final int adderLoopCounter = 100 ;
    private static final int numberOfAdderThreads = 2 ;

    //============ PART II Vars ============================
    public static ReentrantLock locker = new ReentrantLock() ;
    public static Semaphore producerSemaphore = new Semaphore(producerSemaphorePermits) ;
    public static Semaphore consumerSemaphore = new Semaphore(consumerSemaphorePermits) ;
    public static Stack bowls = new Stack();



    public static void main(String[] args) {
        // write your code here
/*
        int arraySize = 1000000 ;



        //Initializations
        System.out.println("Program Started ...");
*/



        //============================ ADDER =========================================

        ArrayList<Integer> allSummations = new ArrayList<>();
        ArrayList<Long> eachThreadTimes = new ArrayList<>() ;

        System.out.println("=========== Adder Part begins.===============");
        for ( int i = 0 ; i < adderLoopCounter ; i++){
            Adder.initializeThreadPool(numberOfAdderThreads);
            Long time = ThreadTools.AdderThread();
            System.out.println("("+(i+1)+"):Summation : " + Adder.getSUMMATION() + "   Time : " + time);
            eachThreadTimes.add(time);
            allSummations.add(Adder.getSUMMATION());
            Adder.terminateThreadPool();
        }
        System.out.println("====== Results ======");
        eachThreadTimes.clear();


// ==================== CONSUMER & PRODUCER ==============================

        Consumer.createThreadPool(consumerNumbers);
        Producer.createThreadPool(producerNumbers);

        ThreadTools.producerRun();
        System.out.println("Producer Produced !");
        ThreadTools.consumerRun();
        System.out.println("Consumer Consumed !");

    }





}
