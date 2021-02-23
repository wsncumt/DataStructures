package com.atWSN.datastructures.sort;

import java.util.Scanner;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
        System.out.println("请输入数据的规模：");
        int capacity = new Scanner(System.in).nextInt();
        unSortArray unSortArray = new unSortArray(capacity);
        System.out.println("------------------");
        System.out.println("排序前：");
        unSortArray.print();
        //排序
        insertSort(unSortArray.arr);
        System.out.println("------------------");
        System.out.println("排序后：");
        unSortArray.print();
    }
    //插入排序
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int index = i;
            for (int j = 0; j < i; j++) {
                if (arr[j] > insertVal){
                    index = j;
                    break;
                }
            }
            if (!(index == i)) {
                for (int j = i; j > index; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[index] = insertVal;
            }
        }
    }

    //版本2
    public static void insertSort1(int[] arr){
        if (arr.length <= 1){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            int insertValue = arr[i];
            while(index >= 0 && insertValue < arr[insertValue]){
                arr[index + 1] = arr[index];
                index--;
            }
            if (!(index == i - 1)) {
                arr[index] = insertValue;
            }
        }
    }
}
