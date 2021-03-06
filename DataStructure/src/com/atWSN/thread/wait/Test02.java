package com.atWSN.thread.wait;

/**
 * @program: DataStructures
 * @description
 * wait()会使线程等待
 * @author:戛剑生
 * @creat: 2021-03-05 21:07:28
 **/
public class Test02 {
    public static void main(String[] args) {
        try {
            String test  = "aaa";
            System.out.println("同步前");
            synchronized (test){
                System.out.println("同步代码块开始！");
                test.wait();    // 调用wait方法后当前线程就会等待同时释放锁对象。当前线程需要被唤醒，如果没有唤醒就会一直等待
                System.out.println("wait后边的代码！");
            }
            System.out.println("同步代码块后边的代码");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main后边的其他代码……");
    }
}
