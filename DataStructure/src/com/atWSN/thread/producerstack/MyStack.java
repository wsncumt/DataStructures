package com.atWSN.thread.producerstack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-07 08:08:29
 **/
public class MyStack {
    private List<String> list = new ArrayList<>();//定义一个集合模拟栈
    private static final int capacity = 2;//定义集合的最大容量
    private int size = 0;

    //定义方法模拟入栈
    public synchronized void push() {
        //栈中的数据已满，就等待
        while (list.size() >= capacity) {
            System.out.println("数据栈容量已满，无法存入数据，请等待！");
            System.out.println(Thread.currentThread().getName() + "开始等待！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束等待！");
        }
        String data = "data:" + new Random().nextInt(101);
        list.add(data);
        size++;
        System.out.println(Thread.currentThread().getName() + "添加了数据：" + data);
        this.notifyAll();
    }

    public synchronized void pop() {
        while (list.isEmpty()) {
            System.out.println("当前数据栈为空，无法取出数据！进入等待");
            System.out.println(Thread.currentThread().getName() + "开始等待");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束等待");
        }
        String data = list.remove(size - 1);
        size--;
        System.out.println(Thread.currentThread().getName() + "取出了数据：" + data);
        this.notifyAll();
    }
}
