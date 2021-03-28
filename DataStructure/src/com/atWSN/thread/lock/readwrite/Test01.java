package com.atWSN.thread.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: DataStructures
 * @description：演示ReadWriteLock演示读读共享即允许多多个线程同时获得读锁
 * @author:戛剑生
 * @creat: 2021-03-09 10:19:03
 **/
public class Test01 {
    static class Service{
        //先定义读写锁
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        //定义方法读取数据
        public void readMethod(){
            try {
                rwLock.readLock().lock();//申请读锁。
                System.out.println(Thread.currentThread().getName() + "获得读锁,开始读取数据的时间" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);//模拟读取数据的用时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                rwLock.readLock().unlock();//释放读锁
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service() ;
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    service.readMethod();//在线程中调用readMethod()读取数据
                }
            }).start();
        }
        //程序运行后，多个线程几乎可以同时获得读锁执行lock()后面的代码。
    }
}
