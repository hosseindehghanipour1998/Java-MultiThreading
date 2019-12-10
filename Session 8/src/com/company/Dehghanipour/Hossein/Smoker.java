package com.company.Dehghanipour.Hossein;

import javafx.scene.control.Tab;

import java.util.ArrayList;

public class Smoker extends Thread{

    int id = 0 ;
    public static ArrayList<Smoker> smokers = new ArrayList<>();
    private static int idCounter = 0 ;

    private Smoker(int smokerId){
        this.id = smokerId;
    }

    public static void createThreadPool(int numberOfSmokers){
        for ( int i = 0 ; i <numberOfSmokers ; i++ ){
            smokers.add(new Smoker(idCounter++));
        }
    }

    private Boolean canEat(){
        if ( Table.resourcesOnTable[this.id] == false ){
            return true ;
        }
        return false ;
    }

    public synchronized void smokerSmoke() {
        if (Table.tableIsFull == true) {
            if (canEat() == true) {
                System.out.println("Smoker Number : " + id + " is Making the Cigar");
                Table.tableIsFull = false;
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void run() {
        System.out.println("Smoker No : " + this.id + " Is On");
       while (true){
            smokerSmoke();
        }
    }
}
