package com.atWSN.thread.intrinsiclock;

/**
 * synchronized 同步代码块
 * 如果线程的锁不同，则不能实现同步。
 * 想要同步必须使用同一个锁对象
 */
public class Test02 {
    public static void main(String[] args) {
        //创建两个线程分别调用mm方法
        //先创建一个Test01对象，通过对象名调用mm()
        Test02 test01 = new Test02();
        Test02 test02 = new Test02();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm();//使用的锁对象this就是test01对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test02.mm();//使用的锁对象this就是test02对象
            }
        }).start();
    }
    /**
     * 打印100行字符串
     */
    public  void mm(){
        synchronized (this) {//经常使用当前对象作为锁对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
            }
        }
    }
}
