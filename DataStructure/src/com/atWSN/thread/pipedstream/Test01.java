package com.atWSN.thread.pipedstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @program: DataStructures
 * @description：使用PipedInputStream和PipedOutputStream管道字节流在线程之间传递数据
 * @author:戛剑生
 * @creat: 2021-03-07 08:58:15
 **/
public class Test01 {
    public static void main(String[] args) {
        //定义管道字节流
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        try{
            pipedInputStream.connect(pipedOutputStream);
            //创造线程向管道流中写入数据

            new Thread(new Runnable(){
                @Override
                public void run() {
                    writeData(pipedOutputStream);
                }
            }).start();

            new Thread(new Runnable(){
                @Override
                public void run() {
                    readData(pipedInputStream);
                }
            }).start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
                pipedOutputStream.close();
                pipedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    //定义方法向管道流中写入数据
    //从线程中把数据写入管道，需要一个输出流管道用于接收
    public static void writeData(PipedOutputStream out) {
        //分别把0~100之间的数写入管道中
        try {
            for (int i = 0; i < 100; i++) {
                String data = "" + (i + 1);
                out.write(data.getBytes());//把字节数组写入到输出管道流中
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();//关闭管道流
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //定义方法从管道流中读取数据
    public static void readData(PipedInputStream in) {
        try {
            byte[] bytes = new byte[1024 * 4];
            int len = 0;
            while (true) {
                len = in.read(bytes);
                if (len == -1){
                    break;
                }
                System.out.println(new String(bytes,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
