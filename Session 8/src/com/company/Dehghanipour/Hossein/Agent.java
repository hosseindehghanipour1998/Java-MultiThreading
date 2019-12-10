package com.company.Dehghanipour.Hossein;

import java.util.Random;


public class Agent extends Thread {
    private Random rnd = new Random() ;
    public void agentFill(){
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
        }
        else {
        }
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Agent is On.");
        while (true){
            agentFill();
        }

    }
}
