package com.atWSN.thread.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description ：Lock锁的基本使用
 * @author:戛剑生
 * @creat: 2021-03-07 15:25:38
 **/
public class Test02 {
    //定义显示锁
    static Lock lock = new ReentrantLock();

    //定义方法
    public static void sm() {
        //先获得锁
        lock.lock();
        //下边这部分就是同步代码块
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + (i + 1));
        }
        //释放锁
        lock.unlock();
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(r).start();
        }
    }
}
