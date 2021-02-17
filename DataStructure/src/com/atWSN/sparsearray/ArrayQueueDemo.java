package com.atWSN.sparsearray;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);
            switch(key){
                case 'a':
                    System.out.println("请输入一个数据：");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出！");
                    break;
                case 'g':
                    try{
                        int ret =arrayQueue.getQueue();
                        System.out.println("取出的数据是：" + ret);;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int ret =arrayQueue.headQueue();
                        System.out.println("头部的数据是：" + ret);;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    //表示数组的最大容量
    private int maxSize;
    //指向队首的指针
    private int front;
    //指向队尾的指针
    private int rear;
    private int[] arr;
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，指向队列头的前一个位置
        rear = -1;//指向队列尾，指向队列的最后一个数据
    }
    //判断队列是否为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满！不能加入！");
            return;
        }
        arr[++rear] = n;
    }
    //获取队列的数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空！不能取数据！");
        }
        return arr[++front];
    }
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空！");
            return;
        }
        System.out.print("[");
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(arr[i]);
            if (i!=rear){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    //显示队列头部的数据，不是取数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return arr[front + 1];
    }
}
