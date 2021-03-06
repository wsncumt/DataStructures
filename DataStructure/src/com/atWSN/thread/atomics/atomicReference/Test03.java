package com.atWSN.thread.atomics.atomicReference;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: DataStructures
 * @description 使用AtomicStampedReference解决ABA问题
 * 在AtomicStampedReference有一个整数标记值stamp
 * 每次执行CAS操作时，都要对比它的版本，即比较stamp的值
 * @author:戛剑生
 * @creat: 2021-03-05 17:03:16
 **/
public class Test03 {
    //    private static AtomicReference<String> atomicReference
//            = new AtomicReference<>("abc");
    //定义AtomicStampedReference引用操作"abc"字符串，指定初始化版本号为0
    private static AtomicStampedReference<String> atomicStampedReference
            = new AtomicStampedReference<>("abc", 0);

    public static void main(String[] args) {
//创建第一个线程，先把abc字符串改为def再把字符串还原为abc
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //每次修改，版本号加1
                atomicStampedReference.compareAndSet
                        ("abc", "def",
                                atomicStampedReference.getStamp(),
                        atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName()
                        + "--" + atomicStampedReference.getReference());
                atomicStampedReference.compareAndSet("def",
                        "abc", atomicStampedReference.getStamp(),
                        atomicStampedReference.getStamp() + 1);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();//获得版本号
                try {
                    TimeUnit.SECONDS.sleep(1l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(atomicStampedReference.
                        compareAndSet("abc",
                                "xyz",stamp,stamp+1));
            }
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicStampedReference.getReference());
    }
}
