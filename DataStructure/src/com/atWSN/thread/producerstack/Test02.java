package com.atWSN.thread.producerstack;



/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-07 08:08:06
 **/
public class Test02 {
    public static void main(String[] args) {
        MyStack obj = new MyStack();

        Thread producer1 = new ProducerThread(obj);
        Thread producer2 = new ProducerThread(obj);
        Thread producer3 = new ProducerThread(obj);
        Thread producer4 = new ProducerThread(obj);

        Thread consumer1 = new ConsumerThread(obj);
        Thread consumer2 = new ConsumerThread(obj);
        Thread consumer3 = new ConsumerThread(obj);
        Thread consumer4 = new ConsumerThread(obj);
        Thread consumer5 = new ConsumerThread(obj);
        Thread consumer6 = new ConsumerThread(obj);

        producer1.setName("生产者线程1");
        producer2.setName("生产者线程2");
        producer3.setName("生产者线程3");
        producer4.setName("生产者线程4");
        consumer1.setName("消费者线程1");
        consumer2.setName("消费者线程2");
        consumer3.setName("消费者线程3");
        consumer4.setName("消费者线程4");
        consumer5.setName("消费者线程5");
        consumer6.setName("消费者线程6");
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        consumer6.start();
        //结果：生产与消费交替运行
    }
}
