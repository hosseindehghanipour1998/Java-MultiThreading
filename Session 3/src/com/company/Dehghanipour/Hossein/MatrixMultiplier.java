package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;

public class MatrixMultiplier extends Thread{
    private static int ARRAY_SIZE = 0 ;
    private static int THREAD_NUMBER = 0 ;
    private static int ID_COUNTER  = 0 ;
    private  int chunkSize , startIndex , endIndex , jumpAmount;
    private int id = 0 ;
    private static int[][] mat1 = new int[ARRAY_SIZE][ARRAY_SIZE];
    private static int[][] mat2 = new int[ARRAY_SIZE][ARRAY_SIZE];
    private static int[][] resultMatrix = new int[ARRAY_SIZE][ARRAY_SIZE] ;
    private static ArrayList<MatrixMultiplier> threadPool = new ArrayList<>() ;

    public static void setBasicInfo(int poolSize , int matrixSize){
        THREAD_NUMBER = poolSize ;
        ARRAY_SIZE = matrixSize ;
    }

    public static void initializeMatrices(){
        Random rnd = new Random() ;

        for (int i = 0 ; i < mat1.length ; i++){
            for(int j = 0 ; j < mat1.length ; j++){
                mat1[i][j] = rnd.nextInt(5) + 1;
                mat2[i][j] = rnd.nextInt(5) + 1;
            }
        }
    }

    public static void printMatrcies(){
        for(int i = 0 ; i < mat1.length ; i++){
            for (int j = 0 ; j < mat1.length ; j++){
                System.out.println(mat1[i][j] + " | " + mat2[i][j]);
            }
        }
    }

    public static void createThreadPool(){
        for(int i = 0 ; i < THREAD_NUMBER ; i++){
            threadPool.add(new MatrixMultiplier(ID_COUNTER++));
        }
    }

    public static void terminateThreadPool(){
        threadPool.clear();
        ID_COUNTER = 0 ;
    }

    public static void terminateResultMatrix(){
        for(int i = 0 ; i < ARRAY_SIZE ; i++){
            for(int j = 0 ; j < ARRAY_SIZE ; j++){
                resultMatrix[i][j] = 0 ;
            }
        }
    }


    @Override
    public void run() {
        super.run();
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

        for (int whichRow = id ; whichRow < endIndex ; whichRow += jumpAmount ){
            for ( int j = 0 ; j < mat1[whichRow].length ; j++){
                resultMatrix[whichRow][j] = 0 ;
                for ( int k = 0 ; k < mat2[j].length ; k++){
                    //resultMatrix[whichRow][j] += StrictMath.sinh(mat1[whichRow][k]) * StrictMath.sinh(mat2[k][j]) ;
                    //resultMatrix[whichRow][j] += mat1[whichRow][k] * mat2[k][j] ;
                }
            }
        }
    }

    public static int getArraySize() {
        return ARRAY_SIZE;
    }

    public static void setArraySize(int arraySize) {
        ARRAY_SIZE = arraySize;
    }

    public static int getThreadNumber() {
        return THREAD_NUMBER;
    }

    public static void setThreadNumber(int threadNumber) {
        THREAD_NUMBER = threadNumber;
    }



    public MatrixMultiplier(int id){
        this.id = id ;
    }

    public static ArrayList<MatrixMultiplier> getThreadPool() {
        return threadPool;
    }
}
