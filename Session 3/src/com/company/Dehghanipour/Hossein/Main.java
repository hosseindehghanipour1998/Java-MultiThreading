package com.company.Dehghanipour.Hossein;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {
    public static final String NORMAL_MATRIX_FILE_NAME = "NormalMultiplierResults.txt" ;
    public static final String HYPERBOLIC_MATRIX_FILE_NAME = "HyperbolicMultiplierResults.txt" ;
    public static final String ADDER_FILE_NAME = "AdderResults.txt" ;


    public static void main(String[] args) {
	// write your code here

        int arraySize = 1000 ;
        int[] threadsNumbers = {1,2,5,10,20,50,100} ;
        ArrayList<Long> eachThreadTimes = new ArrayList<>() ;
        int adderLoopCounter = 500 ;

        //Initializations
        System.out.println("Program Started ...");

        //============================ Matrix Multiplier =========================================
        for ( int i = 0 ; i < 2 ; i++){//Once for Normal Multiplication and Once for sinh(x) form.
            System.out.println("Sinh(x) Enabled ? : " + MatrixMultiplier.sinh.toString().toUpperCase());
            for(int threadNumber : threadsNumbers){//testing with different number of threads.
                MatrixMultiplier.warmUp(threadNumber,arraySize);
                //Place the core Here
                long spentTime = ThreadTools.multiplicationThread();
                eachThreadTimes.add(spentTime);
                //end of core
                MatrixMultiplier.coolDown();
            }

            ThreadTools.printTheMultiplierTable(eachThreadTimes,threadsNumbers);
            ThreadTools.matrixFileWriter(MatrixMultiplier.sinh , eachThreadTimes , threadsNumbers , arraySize);
            eachThreadTimes.clear();
            MatrixMultiplier.sinh = true ;//now it's time to calculate sinh(x) form.
            System.out.println("==================");
        }



        //============================ ADDER =========================================
        //Adder
        IODevice.deletePredefinedFile(ADDER_FILE_NAME);
        ArrayList<Integer> allSummations = new ArrayList<>();
        System.out.println("=========== Adder Part begins.===============");
        Adder.setBasicInfo(2,adderLoopCounter);
        Adder.initializeFrequencyKeeper();
        for ( int i = 0 ; i < 100 ; i++){
            Adder.initializeThreadPool();
            Long time = ThreadTools.AdderThread();
            System.out.println("("+(i+1)+"):Summation : " + Adder.getSUMMATION());
            Adder.frequencyKeeper[Adder.getSUMMATION()] ++ ;
            eachThreadTimes.add(time);
            allSummations.add(Adder.getSUMMATION());
            Adder.terminateThreadPool();
        }
        System.out.println("====== Results ======");
        ThreadTools.printAdderTable();
        IODevice.writeFileAdder(ADDER_FILE_NAME,allSummations);
        eachThreadTimes.clear();


        //End of The Program
        System.out.println("Written In File ... ");
        System.out.println("Program Finished ... ");
    }
}
