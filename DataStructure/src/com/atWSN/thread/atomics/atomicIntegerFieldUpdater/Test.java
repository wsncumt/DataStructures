package com.atWSN.thread.atomics.atomicIntegerFieldUpdater;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-05 15:54:16
 **/
public class Test {
    public static void main(String[] args) {
        User user = new User(12, 10);
        //开启十个线程
        for (int i = 0; i < 10; i++) {
            new SubThread(user).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
