package com.atWSN.thread.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description ：使用Lock锁同步不同方法中的同步代码块
 * @author:戛剑生
 * @creat: 2021-03-07 15:25:38
 **/
public class Test03 {
    //定义显示锁
    static Lock lock = new ReentrantLock();

    //定义方法1
    public static void sm() {
        //经常在try代码块中获得锁，在finally子句中释放锁
        try {
            //先获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " -- method1 -- " + System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000) + 1);
            System.out.println(Thread.currentThread().getName() + " -- method1 -- " + System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //定义方法2
    public static void sm1() {
        //经常在try代码块中获得锁，在finally子句中释放锁
        try {
            //先获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " -- method2 -- " + System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000) + 1);
            System.out.println(Thread.currentThread().getName() + " -- method2 -- " + System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                sm1();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(r1).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(r2).start();
        }
    }
}
