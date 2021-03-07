package com.atWSN.thread.lock.reentrant;


/**
 * @program: DataStructures
 * @description ：演示锁的可重入性
 * @author:戛剑生
 * @creat: 2021-03-07 15:11:04
 **/
public class Test01 {
    public  synchronized void sm1(){
        System.out.println("同步方法1：");
        //线程执行sm1方法，默认this作为锁对象，在sm1()方法中调用了sm2方法，当前线程还是持有this锁对象的
        //sm2同步方法默认的锁对象是this对象，要执行sm2必须先获得this对象，当前this对象被当前线程持有，可以再次获得this对象，这就是锁的可重入性。
        sm2();
    }

    private  synchronized void sm2() {
        System.out.println("同步方法2：");
        sm3();
    }

    private  synchronized void sm3() {
        System.out.println("同步方法3：");
    }

    public static void main(String[] args) {
        Test01 t = new Test01();
        new Thread(new Runnable(){
            @Override
            public void run() {
                t.sm1();
            }
        }).start();
    }
}
