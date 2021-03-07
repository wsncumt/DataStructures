package com.atWSN.thread.producerdata;

/**
 * @program: DataStructures
 * @description
 *      生产者
 * @author:戛剑生
 * @creat: 2021-03-06 21:44:04
 **/
public class ProducerThread extends Thread{
    private ValueOP obj;

    public ProducerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + "正在生产");
            obj.setValue();
        }
    }
}
