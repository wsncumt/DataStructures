package com.atWSN.thread.volatilekw;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-05 08:58:19
 **/
public class Test02 {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        //开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printString();
            }
        }).start();

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
        //程序运行后，可能出现死循环情况
        //分析原因：main线程修改打印标志后，子线程读不到
        //解决办法：使用volatile关键字修饰该打印标志
        //   volatile关键字强制线程从公共内存中读取变量的值而不是从工作内存中读取
    }

    /**
     * 定义一个类打印字符串
     */
    static class PrintString {
        private volatile boolean continuePrint = true;

        public void printString() {
            while (continuePrint) {
//                System.out.println(Thread.currentThread().getName());
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }
    }
}
