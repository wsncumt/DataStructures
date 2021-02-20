package com.atWSN.sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RadixSort {
    public static void main(String[] args) {
        System.out.println("请输入数据的规模：");
        int capacity = new Scanner(System.in).nextInt();
        unSortArray unSortArray = new unSortArray(capacity);
        System.out.println("------------------");
        System.out.println("排序前：");
        unSortArray.print();
        //排序
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间：" + date1Str);
        radixSort(unSortArray.arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + date2Str);
        System.out.println("------------------");
        System.out.println("排序后：");
        unSortArray.print();
    }
    public static void radixSort(int[] arr){
        //得到数组中最大的数
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
            if (arr[i] < min){
                min = arr[i];
            }
        }
        //得到最大位数
        int maxLength = ("" + max).length();
        //定义桶
        int[][] bucket = new int[10][arr.length];
        //记录每个桶中元素的个数
        int[] bucketElementCount = new int[10];
        for (int i = 0,n = 1; i < maxLength; i++ , n*=10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = (arr[j] - min) / n % 10;//找到所在的桶
                //第一个为所在桶的位置
                //第二个为所在桶的指针位置
                bucket[digitOfElement][bucketElementCount[digitOfElement]++] = arr[j];
            }

            //把桶中的数据取出放入原数组
            int index = 0;
            for (int j = 0; j < bucketElementCount.length; j++) {
                if (!(bucketElementCount[j] == 0)){
                    for (int k = 0; k < bucketElementCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                    //从桶中取出数据后，把该桶元素的个数归0
                    bucketElementCount[j] = 0;
                }
            }
        }
    }
}
