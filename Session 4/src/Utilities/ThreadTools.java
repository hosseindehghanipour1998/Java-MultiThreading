package Utilities;

import Utilities.IODevice;
import com.company.Dehghanipour.Hossein.Main;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadTools <T> {

    public static long threadRun(ThreadObject threadObject){
        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( ThreadObject mp : threadObject.getThreadPool()){
            mp.start();
        }
        try {
            for ( ThreadObject mp :threadObject.getThreadPool()){
                mp.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //Terminate Thread Pool.
        return (totalTime/MILLION) ;
    }

    public static long AdderThread(ThreadObject to){

        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( ThreadObject A : ThreadObject.getThreadPool()){
            A.start();
        }
        try {
            for ( ThreadObject A :ThreadObject.getThreadPool()){
                A.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return (totalTime/MILLION) ;

    }

    public static int findMinThreadIndex(ArrayList<Long> allMinimums){
        Long min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }


    public static void printTheMultiplierTable(ArrayList<Long> times , int[] threads ){

        for(int i = 0 ; i < threads.length ; i++){
            System.out.println("Thread(" + threads[i]+") : "+ times.get(i));
        }
        int minIndex = findMinThreadIndex(times);

        System.out.println("Min : " + times.get(minIndex) + " ns\t ->\t Thread(" + threads[minIndex]+")");

    }

    public static void printAdderTable(){

    }


}
