package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;

public class ThreadClass extends Thread {
    //Create int array with wanted size
    public static final int MILLION = 1000000;
    public static int THREAD_NUMBER ;
    private  int chunkSize , startIndex , endIndex , jumpAmount;

    //Vector Values
    public static int ARRAY_SIZE ;
    public static int HORIZONTAL_VECTOR[] ;
    public static int VERTICAL_VECTOR[] ;

    //Result :
    public static long  SUMMATION = 0;

    //Create a Thread Pool
    public static int idCounter = 0;
    public static ArrayList<ThreadClass> threadPool = new ArrayList<>();
    private int id = 0;
    private int whichIndex = id;


    public ThreadClass(int id) {
        this.id = id;
    }

    public static void initializeArrays(int arraySize){
        ARRAY_SIZE = arraySize ;
        ThreadClass.HORIZONTAL_VECTOR = new int[ARRAY_SIZE];
        ThreadClass.VERTICAL_VECTOR = new int[ARRAY_SIZE];
        randomizeArrays();
    }


    public static void createThreadPool(int THREAD_NUMBER) {
        ThreadClass.THREAD_NUMBER = THREAD_NUMBER;
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threadPool.add(new ThreadClass(idCounter++));
        }

    }

    //Terminate Thread Pool
    public static void terminateThreadPool() {
        ThreadClass.threadPool.clear();
        ThreadClass.idCounter =  0 ;
        ThreadClass.SUMMATION = 0 ;
    }

    private static void randomizeArrays(){
        // Randomize The vectors
        Random rnd = new Random() ;
        for ( int i = 0 ; i < ThreadClass.ARRAY_SIZE ; i++){
            ThreadClass.HORIZONTAL_VECTOR[i] = rnd.nextInt(3) + 1 ;
            ThreadClass.VERTICAL_VECTOR[i] = rnd.nextInt(3) + 1 ;
        }
    }

    //Main Core :
    @Override
    public void run() {
        //super.run();
        if ( ARRAY_SIZE > THREAD_NUMBER ){
            chunkSize = ARRAY_SIZE/THREAD_NUMBER ;
            jumpAmount = 1 ;
            endIndex = (chunkSize+id) ;
            startIndex = id*chunkSize ;
        }
        else {
            startIndex = id ;
            jumpAmount = THREAD_NUMBER ;
            endIndex = ARRAY_SIZE ;
        }

        for (whichIndex = startIndex  ; (whichIndex) <  endIndex ; whichIndex += jumpAmount) {
            // I didn't lock a semaphore on this summation because you said this part is not our main goal
            ThreadClass.SUMMATION += HORIZONTAL_VECTOR[whichIndex] * VERTICAL_VECTOR[whichIndex] ;

        }
    }
}

