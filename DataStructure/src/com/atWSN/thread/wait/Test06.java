package com.atWSN.thread.wait;


/**
 * @program: DataStructures
 * @description
 *      notifyAll唤醒所有等待的线程
 * @author:戛剑生
 * @creat: 2021-03-06 08:47:17
 **/
public class Test06 {
    public static void main(String[] args) {
        Object LOCK = new Object();
        SubThread t1 = new SubThread(LOCK);
        t1.setName("t1");
        SubThread t2 = new SubThread(LOCK);
        t2.setName("t2");
        SubThread t3 = new SubThread(LOCK);
        t3.setName("t3");
        SubThread t4 = new SubThread(LOCK);
        t4.setName("t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //唤醒子线程
        synchronized (LOCK){
            LOCK.notify();//调用一次notify只能随机唤醒其中一个线程
            //其他等待的线程依然处于等待状态
            //对于处于等待状态的线程来说，错过了通知信号，这种现象也称为信号丢失
        }
        //唤醒全部子线程
        synchronized (LOCK){
            LOCK.notifyAll();//调用一次notifyAll唤醒其中所有线程
        }
    }
    static class SubThread extends Thread{
        private Object LOCK;

        public SubThread(Object LOCK) {
            this.LOCK = LOCK;
        }

        @Override
        public void run() {
            synchronized (LOCK){
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待！");
                    LOCK.wait();
                    System.out.println(Thread.currentThread().getName() + "结束等待！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
