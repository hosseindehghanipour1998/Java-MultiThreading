package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;

public class ThreadClass extends Thread {
    //Create int array with wanted size
    public static int MILLION = 1000000;
    public static int THREAD_NUMBER ;
    public int sum = 0 ;

    //Vector Values
    public static int ARRAY_SIZE ;
    public static int HORIZONTAL_VECTOR[] ;
    public static int VERTICAL_VECTOR[] ;

    //Result :
    public static int SUMMATION = 0;

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
        randomizeArrays(arraySize);
    }

    public ThreadClass() {
    }

    public static void createThreadPool(int THREAD_NUMBER) {
        ThreadClass.THREAD_NUMBER = THREAD_NUMBER;
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threadPool.add(new ThreadClass(idCounter++));
        }

    }

    //Terminate Thread Pool
    public static void clearThreadPool() {
        ThreadClass.threadPool.clear();
    }

    private void vectorMultiply(int index) {
        int result = HORIZONTAL_VECTOR[index] * VERTICAL_VECTOR[index];
        SUMMATION += result ;
        sum += result ;
        //System.out.println(result + " = " +HORIZONTAL_VECTOR[index] + " * " + VERTICAL_VECTOR[index]);
        //System.out.println(SUMMATION);
    }


    private static void randomizeArrays(int vectorSize){
        // Randomize The vectors
        Random rnd = new Random() ;
        for ( int i = 0 ; i < ThreadClass.ARRAY_SIZE ; i++){
            ThreadClass.HORIZONTAL_VECTOR[i] = rnd.nextInt(100) + 1 ;
            ThreadClass.VERTICAL_VECTOR[i] = rnd.nextInt(100) + 1 ;
        }
    }
    //Main Core :
    @Override
    public void run() {
        //super.run();
        int quota = ARRAY_SIZE / THREAD_NUMBER;
        for (whichIndex = id; (whichIndex < ARRAY_SIZE); whichIndex += THREAD_NUMBER) {
            vectorMultiply(whichIndex);
        }
    }

    public static void printArray(int arr[]){
        for ( Integer i : arr){
            System.out.print(i + "|");
        }
        System.out.println();
    }
}

