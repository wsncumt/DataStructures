package com.atWSN.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: DataStructures
 * @description 线程池的计划任务
 * @author:戛剑生
 * @creat: 2021-03-09 20:23:24
 **/
public class Test02 {

    public static void main(String[] args) {
        //创建一个有调度功能的线程池
        //参数1：Runnable任务，参数2：延迟时间，参数3：时间单位
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //在延迟两秒后执行任务
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "---" + System.currentTimeMillis());
            }
        },2, TimeUnit.SECONDS);
//        //以固定的频率执行任务
//        //在3秒后执行任务，以后每隔5秒重新执行一次
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getId() + "---以固定频率开启任务：" +System.currentTimeMillis());
//                try {
//                    TimeUnit.SECONDS.sleep(1);//睡眠模拟任务执行时间
//                    //如果任务执行时长超过了时间间隔，则任务完成后立即开启下一个任务。
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },3,5,TimeUnit.SECONDS);


        //在上次任务结束后在固定的延迟后再执行该任务
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "---以固定频率开启任务：" +System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(1);//睡眠模拟任务执行时间
                    //如果任务执行时长超过了时间间隔，则任务完成后立即开启下一个任务。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },3,5,TimeUnit.SECONDS);//不管任务耗时多长
        // 总是在任务结束后的2秒内再次开启新的任务

    }
}
