package com.atWSN.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class QuickSort {
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
        quickSort(unSortArray.arr,0,unSortArray.arr.length - 1);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + date2Str);
        System.out.println("------------------");
        System.out.println("排序后：");
        unSortArray.print();
    }

    public static void quickSort (int[] arr, int lo,int hi){
        if(hi - lo < 1){
            return;
        }
        int mid = partition(arr,lo, hi);//mid位置处的元素已就位，即轴点。
        quickSort(arr,lo,mid - 1);//前缀排序
        quickSort(arr,mid + 1,hi);//后缀排序
    }
    //构造轴点
    private static int partition(int[] arr,int lo, int hi) {
        int left = lo;//左指针
        int right = hi;//右指针
        int value = arr[lo];//轴点的值，一般取区间第一个
        while (left < right) {
            //如果右边的值不小于轴点，右指针左移
            while((left < right) && value <= arr[right]){
                right--;
            }
            //右边的值小于轴点的值，取出该值放入左指针所指的值，同时左指针右移一个单位
            if(left < right){
                arr[left] = arr[right];
                left++;
            }
            //如果左指针指的值不大于轴点值，左指针右移
            while((left < right) && value >= arr[left]){
                left++;
            }
            //左指针的值大于轴点的值，取出该值放入右指针所指的位置，同时右指针左移
            if(left < right){
                arr[right] = arr[left];
                right--;
            }
        }
        //循环退出的条件是左指针右指针重合，
        // 其含义表示该位置左边的值都小于轴点，
        // 该位置右边的值都大于轴点
        //把轴点的值放入该位置，同时返回该位置。
        arr[left] = value;
        return left;
    }
}
