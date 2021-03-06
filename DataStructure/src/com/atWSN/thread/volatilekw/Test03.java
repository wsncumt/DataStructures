package com.atWSN.thread.volatilekw;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-05 09:28:43
 **/
public class Test03 {
    public static void main(String[] args) {
//        SubThread subThread = new SubThread();
        for (int i = 0; i < 10; i++) {
            new SubThread().start();
        }
    }
    static class SubThread extends Thread{
        //volatile关键字仅仅是表示所有线程从主内存中读取count变量的值
        //某个线程未运行结束，其他线程可能会抢到CPU执行权，无法保证原子性
        public  volatile static int count;
        public static void addCount(){
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
        //必须使用synchronized保证原子性
        public synchronized static void addCount1(){
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
        @Override
        public void run() {
            addCount1();
        }
    }
}
