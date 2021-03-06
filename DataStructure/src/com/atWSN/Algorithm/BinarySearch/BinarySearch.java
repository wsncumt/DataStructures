package com.atWSN.Algorithm.BinarySearch;

/**
 * 二分查找的算法
 * <p>
 * 要求：使用迭代的方式来完成
 */
public class BinarySearch {
    public static void main(String[] args) {

    }
    //二分查找的非递归实现

    /**
     * 功能：二分查找
     * 要求：迭代完成
     *
     * @param arr    从arr中查找,有序数组
     * @param target 查找元素target
     * @return 找到返回下标，没找到返回-1
     * @author 王松年
     */
    public static int binarySearch(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            if (arr[mid] == target){
                return mid;
            }else if(target < arr[mid]){
                hi = mid - 1;
            }else{
                lo = mid + 1;
            }
        }
        return -1;
    }

    //二分查找递归版
    public static int binarySearch(int arr[],int target,int lo,int hi){
        if (lo > hi){
            return -1;
        }
        int mid = lo + ((hi - lo)>>1);
        if (arr[mid] == target){
            return mid;
        }else if(target < arr[mid]){
            return binarySearch(arr,target,lo,mid -1);
        }else{
            return binarySearch(arr,target,mid + 1,hi);
        }

    }
}
