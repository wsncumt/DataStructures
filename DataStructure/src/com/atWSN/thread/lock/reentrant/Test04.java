package com.atWSN.thread.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：ReentrantLock锁的可重入性
 * @author:戛剑生
 * @creat: 2021-03-07 15:47:24
 **/
public class Test04 {
    static class SubThread extends Thread {
        //定义锁
        private static Lock lock = new ReentrantLock();
        public static int num = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    lock.lock();
                    //可重入锁是指可以反复获得该锁
                    lock.lock();
                    num++;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    lock.unlock();
                    //上边使用几次锁这里就要释放几次
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        t1.setName("t1");
        SubThread t2 = new SubThread();
        t2.setName("t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(SubThread.num);
    }
}
