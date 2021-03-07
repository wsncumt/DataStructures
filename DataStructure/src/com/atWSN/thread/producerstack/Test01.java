package com.atWSN.thread.producerstack;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-07 08:08:06
 **/
public class Test01 {
    public static void main(String[] args) {
        MyStack obj = new MyStack();

        Thread producer = new ProducerThread(obj);
        Thread consumer = new ConsumerThread(obj);
        producer.setName("生产者线程");
        consumer.setName("消费者线程");
        producer.start();
        consumer.start();
        //结果：生产与消费交替运行
    }
}
