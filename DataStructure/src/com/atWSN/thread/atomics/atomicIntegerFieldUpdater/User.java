package com.atWSN.thread.atomics.atomicIntegerFieldUpdater;

/**
 * @program: DataStructures
 * @description
 *      使用AtomicIntegerFieldUpdater更新的字段必须使用volatile修饰
 * @author:戛剑生
 * @creat: 2021-03-05 15:55:12
 **/
public class User {
    int id;
    volatile int age;

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
