package com.atWSN.thread.runnable;

public class test {
    public static void main(String[] args) {
        //3.创建Runnable接口的实现类对象
        MyRunnable myRunnable = new MyRunnable();
        //4.创建线程对象
        //Thread的一个构造方法参数类型为实现Runnable接口的对象
        //使用该构造方法创建一个线程对象
        Thread thread = new Thread(myRunnable);
        //5.开启线程
        thread.start();
        //main线程
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread-->" + i);
        }

        //有时候调用Thread(Runnable)构造方法时，实参也会传递匿名内部类对象
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("匿名内部内子线程 thread-->" + i);
                }
            }
        });
        thread1.start();
    }
}
