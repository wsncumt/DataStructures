package com.atWSN.thread.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: DataStructures
 * @description notify()不会立即释放锁对象
 * @author:戛剑生
 * @creat: 2021-03-05 21:18:43
 **/
public class Test04 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        final String list = "lock";//定义一个字符串作为锁对象
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    System.out.println(Thread.currentThread().getName() + "开始运行" + System.currentTimeMillis());
                    try {
                        if (list.size() != 5) {
                            list.wait();//线程等待，会释放锁对象，当前线程转入WAITING等待状态
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "结束运行" + System.currentTimeMillis());
                }
            }
        });
        t1.setName("t1");

        //定义第二个线程负责唤醒第一个线程
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //notify()方法也需要在同步代码块中由锁对象调用
                synchronized (list) {
                    System.out.println(Thread.currentThread().getName() + "运行开始" + System.currentTimeMillis());
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + "添加第" + (i + 1) + "个数据");
                        list.add("data -- " + (i + 1));
                        if (list.size() == 5) {
                            list.notify();
                            System.out.println(Thread.currentThread().getName() + "已发出唤醒通知");
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    //
                    System.out.println
                            (Thread.currentThread().getName() + "运行结束" + System.currentTimeMillis());
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
