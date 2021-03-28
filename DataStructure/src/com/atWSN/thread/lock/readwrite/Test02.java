package com.atWSN.thread.lock.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: DataStructures
 * @description：演示ReadWriteLock演示写写互斥即只允许一个线程同时获得写锁
 * @author:戛剑生
 * @creat: 2021-03-09 10:30:01
 **/
public class Test02 {
    static class Service{
        //先定义读写锁
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        //定义方法修改数据
        public void write(){
            try {
                rwLock.writeLock().lock();//申请获得写锁
                System.out.println(Thread.currentThread().getName() + "获得写锁，开始修改数据的时间" + System.currentTimeMillis());
                System.out.println("修改数据中");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "写入数据完毕的时间" + System.currentTimeMillis());
                rwLock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    service.write();
                }
            }).start();
        }
        //从执行时间看，同一时间只有一个线程获得写锁，实现了写写互斥
    }
}
