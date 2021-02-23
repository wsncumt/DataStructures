package com.atWSN.datastructures.sort;

import java.util.Arrays;
import java.util.Random;

public class unSortArray {
    private final int capacity;
    public int[] arr;

    public unSortArray(int capacity){
        this.capacity = capacity;
        arr = new int[this.capacity];
        Random random = new Random(1);
        for (int i = 0; i < capacity; i++) {
            arr[i] = random.nextInt(100000) + 1 - 50000;
        }
    }
    public void print(){
        System.out.println(Arrays.toString(arr));
    }
}
