package com.atWSN.datastructures.Search;

import java.util.Arrays;
import java.util.Scanner;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = new Array(10).arr;
        System.out.println(Arrays.toString(arr));
        System.out.println("请输入要查找的数：");
        int num = new Scanner(System.in).nextInt();
        int ret = seqSearch(arr,num);
        if (ret == -1){
            System.out.println("未找到！");
        }else {
            System.out.println("该元素下标为" + ret);
        }

    }
    //找到第一个满足条件的下标
    public static  int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
