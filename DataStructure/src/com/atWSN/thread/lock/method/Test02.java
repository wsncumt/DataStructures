package com.atWSN.thread.lock.method;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：int getHoldCount()可以返回当前线程调用lock()方法的次数
 * @author:戛剑生
 * @creat: 2021-03-08 22:13:12
 **/
public class Test02 {
    static ReentrantLock lock = new ReentrantLock();//定义一个锁对象
    public static void m1(){
        try {
            lock.lock();
            //打印线程调用lock()的次数
            System.out.println(Thread.currentThread().getName() + "--hold count ：" + lock.getHoldCount());
            //调用m2方法
            //ReentrantLock是可重入锁
            //在m2方法中可再次获得该锁对象
            m2();
        }finally {
            lock.unlock();
        }
    }

    public static void m2(){
        try {
            lock.lock();
            //打印线程调用lock()的次数
            System.out.println(Thread.currentThread().getName() + "--hold count ：" + lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //main线程调用m1
        m1();
        /**
         * 程序运行结果：
         * main--hold count ：1
         * main--hold count ：2
         */
    }
}
