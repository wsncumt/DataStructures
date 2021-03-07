package com.atWSN.thread.producerstack;

/**
 * @program: DataStructures
 * @description 生产者
 * @author:戛剑生
 * @creat: 2021-03-07 08:22:10
 **/
public class ProducerThread extends Thread{
    private MyStack myStack;
    public ProducerThread(MyStack myStack){
        this.myStack = myStack;
    }
    @Override
    public void run() {
        while (true){
            myStack.push();
        }
    }
}
