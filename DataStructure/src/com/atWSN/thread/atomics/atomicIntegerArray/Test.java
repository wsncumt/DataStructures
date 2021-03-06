package com.atWSN.thread.atomics.atomicIntegerArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @program: DataStructures
 * @description
 *      AtomicIntegerArray:原子更新数组
 * @author:戛剑生
 * @creat: 2021-03-05 15:13:32
 **/
public class Test {
    public static void main(String[] args) {
        //1.创建一个指定长度的数组
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        System.out.println(atomicIntegerArray);

        //2.设置指定位置的元素
        atomicIntegerArray.set(5,10);
        //设置数组元素的新值时，同时返回数组元素的旧值
        int res = atomicIntegerArray.getAndSet(5,12);
        System.out.println(res);
        //3.返回指定位置的元素
        System.out.println(atomicIntegerArray.get(5));

        //4.修改数组元素的值：把数组元素加上某个值
        atomicIntegerArray.addAndGet(9,5);//先加在返回，第一个为元素下标，第二个为加的数
        System.out.println(atomicIntegerArray);
        atomicIntegerArray.getAndAdd(9,-6);//先返回再加
        System.out.println(atomicIntegerArray);

        //5.CAS操作
        //如果数组中索引为5的元素的值是12，就把其修改为10
        System.out.println(atomicIntegerArray.compareAndSet(5,12,10));

        //6.自增自减操作
        //自增
        atomicIntegerArray.incrementAndGet(0);
        atomicIntegerArray.getAndIncrement(0);
        //自减
        atomicIntegerArray.decrementAndGet(0);
        atomicIntegerArray.getAndDecrement(0);
    }
}
