package com.atWSN.thread.wait;

/**
 * @program: DataStructures
 * @description notify()通知过早
 * @author:戛剑生
 * @creat: 2021-03-06 19:42:57
 **/
public class Test08 {
    public static void main(String[] args) {
        final Object LOCK = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + "开始等待！");
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "等待结束");
                }
            }
        });
        t1.setName("t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + "开始唤醒！");
                    try {
                        LOCK.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "唤醒结束");
                }
            }
        });
        t2.setName("t2");
        //如果先开启t2同志县城，在开启t1等待线程
        //可能会出现t1线程
        t2.start();
        t1.start();

    }
}
