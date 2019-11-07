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
    public static Semaphore producerSemaphore = new Semaphore(4) ;
    public static Semaphore consumerSemaphore = new Semaphore(0) ;
    public static ReentrantLock locker = new ReentrantLock() ;
    public static int stackSize = 3 ;
    public static Stack bowls = new Stack();
    public static int loopCounter = 10 ;
/*
    public static float calcualteAvg(ArrayList<Long> times){
        int sum = 0 ;
        for(int i = 0 ; i < times.size() ; i++){
            sum += times.get(i);
        }
        return (sum/times.size());
    }

 */

    public static void main(String[] args) {
        // write your code here
/*
        int arraySize = 1000000 ;
        int threadsNumbers = 2 ;
        ArrayList<Long> eachThreadTimes = new ArrayList<>() ;
        int adderLoopCounter = 10 ;

        //Initializations
        System.out.println("Program Started ...");
*/

/*

        //============================ ADDER =========================================
        //Adder
        IODevice.deletePredefinedFile(ADDER_FILE_NAME);
        ArrayList<Integer> allSummations = new ArrayList<>();
        System.out.println("=========== Adder Part begins.===============");
        Adder.setBasicInfo(2,adderLoopCounter);
        Adder.initializeFrequencyKeeper();
        for ( int i = 0 ; i < adderLoopCounter ; i++){
            Adder.initializeThreadPool();
            Long time = ThreadTools.AdderThread();
            System.out.println("("+(i+1)+"):Summation : " + Adder.getSUMMATION() + "   Time : " + time);
            Adder.frequencyKeeper[Adder.getSUMMATION()] ++ ;
            eachThreadTimes.add(time);
            allSummations.add(Adder.getSUMMATION());
            Adder.terminateThreadPool();
        }
        System.out.println("====== Results ======");
        //ThreadTools.printAdderTable();
        //IODevice.writeFileAdder(ADDER_FILE_NAME,allSummations);
        System.out.println(calcualteAvg((eachThreadTimes)));
        eachThreadTimes.clear();


        //End of The Program
        System.out.println("Written In File ... ");
        System.out.println("Program Finished ... ");
         */


        int consumerNumbers = 5 ;
        int producerNumbers = 5 ;
        int stackCapacity = 3 ;

        Consumer.createThreadPool(consumerNumbers);
        Producer.createThreadPool(producerNumbers);

        ThreadTools.producerRun();
        System.out.println("Producer Produced");
        ThreadTools.consumerRun();
        System.out.println("Consumer Done !");

    }





}
