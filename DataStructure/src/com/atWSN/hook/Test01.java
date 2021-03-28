package com.atWSN.hook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @program: DataStructures
 * @description：通过Hook线程防止程序重复启动
 * @author:戛剑生
 * @creat: 2021-03-09 16:05:07
 **/
public class Test01 {
    public static void main(String[] args) {
      //1.注入Hook线程，程序退出时删除.lock文件
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
          @Override
          public void run() {
              System.out.println("JVM退出，会启动当前Hook线程，在Hook线程中删除.lock文件");
              getLockFile().toFile().delete();
          }
      }));

      //2程序运行时检查lock文件是否存在，如果lock文件存在则抛出异常
        if (getLockFile().toFile().exists()){
            throw new RuntimeException("程序已启动");
        }else{
            //文件不存在，说明程序是第一次启动
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序启动时，创建了lock文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //模拟程序运行
        for (int i = 0; i < 10; i++) {
            System.out.println("程序正在运行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Path getLockFile(){
        return Paths.get("","tmp.lock");
    }
}
