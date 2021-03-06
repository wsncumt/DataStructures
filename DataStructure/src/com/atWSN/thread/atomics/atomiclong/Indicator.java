package com.atWSN.thread.atomics.atomiclong;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: DataStructures
 * @description 使用原子变量类定义一个计数器
 * 该计数器在整个程序中都可以使用，所有的地方都使用这一个计数器
 * 这个计数器就可以设计为单例
 * @author:戛剑生
 * @creat: 2021-03-05 14:38:30
 **/
public class Indicator {
    //构造方法私有化
    private Indicator() {
    }

    //定义一个私有的本类静态的对象
    private static final Indicator INSTANCE = new Indicator();

    //提供一个公共静态的方法返回该类唯一实例
    public static Indicator getInstance() {
        return INSTANCE;
    }

    //使用源自变量类保存请求总数
    private final AtomicLong requestCount = new AtomicLong(0);//记录请求总数
    private final AtomicLong successCount = new AtomicLong(0);//记录处理成功总数
    private final AtomicLong failCount = new AtomicLong(0);//记录处理失败总数

    //有新的请求
    public void newRequestReceive() {
        requestCount.incrementAndGet();
    }

    //处理成功的
    public void requestProcessSuccess() {
        successCount.incrementAndGet();
    }

    //处理失败的
    public void requestProcessFail() {
        failCount.incrementAndGet();
    }

    //查看总数，成功数，失败数
    public long getRequestCount() {
        return requestCount.get();
    }

    public long getSuccessCount() {
        return successCount.get();
    }

    public long getFailCount() {
        return failCount.get();
    }
}
