package com.atWSN.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
//        queue.addQueue(10);
//        queue.addQueue(20);
//        queue.addQueue(30);
//        queue.addQueue(40);
//        queue.getQueue();
//        queue.addQueue(50);
//        queue.getQueue();
//        queue.addQueue(60);
//        queue.showQueue();
        //测试
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
                    queue.addQueue(scanner.nextInt());
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出！");
                    break;
                case 'g':
                    try{
                        int ret =queue.getQueue();
                        System.out.println("取出的数据是：" + ret);;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int ret =queue.headQueue();
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

class CircleArrayQueue{
    //表示数组的最大容量
    private int maxSize;
    //指向队首的指针
    private int front;
    //指向队尾的指针
    private int rear;
    private int[] arr;
    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize + 1;//要空出一个空间，所以要+1
        arr = new int[this.maxSize];
        front = 0;//指向队列头部，指向队列第一个元素
        rear = 0;//指向队列尾，指向队列的最后一个数据的下一个位置
    }

    //判断队列是否为满
    public boolean isFull(){
        //数组的一个空间是不存元素的
        //假设front在开头，即下标为0；rear在队列的最后，也就是数组的最后一个位置，下标为maxSize - 1
        //此时队列是满的，有(rear + 1) % maxSize == front
        return (rear + 1) % maxSize  == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空！不能取数据！");
        }
        //1.先把front对应的值保留到临时变量中
        //2.把front后移
        //3.返回临时变量保存的值
        int ret =  arr[front];
        front = (front + 1) % maxSize;
        return ret;
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空！");
            return;
        }
        System.out.print("[");
        for (int i = front; i < front + getSize(); i++) {
            System.out.print(arr[i % maxSize]);
            if (i!=front + getSize() - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    //求出当前队列有效数据的个数


    public int getSize() {
        return (rear + maxSize - front) % maxSize;//rear可能会位于front的前边，此时为负数，所以要加上maxSize
    }

    //显示队列头部的数据，不是取数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }
}