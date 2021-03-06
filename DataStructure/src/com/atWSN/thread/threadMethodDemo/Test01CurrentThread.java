package com.atWSN.thread.threadMethodDemo;

public class Test01CurrentThread {
    public static void main(String[] args) {
        System.out.println("main方法打印当前线程名称：" + Thread.currentThread().getName());
        //创建子线程,调用SubThread构造方法
        //在main线程中调用构造方法，所以构造方法中的当前线程就是main线程
        SubThread subThread = new SubThread();
        subThread.start();//启动子线程，子线程会调用run方法，所以run()当前线程就是Thread-0子线程。
        subThread.run();//在main方法中直接调用run()方法，没有开启新的线程，所以run()方法所在的当前线程就是main方法
    }
}
