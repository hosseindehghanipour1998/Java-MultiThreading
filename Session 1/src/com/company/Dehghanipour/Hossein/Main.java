package com.company.Dehghanipour.Hossein;


import java.util.ArrayList;

public class Main {

    public static long runThreads(int numberOfThreads){
        long startTime = System.nanoTime();
        PlusPlus.createThreadPool(numberOfThreads);

        for ( PlusPlus p : PlusPlus.threadPool){
            p.start();
        }
        try {
            for ( PlusPlus p :PlusPlus.threadPool){
                p.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //System.out.println(totalTime/1000000);
        PlusPlus.clearThreadPool();
        return (totalTime/1000000) ;
    }

    public static void main(String[] args) {
        ArrayList<Long> eachBatchTimes = new ArrayList<>() ;
        ArrayList<ArrayList<Long>> eachTaskTimes = new ArrayList<>() ;
        int loopCounter = 10 ;
        int NUMBER_OF_THREADS = 0 ;
        long calculatedTime = 0 ;


        //For <1> Thread
        NUMBER_OF_THREADS = 1 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();

        //For <2> Threads
        NUMBER_OF_THREADS = 2 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <3> Threads
        NUMBER_OF_THREADS = 3 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <4> Threads
        NUMBER_OF_THREADS = 4 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <5> Threads
        NUMBER_OF_THREADS = 5 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <6> Threads
        NUMBER_OF_THREADS = 6 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <7> Threads
        NUMBER_OF_THREADS = 7 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <8> Threads
        NUMBER_OF_THREADS = 8 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <10> Threads
        NUMBER_OF_THREADS = 10 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <32> Threads
        NUMBER_OF_THREADS = 32 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //For <64> Thread
        NUMBER_OF_THREADS = 64 ;
        for ( int i = 0 ; i < loopCounter ; i++){
            calculatedTime = runThreads(NUMBER_OF_THREADS);
            eachBatchTimes.add(calculatedTime);
        }

        eachTaskTimes.add(eachBatchTimes);
        eachBatchTimes.clear();


        //Print The Table :
        int index = 0 ;
        for ( ArrayList<Long> AL : eachTaskTimes ){
            System.out.print("Thread (" + (index++) + "):   ");
            for (Long time : AL){
                System.out.print(time + "   ");
            }
            System.out.println("\n");
        }





    }
}

