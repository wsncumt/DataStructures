package com.atWSN.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: DataStructures
 * @description：在多线程环境中，把字符串转换为日期对象 多个线程使用同一个SimpleDateFormat对象可能会产生线程安全问题
 * 为每一个线程指定自己的SimpleDateFormat对象，使用ThreadLocal
 * @author:戛剑生
 * @creat: 2021-03-07 09:56:11
 **/
public class Test02 {
    //定义SimpleDateFormat对象，该对象可以字符串转换为日期
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    static class ParseDate implements Runnable {
        private int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                String str = (i % 60 < 10 ? ("0" + i % 60) : ("" + i % 60));
                String text = "2021年03月07日 09:56:" + str;//构建日期字符串
//                Date date = sdf.parse(text);//把字符串转换为日期
//                System.out.println(i + "-" + date);
                //先判断当前线程是否有SimpleDateFormat对象，如果当前线程没有SimpleDateFormat对象就创建一个，如果有就直接使用
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
                }
                Date date =threadLocal.get().parse(text);
                System.out.println(i + "-" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //创建100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(new ParseDate(i)).start();
        }
    }
}
