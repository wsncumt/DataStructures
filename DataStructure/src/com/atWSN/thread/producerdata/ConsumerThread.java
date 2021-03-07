package com.atWSN.thread.producerdata;

/**
 * @program: DataStructures
 * @description
 *      消费者
 * @author:戛剑生
 * @creat: 2021-03-06 21:44:04
 **/
public class ConsumerThread extends Thread{
    //消费者使用数据，就是使用ValueOP类的value字段值
    private ValueOP obj;

    public ConsumerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + "正在消费");
            obj.getValue();
        }
    }
}
