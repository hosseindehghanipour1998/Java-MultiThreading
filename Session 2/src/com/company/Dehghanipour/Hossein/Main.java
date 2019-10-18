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

    public static long runThreads(int numberOfThreads , ArrayList<Integer> summations){
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
        System.out.println("Initial Size : " + ThreadClass.threadPool.size());
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        summations.add(ThreadClass.SUMMATION);
        System.out.println("Summation Amount : "  + ThreadClass.SUMMATION);
        //Terminate Thread Pool.
        ThreadClass.terminateThreadPool();

        return (totalTime/THOUSAND) ;
    }

    public static int findMinThread(ArrayList<Long> allMinimums){
        Long min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }

    public static int findMinAvgTime(ArrayList<Float> allMinimums){
        Float min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }

    public static void printTheTable(ArrayList<ArrayList<ArrayList<Long>>> allThreadsTimes  , int[] threadNumbers , int[] arraySizes , ArrayList<Integer> summations ){
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
                    System.out.print(time + " ms\t\t");
                }
                float avgTime = calculateAverage(AL);
                allAverageTimes.add(avgTime) ;
                System.out.print("Max: " + Collections.max(AL) + " ms\t Min : " + Collections.min(AL) + " ms\t Avg : " + avgTime);
                allMinimumCalculatedTimes.add(Collections.min(AL)) ;
                System.out.println("\n");
            }
            //System.out.println("Min :\t" + Collections.min(allMinimumCalculatedTimes) + " ms | Number Of Threads :  ( " + threadNumbers[findMinThread(allMinimumCalculatedTimes)] +" )"  );
            //System.out.println("Min Average:\t" + Collections.min(allAverageTimes)  + " ms | Number Of Threads :  ( " + threadNumbers[findMinAvgTime(allAverageTimes)] +" )"  );

        }
    }

    public static int calculateMul(int[] v1 , int[] v2){
        int sum = 0 ;

        for ( int i = 0 ; i < v1.length  ;i++){
            sum += v1[i] * v2[i] ;
        }
        return sum ;
    }

    public static void main(String[] args) {

        //Initializations
        ArrayList<ArrayList<ArrayList<Long>>> allThreadsTimes = new ArrayList<>() ;
        ArrayList<Integer> summations = new ArrayList<>() ;
        int[] threadNumbers =  {1,2,5,10,20,50,100,150};
        //int[] vectorSizes = {100 , 1000 , 10000 , 100000 , 1000000}; // What TA asked Us to do
        int[] vectorSizes = {5};

        final int LOOP_COUNTER = 1 ;
        long calculatedTime = 0 ;

        //CORE :
        for ( int matrixSize : vectorSizes){
            System.out.println("================ ARRAY_SIZE (" + matrixSize + " ) =======================");
            ArrayList<ArrayList<Long>> eachTaskTimes = new ArrayList<>() ;
            //Initialize the Vector with wanted lenghts
            ThreadClass.ARRAY_SIZE = matrixSize ;
            ThreadClass.initializeArrays(matrixSize);
            //Test
            System.out.println("Real MUL : " + calculateMul(ThreadClass.HORIZONTAL_VECTOR , ThreadClass.VERTICAL_VECTOR));
            ThreadClass.printArray(ThreadClass.HORIZONTAL_VECTOR);
            ThreadClass.printArray(ThreadClass.VERTICAL_VECTOR);
            //Core
            for ( int threadNumber : threadNumbers){
                System.out.println("+++++ NUMBER_OF_THREADS (" + threadNumber + " ) ++++++");
                ArrayList<Long> eachBatchTimes = new ArrayList<>() ;
                ThreadClass.THREAD_NUMBER = threadNumber ;
                for ( int i = 0 ; i < LOOP_COUNTER ; i++){
                    System.out.println("///// LOPP_COUNTER (" + i + " ) /////");
                    calculatedTime = runThreads(threadNumber,summations) ;
                    eachBatchTimes.add(calculatedTime) ;
                }
                eachTaskTimes.add(eachBatchTimes) ;
            }
            System.out.println("=================================");
            allThreadsTimes.add(eachTaskTimes);
        }

        //printTheTable(allThreadsTimes,threadNumbers,vectorSizes,summations) ;
        System.out.println("End Of Program ... ");


    }


}

