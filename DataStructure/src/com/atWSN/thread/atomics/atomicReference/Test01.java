package com.atWSN.thread.atomics.atomicReference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: DataStructures
 * @description 使用AtomicReference原子读写一个对象
 * @author:戛剑生
 * @creat: 2021-03-05 16:25:25
 **/
public class Test01 {
    public static void main(String[] args) {
        //创建100个子线程修改字符串
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("abcd", "def")) {
                        System.out.println(Thread.currentThread().getName() + "把字符串更改为def");
                    }
                }
            }).start();
        }
        //创建100个子线程修改字符串
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("def", "abcd")) {
                        System.out.println(Thread.currentThread().getName() + "把字符串更改为abcd");
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicReference.get());
    }

    //创建一个AtomicReference对象
    static AtomicReference<String> atomicReference = new AtomicReference<>("abcd");
}
