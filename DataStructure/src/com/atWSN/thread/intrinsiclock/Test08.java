package com.atWSN.thread.intrinsiclock;

/**
 * synchronized 同步类方法
 * 同步时，某个线程出现异常，则该线程会释放锁
 */
public class Test08 {
    public static void main(String[] args) {
        //创建两个线程分别调用mm方法
        //先创建一个Test01对象，通过对象名调用mm()
        Test08 test01 = new Test08();
        Test08 test02 = new Test08();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm();//使用当前类的运行时类作为锁对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test02.sm1();//使用当前类的运行时类作为锁对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sm();
            }
        }).start();
    }

    public static final Object OBJ = new Object();//定义一个常量

    /**
     * 打印100行字符串
     */
    public void mm() {
        synchronized (Test08.class) {//可以使用使用当前类的运行时类作为锁对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
                if (i == 20) {
                    System.out.println(Integer.parseInt("a"));//产生异常的语句
                }
            }

        }
    }

    public static void sm() {
        synchronized (Test08.class) {//可以使用当前类的运行时类作为锁对象。
            System.out.println("静态方法");
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
            }
        }
    }

    public synchronized static void sm1() {
//         synchronized (OBJ) {//可以使用常量对象作为锁对象
        System.out.println("修饰静态方法");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
//            }
        }
    }
}
