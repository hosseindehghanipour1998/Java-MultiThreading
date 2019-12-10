package com.company.Dehghanipour.Hossein;

import javafx.scene.control.Tab;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Agent extends Thread {
    private Random rnd = new Random() ;
    public void agentSmoke(){
        if ( Table.tableIsFull == false){
            for ( int i = 0 ; i < Table.resourcesOnTable.length ; i++){
                Table.resourcesOnTable[i] = false ;
            }
            int firstIndex = 0 ; int  secondIndex = 0 ;
            firstIndex = rnd.nextInt(3) ;
            secondIndex = rnd.nextInt(3);
            while(secondIndex == firstIndex){
                secondIndex = rnd.nextInt(3);
            }
            System.out.println("Agent Filled items [" + firstIndex +"] [" +secondIndex+ "]");
            Table.resourcesOnTable[secondIndex] = true ;
            Table.resourcesOnTable[firstIndex] = true ;
            Table.tableIsFull = true;


            //System.out.println("Table is Full");
        }
        else {
            //System.out.println("Full table | Agent bekap");
        }
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Agent hastam");
        while (true){

            agentSmoke();

        }

    }
}
