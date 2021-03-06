package com.atWSN.thread.threadMethodDemo;

/**
 * 简易计时器的实现
 */
public class SimpleTimer {
    public static void main(String[] args) {
        int remaining = 10;
        for (; remaining > 0; remaining--) {
            System.out.println("Remaining：" + remaining);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!!");
    }
}
