package com.atWSN.datastructures.Search;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2,8,10,89,89,89,89,1000,1234};
        int target =  1;
        System.out.println(binarySearch(arr,0,arr.length - 1,target));
        System.out.println(binarySearch1(arr,target));
        System.out.println(Arrays.toString(binarySearch2(arr,target)));
        System.out.println(insert(arr,target));
    }
    //递归版本
    public static int binarySearch(int[] arr,int lo,int hi,int value){
        if (lo > hi){
            return -1;
        }
        int mid = lo + ((hi - lo) >> 1);
        int midValue = arr[mid];
        if(midValue < value){
           return binarySearch(arr,mid + 1,hi,value);
        }else if(value <midValue){
            return binarySearch(arr,lo,mid - 1,value);
        }else{
            return mid;
        }
    }
    //迭代版本
    public static int binarySearch1(int[] arr,int value){
        int lo = 0;
        int hi = arr.length;
        while (lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            if(value < arr[mid]){
                hi = mid - 1;
            }else if(value > arr[mid]){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    //寻找插入值
    //如果元素存在，返回该元素最后一个所在的位置
    //如果元素不存在，返回其插入位置的前一个位置
    public static  int insert(int[] arr,int target){
        int lo = 0;
        int hi = arr.length;
        while (lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            if(target < arr[mid]){
                hi = mid - 1;
            }else if(target > arr[mid]){
                lo = mid + 1;
            }else{
                return findLast(arr,target);
            }
        }
        return lo - 1;
    }

    //找到数组中所有的目标值元素，返回其范围
    //没有则返回[-1,-1]
    public static int[] binarySearch2(int[] arr,int target){
        return new int[]{findFirst(arr,target),findLast(arr,target)};
    }

    private static int findLast(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;
        while (lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            if(target < arr[mid]){
                hi = mid - 1;
            }else if(target > arr[mid]){
                lo = mid + 1;
            }else{
                if(mid == arr.length - 1 || arr[mid + 1] != target){
                    return mid;
                }else{
                    lo = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int findFirst(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;
        while (lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            if(target < arr[mid]){
                hi = mid - 1;
            }else if(target > arr[mid]){
                lo = mid + 1;
            }else{
                if(mid == 0 || arr[mid - 1] != target){
                    return mid;
                }else{
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}
