package com.atWSN.Algorithm.Dynamic;

/**
 * 使用动态规划求解背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {4, 4, 4, 3};//用于保存物品的重量
        int[] value = {3000, 1000, 3000, 2000};//用于保存物品的价值
        final int capacity = 7;//背包的容量
        final int numOfGoods = value.length;

        int[][] valueOfKnapsack = new int[numOfGoods + 1][capacity + 1];

        //为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[numOfGoods + 1][capacity + 1];
        //初始化第0行
        for (int i = 0; i < capacity; i++) {
            valueOfKnapsack[0][i] = 0;
        }
        //初始化第0列
        for (int i = 0; i < value.length; i++) {
            valueOfKnapsack[i][0] = 0;
        }

        for (int i = 1; i <= value.length; i++) {//表示第i个物品
            for (int j = 1; j <= capacity; j++) {//表示当前背包容量
                if (weight[i - 1] > j) {//当前物品大于背包容量
                    valueOfKnapsack[i][j] = valueOfKnapsack[i - 1][j];
                } else {
                    if (valueOfKnapsack[i - 1][j] > value[i - 1] + valueOfKnapsack[i - 1][j - weight[i - 1]]) {
                        valueOfKnapsack[i][j] =valueOfKnapsack[i - 1][j];
                    } else {
                        valueOfKnapsack[i][j] = value[i - 1] + valueOfKnapsack[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 1; i < valueOfKnapsack.length; i++) {
            for (int j = 1; j < valueOfKnapsack[0].length; j++) {
                System.out.print(valueOfKnapsack[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("背包放入的物品为：");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个物品放入背包\n", i);
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
