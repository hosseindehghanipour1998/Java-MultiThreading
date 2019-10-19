package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Collections;
public class Main {
    //**Test
    private static final String DEBUG_FILE = "bugDetectionFile.txt";
    private static  int failsCounter = 0 ;
    private static  int matchesCounter = 0 ;
    //**

    public static float calculateAverage(ArrayList<Long> caulculatedTimes ){
        int sum = 0 ;
        for ( Long time : caulculatedTimes) {
            sum += time ;
        }
        return (sum/caulculatedTimes.size());
    }

    public static long runThreads(int numberOfThreads, long realSummation , ArrayList<Long> summations){
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

        //**Test
        if( realSummation == ThreadClass.SUMMATION){
            IODevice.writePerLine("STATUS : MATCH ",DEBUG_FILE);
            matchesCounter++ ;
        }
        else{
            failsCounter++ ;
            IODevice.writePerLine("STATUS : ...................... < FAIL >..................... ",DEBUG_FILE);
            IODevice.writePerLine("REAL AMOUNT  : " + realSummation,DEBUG_FILE);
            IODevice.writePerLine("SUMMATION AMOUNT : " + ThreadClass.SUMMATION ,DEBUG_FILE);
        }
        IODevice.writePerLine("Initial Size : " + ThreadClass.threadPool.size(),DEBUG_FILE);
        IODevice.writePerLine("Summation Amount : "  + ThreadClass.SUMMATION,DEBUG_FILE);
        //**

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

    public static long calculateMul(int[] v1 , int[] v2){
        long sum = 0 ;

        for ( int i = 0 ; i < v1.length  ;i++){
            sum += v1[i] * v2[i] ;
        }
        return sum ;
    }

    public static void main(String[] args) {
        IODevice.deletePredefinedFile(DEBUG_FILE);
        //Initializations
        ArrayList<ArrayList<ArrayList<Long>>> allThreadsTimes = new ArrayList<>() ;
        int[] threadNumbers =  {1,2,5,10,20,50,100,150};
        int[] vectorSizes = {100 , 1000 , 10000 , 100000}; // What TA asked Us to do
        //For vector size more that 100K , overflow will happen. You can add the number above if you want.

        ArrayList<Long> summations =  new ArrayList<>() ;
        final int LOOP_COUNTER = 10 ;
        long calculatedTime = 0 ;
        System.out.println("Let's do this ;) ");
        //CORE :
        for ( int matrixSize : vectorSizes){ // Vector

            ArrayList<ArrayList<Long>> eachTaskTimes = new ArrayList<>() ;
            //Initialize the Vector with wanted lenghts
            ThreadClass.ARRAY_SIZE = matrixSize ;
            ThreadClass.initializeArrays(matrixSize);
            //**Test**
            Long realAmountOfMultiplying = calculateMul(ThreadClass.HORIZONTAL_VECTOR , ThreadClass.VERTICAL_VECTOR);
            //**
            //Core
            for ( int threadNumber : threadNumbers){//Threads
                //**Test**
                IODevice.writePerLine("NUMBER_OF_THREADS (" + threadNumber + ")",DEBUG_FILE);
                //**
                ArrayList<Long> eachBatchTimes = new ArrayList<>() ;
                ThreadClass.THREAD_NUMBER = threadNumber ;
                for ( int i = 0 ; i < LOOP_COUNTER ; i++){//Loop Counter
                    //**Test**
                    IODevice.writePerLine("LOOP_COUNTER (" + i + ")",DEBUG_FILE);
                    //**
                    calculatedTime = runThreads(threadNumber,realAmountOfMultiplying,summations) ;
                    eachBatchTimes.add(calculatedTime) ;
                }
                eachTaskTimes.add(eachBatchTimes) ;
            }
            //**Test**
            IODevice.writePerLine("=================================",DEBUG_FILE);
            //**
            allThreadsTimes.add(eachTaskTimes);
        }

        printTheTable(allThreadsTimes,threadNumbers,vectorSizes,summations) ;
        IODevice ioDevice = new IODevice();
        ioDevice.writeFile("Results.txt",allThreadsTimes,threadNumbers,vectorSizes);
        System.out.println("End Of Program ... :))  ");
        StringBuilder builder = new StringBuilder();
        builder.append("Fails : " + failsCounter + "\n" +"Matches : " + matchesCounter + "\n" + "Total : " + (matchesCounter + failsCounter) );
        IODevice.writePerLine(builder.toString(),DEBUG_FILE);
        System.out.println( "Accuracy : " + ((float)matchesCounter/(matchesCounter+failsCounter)) * 100 + " %");


    }


}

