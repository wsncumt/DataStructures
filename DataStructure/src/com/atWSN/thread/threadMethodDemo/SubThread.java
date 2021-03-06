package com.atWSN.thread.threadMethodDemo;

public class SubThread extends Thread{
    public SubThread(){
        System.out.println("构造方法打印当前线程名称：" + Thread.currentThread().getName());
    }
    @Override
    public void run() {
        System.out.println("run方法打印当前线程名称：" + Thread.currentThread().getName());
    }
}
