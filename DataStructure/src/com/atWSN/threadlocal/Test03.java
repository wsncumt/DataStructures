package com.atWSN.threadlocal;

import java.util.Date;
import java.util.Random;

/**
 * @program: DataStructures
 * @description ThreadLocal初始值:定义一个子类，在子类中重写initialValue()为其指定初始值
 * @author:戛剑生
 * @creat: 2021-03-07 10:49:32
 **/
public class Test03 {
    //定义ThreadLocal的子类
    static class SubThreadLocal extends ThreadLocal<Date> {
        //重写initialValue()，设置初始值
        @Override
        protected Date initialValue() {
//            return new Date();//把当前日期设置为初始值
            return new Date(System.currentTimeMillis() - 1000 * 60 * 15);//把15分钟之前设置为初始值
        }
    }

    //    //定义ThreadLocal对象
//    static ThreadLocal threadLocal = new ThreadLocal();
    //定义ThreadLocal对象
    static SubThreadLocal threadLocal = new SubThreadLocal();

    //定义线程类
    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                //第一次调用ThreadLocal的get方法会返回null
                System.out.println(i + 1 + "---" + Thread.currentThread().getName() + " value = " + threadLocal.get());
                //如果没有初始值就设置:这里设置一个日期
                if (threadLocal.get() == null) {
                    threadLocal.set(new Date());
                }
                try {
                    Thread.sleep(new Random().nextInt(2000) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
