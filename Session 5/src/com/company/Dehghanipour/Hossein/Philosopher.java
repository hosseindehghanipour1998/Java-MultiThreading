package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;

public class Philosopher extends Thread {

    public static ArrayList<Philosopher> threadPool = new ArrayList<>();
    private static int idCounter = 1;
    private int id;


    public static void initializeThreadPool(int threadPoolSize) {

        for (int i = 0; i < threadPoolSize; i++) {
            threadPool.add(new Philosopher(idCounter++));
        }

    }

    public Philosopher(int newId) {
        this.id = newId;
    }

    private static int loop = 10;

    @Override
    public void run() {
        while (loop >= 0) {
            System.out.println("ID : " + id + " | Sleeping");
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int spoon1 = id - 1;
            int spoon2 = id;
            if (id == 5) {
                spoon2 = 0;
            }
            if (Main.spoons.get(spoon1).tryLock()) {
                if (Main.spoons.get(spoon2).tryLock()) {
                    System.out.println("ID : " + id + " | Is eating.");
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Main.spoons.get(spoon1).unlock();
                    Main.spoons.get(spoon2).unlock();
                } else {
                    System.out.println("ID : " + id + " | Unsuccessful Attempt to eat.");
                    Main.spoons.get(spoon1).unlock();
                }

            } else {
                System.out.println("ID : " + id + " | Unsuccessful Attempt to eat.");
            }
            loop--;
        }


    }
}
