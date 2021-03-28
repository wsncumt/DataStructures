package com.atWSN.thread.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description boolean hasQueuedThread(Thread thread)
 * 查询指定的线程是否在等待获得锁
 * boolean hasQueuedThreads()：查询是否有线程在等待获得锁
 * @author:戛剑生
 * @creat: 2021-03-09 08:30:16
 **/
public class Test05 {
    static ReentrantLock lock = new ReentrantLock();

    public static void waitMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁。");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁对象！");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                waitMethod();
            }
        };
        Thread[] threads = new Thread[10];//定义线程数组
        //给线程数组的元素赋值，每个线程都调用waitMethod方法并启动线程
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].setName("t" + (i + 1));
            threads[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断数组中的每个线程是否在等待获得锁
        System.out.println(threads[0].getName()+lock.hasQueuedThread(threads[0]));
        System.out.println(threads[1].getName()+lock.hasQueuedThread(threads[1]));
        System.out.println(threads[2].getName()+lock.hasQueuedThread(threads[2]));
        System.out.println(threads[3].getName()+lock.hasQueuedThread(threads[3]));
        System.out.println(threads[4].getName()+lock.hasQueuedThread(threads[4]));
        System.out.println(threads[5].getName()+lock.hasQueuedThread(threads[5]));
        System.out.println(threads[6].getName()+lock.hasQueuedThread(threads[6]));
        System.out.println(threads[7].getName()+lock.hasQueuedThread(threads[7]));
        System.out.println(threads[8].getName()+lock.hasQueuedThread(threads[8]));
        System.out.println(threads[9].getName()+lock.hasQueuedThread(threads[9]));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //再次判断是否还有线程在等待获得该锁
        System.out.println(lock.hasQueuedThreads());
    }
}
