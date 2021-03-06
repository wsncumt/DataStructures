package com.atWSN.thread.intrinsiclock;

/**
 * @program: DataStructures
 * @description
 *  死锁演示
 *      多线程中，同步可能需要多个锁，如果获得锁的顺序不一致，可能会导致死锁
 * @author:戛剑生
 * @creat: 2021-03-05 08:35:48
 **/
public class Test09 {
    public static void main(String[] args) {
        SubThread subThread1 = new SubThread();
        subThread1.setName("a");
        subThread1.start();
        SubThread subThread2 = new SubThread();
        subThread2.setName("b");
        subThread2.start();
    }
    static class SubThread extends Thread{
        private static final Object LOCK1 = new Object();
        private static final Object LOCK2 = new Object();
        @Override
        public void run() {
            if ("a".equals(Thread.currentThread().getName())){
                synchronized (LOCK1){
                    System.out.println("a线程获得LOCK1锁，还需要获得LOCK2锁！");
                    synchronized (LOCK2){
                        System.out.println("a线程获得LOCK1锁后获得LOCK2锁！");
                    }
                }
            }

            if ("b".equals(Thread.currentThread().getName())){
                synchronized (LOCK2){
                    System.out.println("b线程获得LOCK2锁，还需要获得LOCK1锁！");
                    synchronized (LOCK1){
                        System.out.println("b线程获得LOCK2锁后获得LOCK1锁！");
                    }
                }
            }
        }
    }
}
