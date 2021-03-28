package com.atWSN.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: DataStructures
 * @description：线程池可能会吃掉程序中的异常
 * @author:戛剑生
 * @creat: 2021-03-10 14:59:53
 **/
public class Test07 {
    //定义一个类实现Runnable接口用于实现两个数相除
    static class DivideTask implements Runnable {
        private int x;
        private int y;

        public DivideTask(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "计算：" + x + " / " + y + " = " + (x / y));
        }
    }

    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        //向线程池添加计算两个数相除的任务
        for (int i = 0; i < 5; i++) {
//            executorService.submit(new DivideTask(10,i));
            executorService.execute(new DivideTask(10,i));
        }
        /**
         * 运行程序只有四条计算结果，实际上线程池提交了5个计算任务
         * 当i==0时，会产生算术异常，但线程池把该异常给吃掉了
         * 导致我们对该异常一无所知
         * 解决方法：
         * 1.把submit()改为execute()
         * 2.对线程池进行扩展，对submit方法进行包装
         */
    }
}
