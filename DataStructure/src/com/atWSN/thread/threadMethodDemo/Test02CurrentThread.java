package com.atWSN.thread.threadMethodDemo;

public class Test02CurrentThread {
    public static void main(String[] args) throws InterruptedException {

        //创建子线程对象
        SubThread1 subThread1 = new SubThread1();
        subThread1.setName("st1");//设置线程名称
        subThread1.start();

        Thread.sleep(500);//main线程睡眠500毫秒
        Thread thread = new Thread(subThread1);
        thread.setName("st2");
        thread.start();
    }
}
