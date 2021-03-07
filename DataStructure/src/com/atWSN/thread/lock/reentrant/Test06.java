package com.atWSN.thread.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：通过ReentrantLock的lockInterruptibly()方法避免死锁问题
 * @author:戛剑生
 * @creat: 2021-03-07 20:25:02
 **/
public class Test06 {
    static class IntLock implements Runnable {
        public static ReentrantLock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        int lockNumber;//定义整数变量决定使用哪个锁

        public IntLock(int lockNumber) {
            this.lockNumber = lockNumber;
        }

        @Override
        public void run() {
            try {
                if (lockNumber % 2 == 1) {
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁" + lockNumber % 2 + "还需要获得锁" + (lockNumber % 2 + 1));
                    Thread.sleep(new Random().nextInt(1000));
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得锁" + lockNumber % 2 + "和锁" + (lockNumber % 2 + 1));
                } else {
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁" + (lockNumber % 2 + 2) + "还需要获得锁" + (lockNumber % 2 + 1));
                    Thread.sleep(new Random().nextInt(1000));
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得锁" + (lockNumber % 2 + 2) + "和锁" + (lockNumber % 2 + 1));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (lock1.isHeldByCurrentThread()) {//锁被当前线程持有就会释放
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {//锁被当前线程持有就会释放
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "线程退出！");
            }
        }
    }

    public static void main(String[] args) {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);
        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();

        t2.start();

        //在main线程中等待3000秒，如果还有线程没有结束就中断该线程
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //可以中断任何一个线程来解决死锁
        if (t2.isAlive()) {
            t2.interrupt();
        }
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (t2.isAlive()) {
//            t2.interrupt();
//        }
    }
}
