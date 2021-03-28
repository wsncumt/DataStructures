package sort;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-22 21:21:21
 **/
public class HeapSort {
    public static void heapSort(int[] arr) {
        //建堆
        //刚开始建堆时，堆是无序的，最后一个父节点是arr.length - 1 - 1，数组的长度为arr.length
        for (int i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int i = arr.length - 1; i >=0; i--) {
            //通过建堆，数组的第一个元素一定 是最大的，将其放在最后
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            //此时只需要调整根节点，因为其他堆是有序的
            adjustHeap(arr,0,i);
        }
    }

    //下滤建堆

    /**
     *
     * @param arr
     * @param index  表示需要下滤的最后一个父节点
     * @param length    表示长度
     */
    public static void adjustHeap(int[] arr, int index, int length) {
        int tmp = arr[index];
        for (int i = 2 * index + 1; i < length; i = 2 * i + 1) {
            if (i + 1 < length && arr[i + 1] > arr[i]) {
                i++;
            }
            if (arr[i] > tmp) {
                arr[index] = arr[i];
                index = i;
            } else {
                break;
            }
        }
        arr[index] = tmp;
    }
}
