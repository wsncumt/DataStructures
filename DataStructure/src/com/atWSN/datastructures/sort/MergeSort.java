package com.atWSN.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        System.out.println("请输入数据的规模：");
        int capacity = new Scanner(System.in).nextInt();
        unSortArray unSortArray = new unSortArray(capacity);
//        System.out.println("------------------");
//        System.out.println("排序前：");
//        unSortArray.print();
        //排序
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间：" + date1Str);
        mergeSort(unSortArray.arr,0,unSortArray.arr.length - 1);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + date2Str);
//        System.out.println("------------------");
//        System.out.println("排序后：");
//        unSortArray.print();
//        int[] arr = {1, 8, 4, 7, 3, 6, 5, 2};
//        mergeSort(arr, 0,  arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {

        if (right - left == 0) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * @param arr   待排序的数组
     * @param left  左边有序的左边界
     * @param mid   左边有序的右边界，右边有序的左边界的前一个位置
     * @param right 右边有序的右边界
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = Arrays.copyOfRange(arr, left, mid + 1);
        int lo1 = 0;
        int lo2 = mid + 1;
        int i = left;
        while (lo1 < tmp.length && lo2 <= right) {
            if (tmp[lo1] <= arr[lo2]) {
                arr[i++] = tmp[lo1++];
            } else {
                arr[i++] = arr[lo2++];
            }
        }
        while (i <= right) {
            if (lo1 < tmp.length) {
                arr[i++] = tmp[lo1++];
            }
            if (lo2 <= right) {
                break;
            }
        }
    }
}
