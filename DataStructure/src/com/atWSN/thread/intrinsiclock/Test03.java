package com.atWSN.thread.intrinsiclock;

/**
 * synchronized 同步代码块
 * 锁对象也可以是常量
 */
public class Test03 {
    public static void main(String[] args) {
        //创建两个线程分别调用mm方法
        //先创建一个Test01对象，通过对象名调用mm()
        Test03 test01 = new Test03();
        Test03 test02 = new Test03();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm();//使用的锁对象是常量对象OBJ
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test02.mm();//使用的锁对象是常量对象OBJ
            }
        }).start();
    }
    public static final Object OBJ = new Object();//定义一个常量
    /**
     * 打印100行字符串
     */
    public  void mm(){
        synchronized (OBJ) {//可以使用常量对象作为锁对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
            }
        }
    }
}
