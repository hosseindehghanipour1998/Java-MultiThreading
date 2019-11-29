package com.company.Dehghanipour.Hossein;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
    Lock locker = new ReentrantLock();
    Lock limitNoLocker = new ReentrantLock();
    public int limitNo = 0 ;


    public void acquire(String semaphoreName){
        if(limitNo != 0){
            System.out.println("We are in acquire("+semaphoreName+") | Before Lock ");
            this.locker.lock();
            System.out.println("We are in acquire("+semaphoreName+")| After Lock ");
            limitNoLocker.lock();
            limitNo--;
            limitNoLocker.unlock();
            if( limitNo > 0 ){
                System.out.println("We are in acquire("+semaphoreName+")  | limit>0");
                this.locker.unlock();
            }
        }

    }

    public void release(String semaphoreName){

        limitNoLocker.lock();
        limitNo ++ ;
        limitNoLocker.unlock();
        this.locker.unlock();
        System.out.println("We are in acquire("+semaphoreName+")  | Released");

    }
    public MySemaphore(int value){
        this.limitNo = value ;
    }

}
