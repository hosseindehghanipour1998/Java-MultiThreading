package com.company.Dehghanipour.Hossein;



public class Main {

    public long runThreads(int numberOfThreads){
        long startTime = System.nanoTime();
        PlusPlus.createThreadPool(numberOfThreads);

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
        return totalTime ;
    }

    public static void main(String[] args) {


    }
}

