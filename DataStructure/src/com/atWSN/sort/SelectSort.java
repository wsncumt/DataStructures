package com.atWSN.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SelectSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入问题的规模：");
        int num = scanner.nextInt();
        int[] arr = new int[num];
        //初始化数组
        initArr(arr);
        //排序前数组
        System.out.println("=================");
        System.out.print("排序前：");
        print(arr);
        //排序
        selectSort(arr);
        //排序后数组
        System.out.println("=================");
        System.out.print("排序后：");
        print(arr);
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] == min){
                    break;
                }else if (arr[j] < min){
                    min = arr[j];
                    index = j;
                }
            }
            if (!(i == index)){
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
            System.out.println("第" + (i + 1) +"轮排序");
            print(arr);
        }
    }

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static void initArr(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10) + 1;
        }
    }
}
