package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;

public class Philosopher extends Thread {

    public static ArrayList<Philosopher> threadPool = new ArrayList<>() ;
    private static int idCounter = 1 ;
    private int id ;


    public static void initializeThreadPool(int threadPoolSize){

        for ( int i = 0 ; i < threadPoolSize ; i++){
            threadPool.add(new Philosopher(idCounter++));
        }

    }

    public Philosopher(int newId){
        this.id = newId ;
    }

    @Override
    public void run() {
        super.run();
        int spoon1 = id - 1 ;
        int spoon2 = id ;
        if ( Main.spoons[spoon1].tryLock()){
            if( Main.spoons[spoon2].tryLock()) {
                System.out.println("ID : " + id + " | Is eating.");
                Main.spoons[spoon1].unlock();
                Main.spoons[spoon2].unlock();

                try {
                    Thread.sleep(10);
                }catch (Exception e){

                }
            }
        }
        else{
            Main.spoons[spoon1].unlock();
            System.out.println("ID : " + id + " | Unsuccessful Attempt to eat.");
        }
    }
}
