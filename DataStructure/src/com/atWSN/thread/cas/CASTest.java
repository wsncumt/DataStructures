package com.atWSN.thread.cas;

/**
 * @program: DataStructures
 * @description 实现一个线程安全的计数器
 * @author:戛剑生
 * @creat: 2021-03-05 10:17:19
 **/
public class CASTest {
    public static void main(String[] args) {
        CASCounter casCounter = new CASCounter();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "count" + casCounter.incrementAndGet());
                }
            }).start();

        }
        //System.out.println(casCounter.getValue());
    }
}

class CASCounter {
    //使用volatile修饰value值，使线程可见
    volatile private long value;

    public long getValue() {
        return value;
    }

    //定义一个compare and swap方法

    /**
     *
     * @param expectValue   是自增前主内存的值
     * @param newValue  自增后的值
     * @return
     */
    private boolean compareAndSwap(long expectValue, long newValue) {
        //如果value的值与期望的expectedValue值一样，就把当前的value字段替换为newValue值
        synchronized (this) {
            if (value == expectValue){
                this.value = newValue;
                return true;
            }
            return false;
        }
    }

    //定义自增的方法
    public long incrementAndGet(){
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue + 1;
        }while (!compareAndSwap(oldValue,newValue));
        return newValue;
    }
}
