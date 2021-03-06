package com.atWSN.thread.atomics.atomicIntegerArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @program: DataStructures
 * @description
 *      在多线程中使用AtomicIntegerArray原子数组
 * @author:戛剑生
 * @creat: 2021-03-05 15:33:14
 **/
public class Test02 {
    //定义原子数组
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
    public static void main(String[] args) {
        //定义线程数组
        Thread[] threads = new Thread[10];
        //给线程数组的每个线程赋值
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new AddThread();
        }
        //开启子线程
        for (Thread thread:
             threads) {
            thread.start();
        }

        //在所有子线程执行完后查看原子数组中各个元素的值
        //把所有的子线程合并到当前的主线程中
        for (Thread thread:
                threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(atomicIntegerArray);
    }
    //定义一个线程类，在线程类中修改原子数组
    static class AddThread extends Thread{

        @Override
        public void run() {
            //把原子数组的每个元素自增1000次
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < atomicIntegerArray.length(); j++) {
                    atomicIntegerArray.getAndIncrement(j);
                }
            }

        }
    }
}
