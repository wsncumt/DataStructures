package com.atWSN.thread.lock.method;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：公平锁与非公平锁
 * @author:戛剑生
 * @creat: 2021-03-08 21:59:34
 **/
public class Test01 {
//    static Lock lock = new ReentrantLock();//定义一个锁，默认是非公平锁。
    static Lock lock = new ReentrantLock(true);//定义一个锁，公平锁。

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + "获得了锁对象");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        /**
         * 运行程序发现：
         *  1.如果是非公平锁，系统会倾向于让一个线程再次获得已经持有的锁，这种策略是高效的但是非公平。
         *  2.如果是公平锁，多个线程不会发生同一个线程连续获得锁的可能。保证了锁的公平，按照先到先得的顺序分配锁。
         */
    }
}
