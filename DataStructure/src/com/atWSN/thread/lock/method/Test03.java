package com.atWSN.thread.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description:int getQueueLength()的使用
 * @author:戛剑生
 * @creat: 2021-03-08 22:23:49
 **/
public class Test03 {
    static  private ReentrantLock lock = new ReentrantLock();
    public static void sm(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁，执行方法，估计等待获得锁的线程数" + lock.getQueueLength());
            Thread.sleep(1000);//睡眠1秒，模拟执行时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}
