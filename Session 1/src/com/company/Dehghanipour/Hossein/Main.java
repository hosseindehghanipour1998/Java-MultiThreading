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
        ArrayList<ArrayList<Long>> eachTaskTimes = new ArrayList<>() ;
        int[] threadAmounts = {1,2,3,4,5,6,7,8,10,32,64} ;
        int loopCounter = 10 ;
        long calculatedTime = 0 ;


        for ( int i = 0 ; i < threadAmounts.length ; i++ ){
            ArrayList<Long> eachBatchTimes = new ArrayList<>() ;
            for ( int  j = 0 ; j < loopCounter ; j++){
                calculatedTime = runThreads(threadAmounts[i]);
                eachBatchTimes.add(calculatedTime) ;
            }
            eachTaskTimes.add(eachBatchTimes) ;

        }

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

