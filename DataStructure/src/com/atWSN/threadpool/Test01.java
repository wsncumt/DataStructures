package com.atWSN.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: DataStructures
 * @description 线程池的基本使用
 * @author:戛剑生
 * @creat: 2021-03-09 20:13:23
 **/
public class Test01 {
    public static void main(String[] args) {
        //创建一个线程池
        //有5个线程
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //向线程池中提交任务
        for (int i = 0; i < 20; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + "编号的线程正在执行！开始时间" +System.currentTimeMillis() );
                    try {
                        Thread.sleep(3000);//模拟执行任务的时长
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
