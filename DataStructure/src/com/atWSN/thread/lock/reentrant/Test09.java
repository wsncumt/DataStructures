package com.atWSN.thread.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: DataStructures
 * @description：使用tyrLock()可以避免死锁
 * @author:戛剑生
 * @creat: 2021-03-08 14:46:57
 **/
public class Test09 {
    static class IntLock implements Runnable {
        private static ReentrantLock lock1 = new ReentrantLock();
        private static ReentrantLock lock2 = new ReentrantLock();
        private int lockNum;

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            if (lockNum % 2 == 0) {//偶数先获得锁1再获得锁2
                while (true) {
                    try {
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁1，还未获得锁2");
                            Thread.sleep(new Random().nextInt(10));
                            try {
                                if (lock2.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "同时获得锁1获得锁2");
                                    return;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock2.isHeldByCurrentThread()) {
                                    lock2.unlock();
                                }
                            }
//                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock1.isHeldByCurrentThread()) {
                            lock1.unlock();
                        }
                    }
                }
            } else {//奇数先获得锁2再获得锁1
                while (true) {
                    try {
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁2，还未获得锁1");
                            Thread.sleep(new Random().nextInt(10));
                            try {
                                if (lock1.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "同时获得锁1获得锁2");
                                    return;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock1.isHeldByCurrentThread()) {
                                    lock1.unlock();
                                }
                            }
//                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock2.isHeldByCurrentThread()) {
                            lock2.unlock();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);
        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
