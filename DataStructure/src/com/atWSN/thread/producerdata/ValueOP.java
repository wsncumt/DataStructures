package com.atWSN.thread.producerdata;

/**
 * @program: DataStructures
 * @description
 *      定义一个操作数据的类
 * @author:戛剑生
 * @creat: 2021-03-06 21:33:03
 **/
public class ValueOP {
    private String  value = "";
    //定义方法修改value字段的值
    public void setValue(){
        //如果value不是空串""，就等待
        synchronized (this){
            while (!value .equals("")){
                try {
                    System.out.println("当前有数据，无需生产");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果是空串，就设置value字段的值
            String value = System.currentTimeMillis()+" - "+System.nanoTime();
            this.value = value;
            System.out.println("set设置的值是：" + value);
            System.out.println("生产成功");
            System.out.println();
            this.notifyAll();
        }
    }
    //定义方法读取value字段的值
    public void getValue(){
        synchronized (this){
            //如果字符串是空串就等待
            while (value .equals("")){
                System.out.println("没有数据可以取" + Thread.currentThread().getName() + "等待");
                System.out.println();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //不是空串，读取字段的值
            System.out.println("get的值是：" + this.value);
            System.out.println("取数据结束");
            System.out.println();
            this.value = "";
            this.notifyAll();
        }
    }
}
