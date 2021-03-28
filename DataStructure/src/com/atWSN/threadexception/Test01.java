package com.atWSN.threadexception;

/**
 * @program: DataStructures
 * @description:演示设置线程的UncaughtExceptionHandler回调接口
 * @author:戛剑生
 * @creat: 2021-03-09 15:43:25
 **/
public class Test01 {
    public static void main(String[] args) {
        //1.设置线程全局的回调接口
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //t参数接收发生异常的线程，e就是该线程中的异常
                System.out.println(t.getName() + "产生了异常" +e.getMessage());
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始运行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    //线程中的受检异常必须捕获处理
                    e.printStackTrace();
                }
                System.out.println(12/0);//产生算术异常
            }
        });
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String txt = null;
                System.out.println(txt.length());//产生空指针异常
            }
        });

        t2.setName("t2");
        t2.start();
        /**
         * 实际开发中，这种异常处理的方式比较常用
         * 尤其是异步执行的方法
         * 如果线程产生了异常，JVM会调用dispatchUncaughtException(Throwable e)方法
         * 在该方法中，调用了getUncaughtExceptionHandler().uncaughtException(this,e)
         * 如果当前线程设置了UncaughtExceptionHandler()回调接口就调用它自己的
         *      uncaughtException方法
         *      如果没有设置则调用当前线程所在线程组的UncaughtExceptionHandler()方法
         *      如果线程组也没有设置该回调接口，直接把异常信息定向到System.err中
         */
    }
}
