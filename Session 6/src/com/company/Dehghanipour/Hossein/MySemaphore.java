package com.company.Dehghanipour.Hossein;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
     //Lock locker = new ReentrantLock();
    // Lock locker = new ReentrantLock();
     Semaphore locker = new Semaphore(1);
     Lock limitNoLocker = new ReentrantLock();
    public int limitNo = 0 ;


    public void acquire(String semaphoreName , int id){
        try {
            locker.acquire();
            limitNoLocker.lock();
            limitNo--;
            if ( limitNo > 0 ){
                this.locker.release();
            }
            limitNoLocker.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void release(String semaphoreName){
        limitNoLocker.lock();
        limitNo ++ ;
        if ( limitNo  <= 1){
            this.locker.release();
        }
        limitNoLocker.unlock();
    }

    public MySemaphore(int value){
        this.limitNo = value ;
        if(value == 0 ){
            try {
                this.locker.acquire();
            }catch (Exception e){e.printStackTrace();}
        }
    }

}
