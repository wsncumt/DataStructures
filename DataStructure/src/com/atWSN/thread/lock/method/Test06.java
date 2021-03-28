package com.atWSN.thread.lock.method;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：boolean hasWaiters(Condition condition)
 * 查询是否有线程在等待指定的condition条件
 * @author:戛剑生
 * @creat: 2021-03-09 08:57:18
 **/
public class Test06 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static void sm() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁定！");
            System.out.println("是否有线程在等待当前condition条件？" + lock.hasWaiters(condition) + "等待数量" + lock.getWaitQueueLength(condition));
            condition.await(new Random().nextInt(3), TimeUnit.SECONDS);//超时后自动唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁对象");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}
