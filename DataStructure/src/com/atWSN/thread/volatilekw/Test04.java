package com.atWSN.thread.volatilekw;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-05 09:28:43
 **/
public class Test04 {
    public static void main(String[] args) {
//        SubThread subThread = new SubThread();
        for (int i = 0; i < 10; i++) {
            new SubThread().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(SubThread.count.get());
    }
    static class SubThread extends Thread{
        //使用AtomicInteger对象
        static private AtomicInteger count = new AtomicInteger();
        public static void addCount(){
            for (int i = 0; i < 1000; i++) {
                count.getAndIncrement();

            }
            System.out.println(Thread.currentThread().getName() + "count" + count.get());
        }
        //必须使用synchronized保证原子性
        public synchronized static void addCount1(){
            for (int i = 0; i < 1000; i++) {
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName() + "count" + count.get());
        }
        @Override
        public void run() {
            addCount();
        }
    }
}
