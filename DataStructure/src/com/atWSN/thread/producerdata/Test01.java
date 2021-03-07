package com.atWSN.thread.producerdata;

import org.omg.CORBA.Object;

/**
 * @program: DataStructures
 * @description 测试一生产，一消费的情况
 * @author:戛剑生
 * @creat: 2021-03-06 21:32:37
 **/
public class Test01 {
    public static void main(String[] args) {
        ValueOP obj = new ValueOP();

        Thread producer = new ProducerThread(obj);
        Thread consumer = new ConsumerThread(obj);
        producer.setName("生产者线程");
        consumer.setName("消费者线程");
        producer.start();
        consumer.start();
        //结果：生产与消费交替运行

    }
}
