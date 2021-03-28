package com.atWSN.thread.lock.condition;

import com.atWSN.thread.producerstack.MyStack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：实现生产者/消费者设计模式，两个线程交替打印
 * @author:戛剑生
 * @creat: 2021-03-08 17:14:29
 **/
public class Test03 {
    static class MyService{
        private Lock lock=new ReentrantLock();//创建锁对象
        private Condition condition = lock.newCondition();//创建condition对象
        private boolean flag = true;//定义一个交替打印的标识

        //定义方法：只打印----横线
        public void printOne(){
            try {
                //锁定
                lock.lock();
                while (flag){//flag为true进行等待
                    condition.await();
                }
                //flag为false进行打印
                System.out.println(Thread.currentThread().getName() + "------------------");
                //打印完后把flag置为true;
                flag = true;
                //通知另外的线程打印
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        //定义方法：只打印****星号
        public void printTwo(){
            try {
                //锁定
                lock.lock();
                while (!flag){//flag为false进行等待
                    condition.await();//等待时会释放锁对象
                }
                //flag为true进行打印
                System.out.println(Thread.currentThread().getName() + "***************");
                //打印完后把flag置为false;
                flag = false;
                //通知另外的线程打印
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        MyService myService = new MyService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    myService.printOne();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    myService.printTwo();
                }
            }
        }).start();
    }
}
