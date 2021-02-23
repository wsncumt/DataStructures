package com.atWSN.datastructures.tree;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(100) + 1;
        }
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        //先构建堆
        //从第一个叶子节点开始往前，直到根节点
        for (int j = (arr.length - 1 - 1) / 2; j >= 0; j--) {
            adjustHeap(arr, j, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            //上边构建堆后堆顶元素必然是最大，将其与尾元素进行交换
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            //交换后不满足堆序性，重新建堆
            adjustHeap(arr, 0, i);
        }
    }
    //构建堆

    /**
     * @param arr    要构建的数组
     * @param i      要调整的最后一个非叶子节点
     * @param length 要调整的数组长度
     */

    public static void adjustHeap(int[] arr, int i, int length) {
//先取出当前元素的值，保存在临时变量中
        int tmp = arr[i];
        //说明
        //1.k = i * 2 + 1是i节点的左子节点，
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {//判断右子节点是否存在
                //右子节点的值大于左子节点，把指针指向右子节点
                j++;
            }
            if (arr[j] > tmp) {//孩子大于该值，就下滤
                arr[i] = arr[j];
                i = j;//让i指向j继续比较
            } else {
                break;
            }
        }
        //for结束后，已经将以i为父节点的树的最大值放在了最顶上
        arr[i] = tmp;
    }
}
