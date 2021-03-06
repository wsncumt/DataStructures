package com.atWSN.thread.atomics.atomicIntegerFieldUpdater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-05 16:08:57
 **/
public class SubThread extends Thread{
    private User user;//要更新的User对象
    //创建一个AtomicIntegerFieldUpdater更新器
    //AtomicIntegerFieldUpdater是抽象类，不能直接创建对象
    private AtomicIntegerFieldUpdater<User> updater =
            AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public SubThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        //在子线程中对user对象的age字段自增
        for (int i = 0; i < 10; i++) {
            System.out.println(updater.getAndIncrement(user));
        }
    }
}
