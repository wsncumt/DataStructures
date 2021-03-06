package com.atWSN.thread.atomics.atomiclong;

import java.util.Random;

/**
 * @program: DataStructures
 * @description
 *      模拟服务器的请求总数，处理成功数，处理失败数
 * @author:戛剑生
 * @creat: 2021-03-05 14:35:15
 **/
public class Test {
    public static void main(String[] args) {
        //通过线程模拟请求，在实际应用中可以在ServletFilter中调用Indicator计数器相关方法
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程就是一个请求
                    //请求总数要加1
                    Indicator.getInstance().newRequestReceive();
                int num = new Random().nextInt( );
                if (num % 2 == 0){//用随机数模拟请求成功或失败，
                    //偶数表示成功
                    Indicator.getInstance().requestProcessSuccess();
                }else{
                    Indicator.getInstance().requestProcessFail();
                }
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("发起的总请求数：" + Indicator.getInstance().getRequestCount());
        System.out.println("发起的成功请求数：" + Indicator.getInstance().getSuccessCount());
        System.out.println("发起的失败请求数：" + Indicator.getInstance().getFailCount());
    }
}
