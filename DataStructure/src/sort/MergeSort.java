package sort;

import com.atWSN.datastructures.Search.Array;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-24 20:34:27
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[12];
        mySort(arr);
//        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right){
            return;
        }
        int mid = left + ((right - left)>>1);
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    private static void merge(int[] arr,int left, int mid, int right) {
        if (left >= right || mid>=right){
            return;
        }
        int[] tmpArr = new int[mid - left + 1];
        for (int i = 0; i < tmpArr.length; i++) {
            tmpArr[i] = arr[left+i];
        }
        int i = 0;
        int j = mid + 1;
        int index = left;
        while(i <tmpArr.length && j <= right){
            if (tmpArr[i] <= arr[j]){
                arr[index++] = tmpArr[i++];
            }else{
                arr[index++] = arr[j++];
            }
        }
        while (i<tmpArr.length){
            arr[index++] = tmpArr[i++];
        }
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
        mergeSort1(arr);
        System.out.println("排序后：");
        print(arr);
    }

    public static void mergeSort1(int[] arr){
        for (int step = 1;step < arr.length;step *= 2){
            for (int i = 0; i < arr.length; i+= 2*step) {
                int mid = i+step -1 >= arr.length ? arr.length-1:i+step -1;
                int right = i+2*step-1>= arr.length ? arr.length-1:i+2*step-1;
                merge(arr,i,mid,right);
            }
        }
    }
}
