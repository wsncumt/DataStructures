package com.atWSN.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入问题的规模：");
        int num = scanner.nextInt();
        int[] arr = new int[num];
        //初始化数组
        initArr(arr);
        //排序前数组
//        System.out.println("=================");
//        System.out.print("排序前：");
//        print(arr);
        //排序
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间：" + date1Str);
        bubbleSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + date2Str);
        //排序后数组
//        System.out.println("=================");
//        System.out.print("排序后：");
//        print(arr);
    }

    private static void bubbleSort(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    flag = false;
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
           // System.out.println(Arrays.toString(arr));
            if (flag){
                break;
            }
        }
    }

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static void initArr(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000) - 1000;
        }
    }
}
