package com.atWSN.Search;

import java.util.Arrays;
import java.util.Random;

public class Array {
    private final int capacity;
    public int[] arr;
    Array(int capacity){
        this.capacity = capacity;
        arr = new int[this.capacity];
        initArray();
    }

    private void initArray() {
        Random random = new Random();
        for (int i = 0; i < this.capacity; i++) {
            this.arr[i] = random.nextInt(10000) - 5000 + 1;
        }
    }

    public void sort(){
        Random random = new Random();
        int i = random.nextInt(3);
        switch (i) {
            case 0:
                mergeSort(0, capacity - 1);
                break;
            case 1:
                quickSort(0,capacity - 1);
                break;
            case 2:
                radixSort();
                break;
        }
    }

    private void mergeSort( int left, int right) {

        if (right - left == 0) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    /**
     * @param left  左边有序的左边界
     * @param mid   左边有序的右边界，右边有序的左边界的前一个位置
     * @param right 右边有序的右边界
     */
    private  void merge( int left, int mid, int right) {
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

    private void quickSort (int lo,int hi){
        if(hi - lo < 1){
            return;
        }
        int mid = partition(lo, hi);//mid位置处的元素已就位，即轴点。
        quickSort(lo,mid - 1);//前缀排序
        quickSort(mid + 1,hi);//后缀排序
    }
    //构造轴点
    private int partition(int lo, int hi) {
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

    private void radixSort(){
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
    public void print(){
        System.out.println(Arrays.toString(arr));
    }
}
