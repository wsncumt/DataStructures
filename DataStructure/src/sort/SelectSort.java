package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-22 20:54:54
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        mySort(arr);
    }
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }
            if(i!=index) {
                int tmp = arr[i];
                arr[i] = min;
                arr[index] = tmp;
            }
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
        selectSort(arr);
        System.out.println("排序后：");
        print(arr);
    }
}
