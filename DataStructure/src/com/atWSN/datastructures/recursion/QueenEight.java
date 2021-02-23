package com.atWSN.datastructures.recursion;
import java.util.Arrays;

public class QueenEight {
    //皇后的个数
    final static int capacity = 8;
    //定义一个一维数组存放皇后的位置
    int[] pos = new int[capacity];
    //解法
    static int count;
    public static void main(String[] args) {
        new QueenEight().check(capacity);
    }
    //将皇后摆放的位置输出
    private  void print(){
        System.out.println(Arrays.toString(pos));
    }

    //放置皇后
    private void check(int n){
        int x = capacity - n;
        if (x == capacity){//共8个皇后，x从0开始的，n等于8说明全部放完了
            System.out.print("第" + (++count) + "种解法：");
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < capacity; i++) {
            //先把当前这个皇后x，放到该行的第1列
            pos[x] = i;
            //放置后判断是否冲突
            if(judge(x)){//不冲突就放下一个皇后
                check(n - 1);
            }
            //冲突就把该皇后放到下一个位置
        }
    }

    //当放第n个皇后，检测是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 第n个皇后,n从0开始
     * @return 冲突：false，不冲突：true
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //pos[i] == pos[n]表示第n个皇后和第i个皇后位于同一列
            //Math.abs(n - i) == Math.abs(pos[n] - pos[i])表示第n个皇后和第i个皇后位于同一斜线
            if (pos[i] == pos[n] || Math.abs(n - i) == Math.abs(pos[n] - pos[i])){
                return false;
            }
        }
        return true;
    }
}
