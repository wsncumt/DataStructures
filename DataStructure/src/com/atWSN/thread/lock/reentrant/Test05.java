package com.atWSN.thread.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：演示lockInterruptibly()方法
 * @author:戛剑生
 * @creat: 2021-03-07 16:31:47
 **/
public class Test05 {
    public static void main(String[] args) {
        Service service = new Service();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };

        Thread t1 = new Thread(r);
        t1.setName("t1");
        t1.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(r);
        t2.setName("t2");
        t2.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();//t2中断

        /**
         * 程序运行结果：
         * t1 -- 获得锁！
         * null
         * t2 -- 释放锁！
         * Exception in thread "t2" java.lang.IllegalMonitorStateException
         * 	at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
         * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
         * 	at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
         * 	at com.atWSN.thread.lock.reentrant.Test05$Service.serviceMethod(Test05.java:57)
         * 	at com.atWSN.thread.lock.reentrant.Test05$1.run(Test05.java:18)
         * 	at java.lang.Thread.run(Thread.java:748)
         * t1 -- 释放锁！
         */
    }

    static class Service {
        private Lock lock = new ReentrantLock();

        public void serviceMethod() {
            try {
//                lock.lock();//获得锁定，即使调用了现成的interrupt()方法，也没有真正中断线程
                lock.lockInterruptibly();//如果线程被中断了，不会获得锁，会抛出异常
                System.out.println(Thread.currentThread().getName() + " -- 获得锁！");
                //模拟执行一段耗时的操作
                for (int i = 0; i < Integer.MAX_VALUE >>> 2; i++) {
                    new StringBuilder();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(Thread.currentThread().getName() + " -- 释放锁！");
                lock.unlock();
            }
        }
    }
}
