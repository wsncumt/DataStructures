package com.atWSN.threadpool;

import com.atWSN.datastructures.Search.Array;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: DataStructures
 * @description：演示ForkJoinPool线程池的使用
 * 使用线程池模拟数列求和
 * @author:戛剑生
 * @creat: 2021-03-10 15:56:49
 **/
public class Test09 {
    //计算数列的和需要返回结果
    //可以定义一个任务继承RecursiveTask
private static class CountTask extends RecursiveTask<Long>{
        private static final int THRESHOLD = 10000;//定义数据规模的阈值
        //即允许计算10000个数内的和,
        //超过该阈值数列就要分解

        private long start;//计算数列的起始值
        private long end;//计算数列的结束值

        //约定每次分解为100个小任务
        private static final int TASKNUM = 100;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        //重写RecursiveTask的compute()
        @Override
        protected Long compute() {
            long sum = 0;
            //判断任务是否需要继续分解
            //当前数列end与start范围的数据超过阈值
            //就需要继续分解
            if (end - start < THRESHOLD){
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            }else{
                //继续分解
                //约定每次分解为100个小任务，计算每个任务的计算量
                long step = (end - start + 1)/TASKNUM;
                //创建一个任务集合
                ArrayList<CountTask> subTaskList = new ArrayList<>();
                long pos = start;//任务的起始位置
                for (int i = 0; i < TASKNUM; i++) {
                    long lastOne = pos + step;//每个任务的结束位置
                    if (lastOne > end){
                        lastOne = end;
                    }
                    //创建子任务
                    CountTask countTask = new CountTask(pos,lastOne);
                    //把任务添加到集合中
                    subTaskList.add(countTask);
                    //调用fork提交子任务
                    countTask.fork();
                    //调整下个任务的起始位置
                    pos += step +1;
                }
                //等待所有的任务结束后，合并计算结果
                for (CountTask task: subTaskList
                     ) {
                    sum += task.join();
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        //创建ForkJoinPool线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //创建一个大的任务
        CountTask task = new CountTask(0l,20003l);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            Long res = result.get();//调用任务的get()方法返回结果
            System.out.println("计算数列结果为："+res);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
