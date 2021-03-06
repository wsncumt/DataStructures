package com.atWSN.thread.runnable;

/**
 * 当线程类已经有父类，就不能用继承Thread类的形式来创建线程
 * 可以使用实现Runnable接口的形式来实现
 * 1.定义类实现Runnable接口
 */
public class MyRunnable implements Runnable{
    //2.重写Runnable接口中的抽象方法run
    //run()就是子线程要执行的代码
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("sub thread-->" + i);
        }
    }
}
