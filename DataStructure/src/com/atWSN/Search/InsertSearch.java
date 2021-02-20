package com.atWSN.Search;

public class InsertSearch {
    static int count;
    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int ret = insertSearch(arr,7);
        System.out.println(ret);
        System.out.println(count);
    }

    public static int insertSearch(int[] arr,int target){
        if (target < arr[0] || target > arr[arr.length - 1]){
            return -1;
        }
        int lo = 0;
        int hi = arr.length - 1;
        while(lo <= hi){
            int mid  = lo + (hi - lo)*(target - arr[lo])/(arr[hi] - arr[lo]);
            count++;
            if(target < arr[mid]){
                hi = mid - 1;
            }else if(arr[mid] < target){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
