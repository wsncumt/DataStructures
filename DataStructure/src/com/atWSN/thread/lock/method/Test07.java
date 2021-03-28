package com.atWSN.thread.lock.method;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：boolean isHeldByCurrentThread()
 * 判断锁是否被当前线程持有
 * boolean isFair()
 * 判断是否为公平锁
 * @author:戛剑生
 * @creat: 2021-03-09 09:14:56
 **/
public class Test07 {
    static class Service {
        private ReentrantLock lock = new ReentrantLock();

        //通过构造方法接收boolean，判断当前锁是否公平
        public Service(boolean isFair) {
            this.lock = new ReentrantLock(isFair);
        }

        public void serviceMethod() {
            try {
                System.out.println("是否为公平锁？" + (lock.isFair() ? "是" : "不是") + "--" + Thread.currentThread().getName() + " 调用lock前是否持有锁？" + (lock.isHeldByCurrentThread() ? "持有" : "未持有"));
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 调用lock后是否持有锁？" + (lock.isHeldByCurrentThread() ? "持有" : "未持有"));
            } finally {
                try {
                    lock.unlock();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt(10);
                System.out.println(num);
                Service service = new Service(num % 2 == 0);
                service.serviceMethod();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(r, "t" + (i + 1)).start();
        }
    }
}
