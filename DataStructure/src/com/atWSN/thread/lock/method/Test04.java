package com.atWSN.thread.lock.method;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description:int getWaitQueueLength(Condition condition)
 * 返回与Condition条件相关的等待的线程预估数。
 * @author:戛剑生
 * @creat: 2021-03-08 22:33:59
 **/
public class Test04 {
    static class Service {
        private ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void waitMethod() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "进入等待前，现在该condition条件上等待的线程预估数：" + lock.getWaitQueueLength(condition));
                condition.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void notifyMethod() {
            try {
                lock.lock();
                condition.signalAll();//唤醒所有等待的线程
                System.out.println("唤醒所有等待后，condition条件上等待的线程预估数：" + lock.getWaitQueueLength(condition));
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //1秒后唤醒所有的等待
        service.notifyMethod();
    }
}
