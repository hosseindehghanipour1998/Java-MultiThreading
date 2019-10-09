package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlusPlus extends Thread {
    private static int ARRAY_SIZE = 90000000;
    public static int intArray[] = new int[ARRAY_SIZE];


    public static int idCounter = 1 ;
    //1,2,3,4,5,6,7,8,32,64,
    public static ArrayList<PlusPlus> threadPool = new ArrayList<>() ;
    private int id = 0 ;
    private int whichIndex = id ;


    public PlusPlus(int id){
        this.id = id ;
    }
    public PlusPlus(){}

    public static void createThreadPool(int THREAD_NUMBER){
        for ( int i = 0 ; i < THREAD_NUMBER ; i++){
            threadPool.add(new PlusPlus(idCounter++));
        }

    }

    public static void clearThreadPool(){
        PlusPlus.threadPool.clear();
    }
    @Override
    public void run() {
        //super.run();
        Random rnd = new Random();
        for(whichIndex = id ; whichIndex < ARRAY_SIZE ; whichIndex += 32){
            intArray[whichIndex] = (int)(ThreadLocalRandom.current().nextInt()) ;
            //int a = rnd.nextInt(10000);
            //intArray[whichIndex] = a ;
        }
    }
}