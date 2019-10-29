package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadTools  {

    public static long multiplicationThread(){
        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( MatrixMultiplier mp : MatrixMultiplier.getThreadPool()){
            mp.start();
        }
        try {
            for ( MatrixMultiplier mp :MatrixMultiplier.getThreadPool()){
                mp.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //Terminate Thread Pool.
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

    public static float calculateAverage(ArrayList<Long> caulculatedTimes ){
        int sum = 0 ;
        for ( Long time : caulculatedTimes) {
            sum += time ;
        }
        return (sum/caulculatedTimes.size());
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

}
