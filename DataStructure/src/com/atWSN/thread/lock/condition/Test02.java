package com.atWSN.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description:多个Condition实现通知部分线程
 * @author:戛剑生
 * @creat: 2021-03-08 16:49:17
 **/
public class Test02 {

    static class Service {
        private Lock lock = new ReentrantLock();
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();

        //定义方法，使用ConditionA等待
        public void waitMethodA() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "开始等待" + System.currentTimeMillis());
                conditionA.await();
                System.out.println(Thread.currentThread().getName() + "结束等待" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //定义方法，使用ConditionB等待
        public void waitMethodB() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "开始等待" + System.currentTimeMillis());
                conditionB.await();
                System.out.println(Thread.currentThread().getName() + "结束等待" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //定义方法唤醒ConditionA上的等待
        public void signalA() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "唤醒A的时间" + System.currentTimeMillis());
                conditionA.signal();
                System.out.println(Thread.currentThread().getName() + "唤醒A的时间" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //定义方法唤醒ConditionB上的等待
        public void signalB() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "唤醒B的时间" + System.currentTimeMillis());
                conditionB.signal();
                System.out.println(Thread.currentThread().getName() + "唤醒B的时间" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodB();
            }
        }).start();
        try {
            Thread.sleep(3000);//main线程睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //唤醒conditionA的等待
        service.signalA();
        service.signalB();
    }
}
