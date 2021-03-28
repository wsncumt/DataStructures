package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-22 20:06:12
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        mySort(arr);
    }

    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int val = arr[i];
                int j = i - gap;
                for (; j >= 0; j -= gap) {
                    if (arr[j] > val) {
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + gap] = val;
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
        shellSort(arr);
        System.out.println("排序后：");
        print(arr);
    }
}
