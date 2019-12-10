package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;

public class Table {
    public static Boolean[] resourcesOnTable = {false , false , false};
    public static Boolean tableIsFull = false ;
    public static final int NUMBER_OF_SMOKERS = 3 ;
    public static Agent saghi = new Agent();

    public static void main(String[] args) {

        Smoker.createThreadPool(NUMBER_OF_SMOKERS);
        //=========================================

        for(Smoker s : Smoker.smokers){
            s.start();
        }
        saghi.start();

    }
}
