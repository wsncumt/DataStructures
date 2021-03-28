package sort;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-22 19:33:55
 **/
public class InsertSort {
    public static void main(String[] args) {

    }
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i-1;
            for (; j >=0 ; j--) {
                if (val < arr[j]){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j + 1] = val;
        }
    }
}
