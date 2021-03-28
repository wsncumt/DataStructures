package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-24 19:32:33
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        mySort(arr);
    }
    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int left,int right){
        if(left >= right){
            return;
        }
        int mid = partition(arr,left,right);
        quickSort(arr,left,mid-1);
        quickSort(arr,mid+1,right);
    }

    private static int partition(int[] arr, int left, int right) {
        int lo = left;
        int hi = right;
        int index = arr[lo] > arr[hi] ? (arr[lo] > arr[lo + ((hi-lo)>>1)] ? lo :lo + ((hi-lo)>>1)):hi;
        int val = arr[lo];
        while(lo < hi){
            while(lo < hi && arr[hi] >= val){
                hi--;
            }
            if (lo < hi){
                arr[lo] = arr[hi];
                lo++;
            }
            while(lo<hi&&arr[lo] <= val){
                lo++;
            }
            if (lo < hi){
                arr[hi] = arr[lo];
                hi--;
            }
        }
        arr[lo] = val;
        return lo;
    }

    public static  void initArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(arr.length * 10);
        }
    }

    public static void print(int[] arr){
        String str = Arrays.toString(arr);
        System.out.println(str);
    }

    public static void mySort(int[] arr){
        initArr(arr);
        System.out.println("排序前：");
        print(arr);
        quickSort1(arr);
        System.out.println("排序后：");
        print(arr);
    }

    public static void quickSort1(int[] arr){
        //创建一个栈
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length-1);
        while (!stack.isEmpty()){
            int right = stack.pop();
            int left = stack.pop();
            if (left>=right){
                continue;
            }
            int mid = partition(arr,left,right);
            stack.push(left);
            stack.push(mid -1);
            stack.push(mid+1);
            stack.push(right);
        }
    }
}
