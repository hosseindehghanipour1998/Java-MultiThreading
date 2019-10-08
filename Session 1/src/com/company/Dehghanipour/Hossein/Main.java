package com.company.Dehghanipour.Hossein;

public class Main {
    static int NUMBER_OF_THREADS = 10 ;
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        PlusPlus.createThreadPool(NUMBER_OF_THREADS);

        for ( PlusPlus p : PlusPlus.threadPool){
            p.start();
        }

        try {
            for ( PlusPlus p :PlusPlus.threadPool){
                p.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000000);
        System.out.println();
    }

}

