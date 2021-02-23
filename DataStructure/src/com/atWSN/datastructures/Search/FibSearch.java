package com.atWSN.datastructures.Search;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FibSearch {
    public static void main(String[] args) {
//        int[] arr = {1,8,10,89,1000,1234,1235};
        int num = new Random().nextInt(100)+ 10;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = new Random().nextInt(100)+ 10;
        }
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("请输入要查找的数：");
        int target = new Scanner(System.in).nextInt();
        int ret = fibSearch(arr,target);
        System.out.println(ret);
    }

    //mid = low + fib(k - 1) - 1;
    public static int fibSearch(int[] arr,int target){
        int lo = 0;
        int hi = arr.length - 1;
        int len = arr.length;
        int k = 0;//表示斐波那契数列分割数值下标
        int mid = 0;
        int[] fib = fib(arr.length);//获取斐波那契数列
        //获取斐波那契数列分割数值的下标
        while (fib[k] - 1 < arr.length){
            k++;
        }
        //如果数组长度小于斐波那契数列数 - 1的值，把数组扩容至斐波那契数列数 - 1
        if (!(fib[k] - 1 == arr.length)){
            int[] arrTmp = Arrays.copyOf(arr,fib[k] - 1);
            for (int i = arr.length; i < fib[k] - 1; i++) {
                //扩容部分的数为原数组最后一个元素
                arrTmp[i] = arr[arr.length - 1];
            }
            arr = arrTmp;
        }
        while (lo <= hi) {
            //数组长度为fib(k) - 1
            //mid把数组分为左边是fib(k - 1) - 1
            //右边长度是fib(k - 2) - 1
            //fib(k) - 1 = fib(k - 1) - 1 + fib(k - 2) - 1 + 1
            mid = lo + fib[k - 1] - 1;

            if (target < arr[mid]) {
                hi = mid - 1;
                k--;
            } else if(arr[mid] < target) {
                lo = mid + 1;
                k -= 2;
            } else{
                if(mid >= len){
                    return len - 1;
                }
                return mid;
            }
        }
        return -1;
    }

    public static int[] fib(int n){
        int[] arr = new int[n];
        if (n == 1){
            arr[0] = 1;
            return arr;
        }
        arr[0] = 1;
        if (n == 2){
            arr[1] = 1;
            return arr;
        }
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
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
