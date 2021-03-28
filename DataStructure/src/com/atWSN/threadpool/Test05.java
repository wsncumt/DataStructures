package com.atWSN.threadpool;

import java.util.concurrent.*;

/**
 * @program: DataStructures
 * @description：监控线程池
 * @author:戛剑生
 * @creat: 2021-03-10 10:29:21
 **/
public class Test05 {
    public static void main(String[] args) {
        //定义任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "编号的线程开始执行。"+System.currentTimeMillis());

                try {
                    Thread.sleep(10000);//线程睡眠10秒模拟任务执行时长
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        //向线程池提交30个任务
        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.submit(r);
            System.out.println("当前线程池核心线程数：" + threadPoolExecutor.getCorePoolSize()+",最大线程数" + threadPoolExecutor.getMaximumPoolSize() + ",当前线程池大小：" + threadPoolExecutor.getPoolSize()+",活动线程数大小："+threadPoolExecutor.getActiveCount()+",共收到任务数："+threadPoolExecutor.getTaskCount()+",完成任务数："+threadPoolExecutor.getCompletedTaskCount()+",等待任务数：" + threadPoolExecutor.getQueue().size());
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("------------------------------------------------------------------------");
        while (threadPoolExecutor.getActiveCount()>=0){
            System.out.println("当前线程池核心线程数：" + threadPoolExecutor.getCorePoolSize()+",最大线程数" + threadPoolExecutor.getMaximumPoolSize() + ",当前线程池大小：" + threadPoolExecutor.getPoolSize()+",活动线程数大小："+threadPoolExecutor.getActiveCount()+",共收到任务数："+threadPoolExecutor.getTaskCount()+",完成任务数："+threadPoolExecutor.getCompletedTaskCount()+",等待任务数：" + threadPoolExecutor.getQueue().size());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
