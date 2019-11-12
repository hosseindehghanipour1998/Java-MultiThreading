package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock ;

public class Main {


    public static ArrayList<ReentrantLock> spoons = new ArrayList<>();
    private static final int NUMBER_OF_PHLIOSOPHERS = 5 ;
    private static final int NUMBER_OF_SPOONS = 5 ;
    public static void main(String[] args) {

        Philosopher.initializeThreadPool(NUMBER_OF_PHLIOSOPHERS);
        Main.initializeSpoons();

        for ( Philosopher p : Philosopher.threadPool){
            p.start();
        }

    }

    private static void initializeSpoons(){
        for(int i = 0 ; i < NUMBER_OF_SPOONS ; i++){
            spoons.add(new ReentrantLock());
        }
    }

}
