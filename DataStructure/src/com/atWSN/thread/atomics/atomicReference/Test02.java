package com.atWSN.thread.atomics.atomicReference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: DataStructures
 * @description 演示AtomicReference的ABA问题
 * @author:戛剑生
 * @creat: 2021-03-05 16:38:25
 **/
public class Test02 {
    private static AtomicReference<String> atomicReference
            = new AtomicReference<>("abc");

    public static void main(String[] args) {
        //创建第一个线程，先把abc字符串改为def再把字符串还原为abc
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet("abc", "def");
                System.out.println(Thread.currentThread().getName() +
                        "---->" + atomicReference.get());
                atomicReference.compareAndSet("def", "abc");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicReference.compareAndSet
                        ("abc", "xyz"));
            }
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicReference.get());
    }
}
