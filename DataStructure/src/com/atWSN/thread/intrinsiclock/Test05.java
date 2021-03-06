package com.atWSN.thread.intrinsiclock;

/**
 * synchronized 同步实例方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是this对象
 */
public class Test05 {
    public static void main(String[] args) {
        //创建两个线程分别调用mm方法
        //先创建一个Test01对象，通过对象名调用mm()
        Test05 test01 = new Test05();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm();//使用的锁对象this就是test01对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm1();//使用的锁对象this也是test01对象
            }
        }).start();
    }

    /**
     * 打印100行字符串
     */
    public void mm() {
        synchronized (this) {//经常使用当前对象作为锁对象
            System.out.println("修饰代码块");
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
            }
        }
    }
    //使用synchronized修饰实例方法，同步实例方法，默认this作为锁对象
    public synchronized void mm1() {
        System.out.println("修饰方法");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
        }
    }
}
