package com.atWSN.thread.intrinsiclock;

/**
 * 同步代码块与同步方法如何选择
 * 同步方法锁的粒度粗，执行效率低
 * 同步代码块锁的粒度细，执行效率高
 */
public class Test07 {
    public static void main(String[] args) {
        Test07 test07 = new Test07();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test07.doLongTimeTask();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test07.doLongTimeTask();
            }
        }).start();
    }

    public void doLongTimeTask() {
        try {
            System.out.println("任务开始。。。");
            Thread.sleep(3000);//模拟任务需要准备3秒

            synchronized (this) {
                System.out.println("开始同步");

                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "---->" + (i + 1));
                }
            }
            Thread.sleep(3000);//模拟任务需要准备3秒
            System.out.println("任务结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
