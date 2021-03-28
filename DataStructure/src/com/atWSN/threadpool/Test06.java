package com.atWSN.threadpool;


import java.util.concurrent.*;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-10 14:15:23
 **/
public class Test06 {
    //定义一个任务类
    private static class MyTask implements Runnable{
        private String taskName;

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(taskName + "正在被线程"+Thread.currentThread().getId()+"执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //定义扩展线程池
        //可以定义线程池类继承ThreadPoolExecutor
        //在子类中重写beforeExecute()/afterExecute()方法

        //也可以直接使用ThreadPoolExecutor内部类
        ExecutorService executorService = new ThreadPoolExecutor(5,5,0,TimeUnit.SECONDS,new LinkedBlockingQueue<>()){
            //在内部类中重写开始方法
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getId() + "线程准备执行任务"+((MyTask)r).getTaskName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask)r).getTaskName() + "任务执行完毕");
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出！");
            }
        };

        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("任务" + i);
            executorService.execute(task);
        }

        //关闭线程池
        //仅仅是说线程池不再接收新的任务，线程池中已接收的任务正常执行完毕
        executorService.shutdown();
    }
}
