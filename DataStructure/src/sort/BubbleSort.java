package sort;

import java.util.Arrays;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-24 19:12:27
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1,3,2,4,5,6};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr) {
        int index = arr.length - 1;
        while (index>0) {
            int last = 0;
            for (int i = 0; i < index ; i++) {
                if (arr[i] > arr[i+1]){
                    int tmp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = tmp;
                    last = i;
                }
            }
            index = last;
        }
    }
}
