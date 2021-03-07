package com.atWSN.thread.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: DataStructures
 * @description 定义一个集合
 * 定义一个线程向集合中添加数据，添加完数据后通知另外的线程从集合中取数据
 * 定义一个线程从集合中取数据，如果集合中没有数据就等待
 * @author:戛剑生
 * @creat: 2021-03-06 20:24:49
 **/
public class Test10 {
    public static final Object LOCK = new Object();
    //定义一个List集合
    static List list = new ArrayList<>();

    public static void main(String[] args) {
        Thread t1 = new Subtract();
        t1.setName("取数据的线程1");
        Thread t2 = new Add();
        t2.setName("添加数据的线程");
        //测1：先开启添加数据的线程，再开启一个取数据的线程
        //大多数情况下会正常取数据
//        t2.start();
//        t1.start();
        //测2：先开启取数据的线程，再开启添加数据的线程
        //取数据的线程会先等待，等到添加数据之后，再取数据
//        t1.start();
//        t2.start();
        //测3：开启两个取数据的线程，再开启添加数据的线程
        Thread t3 = new Subtract();
        t3.setName("取数据的线程2");
        t1.start();
        t3.start();
        t2.start();
        /**
         * 某次的执行结果如下
         * 取数据的线程1开始等待！
         * 取数据的线程2开始等待！
         * 添加数据的线程添加数据！
         * 添加数据的线程完成添加数据！
         * 取数据的线程2结束等待！
         * 取数据的线程2从集合中取了x后，集合中剩余的数据数量：0
         * 取数据的线程1结束等待！
         * Exception in thread "取数据的线程1" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
         *
         * 出现异常的原因：向集合中添加了一次数据，但取了两次
         * 解决方案：等待的线程被唤醒后，再判断一次集合中是否有数据可取
         * 需要把subtract方法中的if改为while
         */
    }

    //定义方法从集合中取数据
    public static void subtract() {
        synchronized (LOCK) {
//            if (list.isEmpty()) {
            while (list.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "开始等待！");
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结束等待！");
            }
            Object data = list.remove(0);
            System.out.println(Thread.currentThread().getName() + "从集合中取了" + data + "后，集合中剩余的数据数量：" + list.size());
        }
    }

    //向集合中添加数据
    //添加完2数据后，通知等待的线程取数据
    public static void add() {
        synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + "添加数据！");
            list.add("x");
            LOCK.notifyAll();
            System.out.println(Thread.currentThread().getName() + "完成添加数据！");
        }
    }

    //定义线程类调用subtract
    static class Subtract extends Thread {
        @Override
        public void run() {
            subtract();
        }
    }

    //定义线程类调用add
    static class Add extends Thread {
        @Override
        public void run() {
            add();
        }
    }
}
