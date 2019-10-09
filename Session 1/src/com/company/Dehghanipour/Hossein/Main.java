package com.company.Dehghanipour.Hossein;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static long runThreads(int numberOfThreads){
        final int MILLION = 1000000;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads
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
        //Terminate Thread Pool.
        PlusPlus.clearThreadPool();
        return (totalTime/MILLION) ;
    }

    public static void main(String[] args) {
        //Initializations
        ArrayList<ArrayList<Long>> eachTaskTimes = new ArrayList<>() ;
        int[] threadNumbers = {1,2,3,4,5,6,7,8,10,32,64} ;
        int loopCounter = 10 ;
        long calculatedTime = 0 ;

        for ( int i = 0 ; i < threadNumbers.length ; i++ ){
            ArrayList<Long> eachBatchTimes = new ArrayList<>() ;
            for ( int  j = 0 ; j < loopCounter ; j++){
                calculatedTime = runThreads(threadNumbers[i]);
                eachBatchTimes.add(calculatedTime) ;
            }
            eachTaskTimes.add(eachBatchTimes) ;
        }

        //Print The Table :
        int index = 0 ;
        ArrayList<Long> allMinimums = new ArrayList<>() ;
        for ( ArrayList<Long> AL : eachTaskTimes ){
            System.out.print("Threads (" + (threadNumbers[index++]) + "):\t\t");
            for (Long time : AL){
                System.out.print(time + " ms\t\t");
            }
            System.out.print("Max: " + Collections.max(AL) + " ms\t Min : " + Collections.min(AL));
            allMinimums.add(Collections.min(AL)) ;
            System.out.println("\n");
        }
        System.out.println("Min :\t" + Collections.min(allMinimums) + " ms | Number Of Threads :  ( " + threadNumbers[findMinThread(allMinimums)] +" )"  );

        //Write the table in a file
        IODevice ioDevice = new IODevice();
        ioDevice.writeFile("calculatedTimes.txt" , eachTaskTimes , threadNumbers);
    }


    public static int findMinThread(ArrayList<Long> allMinimums){
        Long min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }
}

