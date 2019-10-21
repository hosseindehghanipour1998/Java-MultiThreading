package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Collections;
public class Main {

    public static float calculateAverage(ArrayList<Long> caulculatedTimes ){
        int sum = 0 ;
        for ( Long time : caulculatedTimes) {
            sum += time ;
        }
        return (sum/caulculatedTimes.size());
    }

    public static long runThreads(int numberOfThreads,  ArrayList<Long> summations){
        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads
        ThreadClass.createThreadPool(numberOfThreads);

        for ( ThreadClass p : ThreadClass.threadPool){
            p.start();
        }
        try {
            for ( ThreadClass p :ThreadClass.threadPool){
                p.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        summations.add(ThreadClass.SUMMATION);
        //Terminate Thread Pool.
        ThreadClass.terminateThreadPool();
        return (totalTime/THOUSAND) ;
    }

    public static int findMinThreadIndex(ArrayList<Long> allMinimums){
        Long min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }

    public static int findMinAvgTimeIndex(ArrayList<Float> allMinimums){
        Float min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }

    public static void printTheTable(ArrayList<ArrayList<ArrayList<Long>>> allThreadsTimes  , int[] threadNumbers , int[] arraySizes , ArrayList<Long> summations ){
        int arrayIndex = 0 ;
        int threadIndex = 0 ;

        ArrayList<Long> allMinimumCalculatedTimes = new ArrayList<>() ;
        ArrayList<Float> allAverageTimes = new ArrayList<>() ;
        for (ArrayList<ArrayList<Long>> allThreadTime : allThreadsTimes){

            System.out.println("================= (" + arraySizes[arrayIndex] +")=====================");
            System.out.println("Summation : " + summations.get(arrayIndex++));
            threadIndex = 0 ;
            for ( ArrayList<Long> AL : allThreadTime ){
                System.out.print("Threads (" + (threadNumbers[threadIndex++]) + "):\t\t") ;
                for ( Long time : AL ){
                    System.out.print(time + " us\t\t");
                }
                float avgTime = calculateAverage(AL);
                allAverageTimes.add(avgTime) ;
                System.out.print("Max: " + Collections.max(AL) + " us\t Min : " + Collections.min(AL) + " us\t Avg : " + avgTime);
                allMinimumCalculatedTimes.add(Collections.min(AL)) ;
                System.out.println("\n");
            }
            //Edit here -> Make it to show for each <vector>
            //System.out.println("Min :\t" + Collections.min(allMinimumCalculatedTimes) + " ms | Number Of Threads :  ( " + threadNumbers[findMinThread(allMinimumCalculatedTimes)] +" )"  );
            //System.out.println("Min Average:\t" + Collections.min(allAverageTimes)  + " ms | Number Of Threads :  ( " + threadNumbers[findMinAvgTime(allAverageTimes)] +" )"  );

        }
    }

    public static void main(String[] args) {
        //Initializations
        final int MILLION = 1000000 ;
        ArrayList<ArrayList<ArrayList<Long>>> allThreadsTimes = new ArrayList<>() ;
        int[] threadNumbers =  {1,2,5,10,20,50,100,150};
        int[] vectorSizes = {100 , 1000 , 10000 , 100000 , 1000000 , 400 * MILLION}; // What TA asked Us to do

        ArrayList<Long> summations =  new ArrayList<>() ;
        final int LOOP_COUNTER = 10 ;
        long calculatedTime = 0 ;
        System.out.println("Let's do this ;) ");
        System.out.println("Program started ... please Wait !");
        //CORE :
        for ( int matrixSize : vectorSizes){ // Vector
            ArrayList<ArrayList<Long>> eachTaskTimes = new ArrayList<>() ;
            //Initialize the Vector with wanted lengths
            ThreadClass.ARRAY_SIZE = matrixSize ;
            ThreadClass.initializeArrays(matrixSize);
            //Core
            for ( int threadNumber : threadNumbers){//Threads
                ArrayList<Long> eachBatchTimes = new ArrayList<>() ;
                ThreadClass.THREAD_NUMBER = threadNumber ;
                for ( int i = 0 ; i < LOOP_COUNTER ; i++){//Loop Counter
                    calculatedTime = runThreads(threadNumber,summations) ;
                    eachBatchTimes.add(calculatedTime) ;
                }
                eachTaskTimes.add(eachBatchTimes) ;
            }
            allThreadsTimes.add(eachTaskTimes);
        }
        //printTheTable(allThreadsTimes,threadNumbers,vectorSizes,summations) ;
        IODevice ioDevice = new IODevice();
        ioDevice.writeFile("Results.txt",allThreadsTimes,threadNumbers,vectorSizes);
        System.out.println("End Of Program ... :))  ");
        System.out.println("Please Check \"Results.txt\" which is created by running this program.");
    }


}

