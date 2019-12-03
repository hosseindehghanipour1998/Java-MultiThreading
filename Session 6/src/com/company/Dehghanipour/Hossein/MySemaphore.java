package com.company.Dehghanipour.Hossein;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
     //Lock locker = new ReentrantLock();
     Lock locker = new ReentrantLock();
     Lock limitNoLocker = new ReentrantLock();
     Lock lvl2 = new ReentrantLock();
    public int limitNo = 0 ;


    public void acquire(String semaphoreName , int id){

        System.out.println("We are in acquire("+semaphoreName+") | Before Lock  -> Limit No : " + limitNo );
        this.locker.lock();
        System.out.println("Semaphore (" + semaphoreName + ") ID : " + id + " Locked !" );
        limitNoLocker.lock();
        limitNo--;
        limitNoLocker.unlock();
        if ( limitNo > 0 ){
            System.out.println("We are in acquire("+semaphoreName+")  | limit>0");
            this.locker.unlock();
            lvl2.unlock();
        }

    }

    public void release(String semaphoreName){
        System.out.println("We are in acquire("+semaphoreName+")  | Released");
        limitNoLocker.lock();
        limitNo ++ ;
        limitNoLocker.unlock();
        this.locker.unlock();



    }
    public MySemaphore(int value){
        this.limitNo = value ;
        if(value == 0 ){
            this.locker.lock();
        }
    }

}
