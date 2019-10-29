package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;

public class Main {
    private static void warmUp(int poolSize , int arraySize){
        MatrixMultiplier.setBasicInfo(poolSize,arraySize);
        MatrixMultiplier.createThreadPool();
        MatrixMultiplier.initializeMatrices();
    }

    private static void coolDown(){
        MatrixMultiplier.terminateThreadPool();
        MatrixMultiplier.terminateResultMatrix();
    }


    public static void main(String[] args) {
	// write your code here

        int arraySize = 1000 ;
        int[] threadsNumbers = {1,2,5,10,20,50,100} ;
        ArrayList<Long> eachThreadTimes = new ArrayList<>() ;

        //Initializations
            for(int threadNumber : threadsNumbers){
                warmUp(threadNumber,arraySize);
                //Place the core Here
                long spentTime = ThreadTools.multiplicationThread();
                eachThreadTimes.add(spentTime);
                //end of core
                coolDown();
            }
    }
}
