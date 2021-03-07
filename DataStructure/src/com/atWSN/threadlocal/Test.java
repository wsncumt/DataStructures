package com.atWSN.threadlocal;

/**
 * @program: DataStructures
 * @description：ThreadLocal的使用
 * @author:戛剑生
 * @creat: 2021-03-07 09:47:26
 **/
public class Test {
    //定义一个ThreadLocal对象
    static ThreadLocal threadLocal = new ThreadLocal();

    //定义线程类
    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                //设置线程关联的值
                threadLocal.set(Thread.currentThread().getName() + "-" + (i + 1));
                //调用get方法读取关联的值
                System.out.println(Thread.currentThread().getName() + " value = " + threadLocal.get());
            }
        }
    }

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
