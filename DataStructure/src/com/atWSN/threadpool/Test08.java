package com.atWSN.threadpool;

import java.util.concurrent.*;

/**
 * @program: DataStructures
 * @description：自定义线程池类，对ThreadPoolExecutor进行扩展
 * @author:戛剑生
 * @creat: 2021-03-10 14:59:53
 **/
public class Test08 {
    //自定义线程池内，对任务进行包装
    //
    private static class TraceThreadPoolExecutor extends ThreadPoolExecutor{
        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        //定义方法，对执行的任务进行包装
        //接收两个参数：一是接收要执行的任务，二是Exception异常
        public Runnable wrap(Runnable r,Exception exception){
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        r.run();
                    }catch (Exception e){
                        exception.printStackTrace();
                        throw e;
                    }
                }
            };
        }
        //重写submit方法

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task,new Exception("客户跟踪异常")));
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command,new Exception("客户跟踪异常")));
        }
    }

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
//        //创建线程池
//        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0, TimeUnit.SECONDS,
//                new SynchronousQueue<>());
        //使用自定义线程池
        TraceThreadPoolExecutor traceThreadPoolExecutor = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0,
                TimeUnit.SECONDS,new SynchronousQueue<>());
        //向线程池添加计算两个数相除的任务
        for (int i = 0; i < 5; i++) {
            traceThreadPoolExecutor.submit(new DivideTask(10,i));
//            traceThreadPoolExecutor.execute(new DivideTask(10,i));
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
