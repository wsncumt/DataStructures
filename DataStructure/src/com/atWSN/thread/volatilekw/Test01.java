package com.atWSN.thread.volatilekw;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-05 08:58:19
 **/
public class Test01 {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printString();
        //main线程睡眠
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //在main线程中修改打印标志
        System.out.println("修改打印标志");
        printString.setContinuePrint(false);
        //修改完打印标志后，运行程序，查看程序运行结果
        //程序根本不会停止运行，因为调用printString.printString()后
        // 该方法一直处于死循环状态
        // 程序根本执行不到printString.setContinuePrint(false);
        // 解决方法：使用多线程技术
    }

    /**
     * 定义一个类打印字符串
     */
    static class PrintString {
        private boolean continuePrint = true;

        public void printString() {
            while (continuePrint) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }
    }
}
