package com.atWSN.datastructures.sort;

import java.util.Scanner;

public class ShellSort {
    public static void main(String[] args) {
        System.out.println("请输入问题的规模：");
        int capacity = new Scanner(System.in).nextInt();
        unSortArray unSortArray = new unSortArray(capacity);
        System.out.println("排序前：");
        unSortArray.print();
        //排序
        shellSort2(unSortArray.arr);
        System.out.println("------------------");
        System.out.println("排序后：");
        unSortArray.print();
    }

    private static void shellSort1(int[] arr) {
        if (arr.length < 2){
            return;
        }
        for (int step = arr.length/2; step > 0 ; step /= 2) {
            //遍历各组中所有元素，每组元素有step个
            for (int i = step; i < arr.length; i++) {
                //交换法：从每组的倒数第二个元素开始，与它的后一个元素比较，若大于就交换，每次向前移动
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]){
                        int tmp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp;
                    }
                }
            }
        }

    }

    //优化shell排序：移位法
    public static void shellSort2(int[] arr){
        if(arr.length < 2){
            return;
        }
        for (int step = arr.length/2; step > 0; step /= 2) {
            //step表示组数
            //从第step个元素开始，对其所在的组进行插入排序
            for (int i = step; i < arr.length; i++) {
                int j = i - step;
                int value = arr[i];
                while (j >= 0 && arr[j] > value){
                    arr[j + step] = arr[j];
                    j -= step;
                }
                if(!(j == i - step)){
                    arr[j + step] = value;
                }

            }
        }
    }

}
