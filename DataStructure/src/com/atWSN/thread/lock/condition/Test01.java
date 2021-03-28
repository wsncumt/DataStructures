package com.atWSN.thread.lock.condition;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：Condition等待与通知
 * @author:戛剑生
 * @creat: 2021-03-08 16:30:42
 **/
public class Test01 {
    //定义锁
    static Lock lock  = new ReentrantLock();
    //获得Condition对象
    static Condition condition = lock.newCondition();
    //定义线程子类
    static class SubThread extends Thread{
        @Override
        public void run() {
            try {
                lock.lock();//调用await方法前必须先获得锁。
                System.out.println(Thread.currentThread().getName() + "获得锁");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "线程等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            }
        }
    }
    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        t1.setName("t1");
        t1.start();//子线程启动后会转入等待状态

        try {
            Thread.sleep(3000);//主线程睡眠3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //唤醒子线程的等待
        try {
            lock.lock();
            condition.signal();//调用signal的线程也必须获得对应的锁，此时是main线程调用的
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
