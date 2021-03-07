package com.atWSN.thread.wait;

/**
 * @program: DataStructures
 * @description
 *      wait(long)
 * @author:戛剑生
 * @creat: 2021-03-06 19:35:45
 **/
public class Test07 {
    public static void main(String[] args) {
        final Object LOCK = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    System.out.println(Thread.currentThread().getName() + "开始等待！");
                    try {
                        LOCK.wait(5000);//如果5秒内没有被唤醒，会自动唤醒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "结束等待！");
                }
            }
        });
        thread.setName("t1");
        thread.start();
    }
}
