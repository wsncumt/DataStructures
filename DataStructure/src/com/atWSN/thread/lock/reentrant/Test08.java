package com.atWSN.thread.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：tryLock()的基本使用，锁对象没有被其他线程持有的情况下，才会获得该锁定
 * @author:戛剑生
 * @creat: 2021-03-07 21:36:23
 **/
public class Test08 {
    static class TimeLock implements Runnable {
        private static ReentrantLock lock = new ReentrantLock();//定义一个锁对象

        @Override
        public void run() {
            try {
                if (lock.tryLock()) {//获得锁返回true
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                    System.out.println("执行相应的任务");
                    Thread.sleep(3000);//一个线程1获得锁并执行耗时任务，该任务需要3秒钟
                    // 另一个线程2尝试获得锁，线程2两秒内还没获得锁的话就会放弃
                } else {//没有获得锁
                    System.out.println(Thread.currentThread().getName() + "没有获得锁！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放锁！");
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        t1.setName("t1");
        Thread t2 = new Thread(timeLock);
        t2.setName("t2");
        Thread t3 = new Thread(timeLock);
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();

    }
}
