package com.atWSN.thread.wait;

/**
 * @program: DataStructures
 * @description 需要通过notify()唤醒等待的线程
 * @author:戛剑生
 * @creat: 2021-03-05 21:18:43
 **/
public class Test03 {
    public static void main(String[] args) {
        final String LOCK = "lock";//定义一个字符串作为锁对象
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + "开始等待" + System.currentTimeMillis());
                    try {
                        LOCK.wait();//线程等待，会释放锁对象，当前线程转入WAITING等待状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "结束等待" + System.currentTimeMillis());
                }
            }
        });
        t1.setName("t1");

        //定义第二个线程负责唤醒第一个线程
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //notify()方法也需要在同步代码块中由锁对象调用
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + "开始唤醒" + System.currentTimeMillis());

                    LOCK.notify();//唤醒在LOCK锁对象上等待的某一个线程
                    //
                    System.out.println(Thread.currentThread().getName() + "结束唤醒" + System.currentTimeMillis());
                }
            }
        });
        t2.setName("t2");

        t1.start();//开启t1线程，t1线程等待
        try {
            Thread.sleep(3000);//为了确保等待，让主线程睡3s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();//t1线程开启3秒后，再开启t2线程唤醒t1线程
    }
}
