package com.atWSN.thread.wait;

/**
 * @program: DataStructures
 * @description
 *      中断线程会唤醒线程的等待
 * @author:戛剑生
 * @creat: 2021-03-06 08:26:07
 **/
public class Test05 {
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.setName("t");
        subThread.start();

        try {
            Thread.sleep(2000);//主线程睡眠2秒，确保子线程处于wait()状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subThread.interrupt();
    }

    static private final Object OBJ = new Object();

    static class SubThread extends Thread {
        @Override
        public void run() {
            synchronized (OBJ) {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    OBJ.wait();
                    System.out.println(Thread.currentThread().getName() + "结束等待");
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("wait等待被中断了");
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
