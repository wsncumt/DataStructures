package com.atWSN.Algorithm.DivideAndConquer;

/**
 * 分治算法
 * 以求解汉诺塔为例
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(10, 'A', 'B', 'C');
    }

    /**
     * 使用分治算法
     *
     * @param n 汉诺塔的盘子数
     * @param a 盘子所在的柱子
     * @param b 辅助柱子
     * @param c 盘子要移动的柱子
     */
    public static void hanoiTower(int n, char a, char b, char c) {
        if (n == 1) {
            //如果只有一个盘，就移动这一个盘
            System.out.println("第1个盘从" + a + "移动到" + c+"上");
            return;
        }
        //把上边的n-1个盘从a移动到b上（借助c盘移动）
        hanoiTower(n - 1, a, c, b);
        //把最下边那个盘从a移动到c上
        System.out.println("第"+n+"个盘从" + a + "移动到" + c + "上");
        //把b上的盘借助a移动到c上
        hanoiTower(n - 1, b, a, c);
    }
}
