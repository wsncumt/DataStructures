package com.atWSN.thread.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: DataStructures
 * @description：演示ReadWriteLock中的读写互斥 一个线程获得读锁时，写线程等待
 * 一个线程获得写锁时，其他线程等待
 * @author:戛剑生
 * @creat: 2021-03-09 11:19:17
 **/
public class Test03 {
    static class Service {
        //先定义读写锁
        ReadWriteLock rwLock = new ReentrantReadWriteLock();

        //定义方法修改数据
        public void write() {
            try {
                rwLock.writeLock().lock();//申请获得写锁
                System.out.println(Thread.currentThread().getName() + "获得写锁，开始修改数据的时间" + System.currentTimeMillis());
                System.out.println("修改数据中");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "写入数据完毕的时间" + System.currentTimeMillis());
                rwLock.writeLock().unlock();
            }
        }

        //定义方法读取数据
        public void readMethod() {
            try {
                rwLock.readLock().lock();//申请读锁。
                System.out.println(Thread.currentThread().getName() + "获得读锁,开始读取数据的时间" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);//模拟读取数据的用时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "数据读取结束的时间" + System.currentTimeMillis());
                rwLock.readLock().unlock();//释放读锁
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        //定义一个线程读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.readMethod();
            }
        }).start();
        //定义一个线程写数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }).start();
        //定义一个线程读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.readMethod();
            }
        }).start();
        //定义一个线程读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.readMethod();
            }
        }).start();
        //定义一个线程读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.readMethod();
            }
        }).start();
    }
}
