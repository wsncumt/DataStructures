package com.atWSN.thread.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：isLocked()判断锁是否被当前线程持有
 * @author:戛剑生
 * @creat: 2021-03-09 09:37:42
 **/
public class Test08 {
    static ReentrantLock lock = new ReentrantLock();

    static void sm() {
        try {
            System.out.println("lock是否被" + Thread.currentThread().getName() + "持有？" + (lock.isLocked() ? "是" : "否"));
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁！");
            System.out.println("线程获得锁后lock是否被" + Thread.currentThread().getName() + "持有？" + (lock.isLocked() ? "是" :
                    "否"));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("11-----" + lock.isLocked());
        new Thread(new Runnable(){
            @Override
            public void run() {
                sm();
            }
        }).start();
        try {
            Thread.sleep(3000);//确保子线程执行结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("22-----" + lock.isLocked());
    }
}
