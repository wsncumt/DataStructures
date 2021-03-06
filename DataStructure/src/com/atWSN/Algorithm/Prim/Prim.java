package com.atWSN.Algorithm.Prim;

import java.util.Arrays;

/**
 * 最小生成树问题
 */
public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int capacity = data.length;
        //邻接矩阵
        int[][] weight =
                {
                        //用0表示不连通
                    //   A  B  C  D  E  F  G
                        {0, 5, 7, 0, 0, 0, 2},
                        {5, 0, 0, 9, 0, 0, 3},
                        {7, 0, 0, 0, 8, 0, 0},
                        {0, 9, 0, 0, 0, 4, 0},
                        {0, 0, 8, 0, 0, 5, 4},
                        {0, 0, 0, 4, 5, 0, 6},
                        {2, 3, 0, 0, 4, 6, 0}
                };
        //创建一个mGraph对象
        MGraph mGraph = new MGraph(capacity);
        MinTree minTree = new MinTree();
        minTree.creatGraph(mGraph,data,weight);
        minTree.showGraph(mGraph);
        int[][] ret =  minTree.prim(mGraph,0);
        System.out.println("最小生成树为：");
        for (int i = 0; i < ret.length; i++) {
            System.out.println(""+data[ret[i][0]] + "<-"+weight[ret[i][0]][ret[i][1]]+"->" +data[ret[i][1]]);
        }
    }
}

//创建最小生成树
class MinTree {
    //创建邻接矩阵

    /**
     * @param mGraph   图的对象
     * @param data     图中各节点的名字
     * @param weight   图中各节点之间的权值
     */
    public void creatGraph(MGraph mGraph, char[] data, int[][] weight) {
        for (int k = 0; k < mGraph.capacity; k++) {
            mGraph.data[k] = data[k];
            for (int i = 0; i < mGraph.capacity; i++) {
                mGraph.weight[k][i] = weight[k][i];
            }
        }
    }

    //显示图的方法:显示图的邻接矩阵
    public void showGraph(MGraph mGraph) {
        System.out.print("   ");
        for (int i = 0; i < mGraph.capacity; i++) {
            System.out.print(mGraph.data[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < mGraph.capacity; i++) {
            System.out.print(mGraph.data[i]);
            System.out.print(": ");
            for (int j = 0; j < mGraph.capacity; j++) {
                System.out.print(mGraph.weight[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //编写prim算法，以生成最小生成树

    /**
     *
     * @param mGraph 要创建最小生成树的那个图
     * @param v 开始生成的顶点
     */
    public int[][] prim(MGraph mGraph,int v){
        //创建一个数组来标记顶点是否被访问
        boolean[] visit = new boolean[mGraph.capacity];
        //将这些顶点初始标记为false
        for (int i = 0; i < mGraph.capacity; i++) {
            visit[i] = false;
        }
        //把当前节点标记为已访问
        visit[v] = true;
        //用于记录两个顶点的下标
        int[][] edge = new int[mGraph.capacity - 1][2];
        int minWeight = 0;
        //找到最大的权值
        for (int i = 0; i < mGraph.capacity; i++) {
            for (int j = i; j < mGraph.capacity; j++) {
                if (mGraph.weight[i][j] > minWeight) {
                    minWeight = mGraph.weight[i][j];
                }
            }
        }
        //记录最大的权值
        int max = minWeight;//

        int h1 = 0;
        int h2 = 0;
        for (int i = 1; i < mGraph.capacity; i++) {//最小生成树有capacity-1条边
            //确定每一次生成的子图，和哪个节点的距离最小
            for (int j = 0; j < mGraph.capacity; j++) {
                //j表示被访问过的节点
                for (int k = 0; k < mGraph.capacity; k++) {
                    //k表示未被访问的节点
                    //寻找访问节点和未访问节点之间的最小权值
                    //mGraph.weight[j][k]：访问节点和未访问节点之间的权值
                    if (visit[j] && !visit[k] && mGraph.weight[j][k] != 0 && mGraph.weight[j][k] < minWeight) {
                        minWeight = mGraph.weight[j][k];
                        //第i条边
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            //把权值最小的那个节点记录到最小生成树里
            edge[i - 1][0] = h1;
            edge[i - 1][1] = h2;
            //将节点标记为已访问
            visit[edge[i - 1][1]] = true;
            minWeight = max;
        }

        return edge;
    }
}

class MGraph {
    final int capacity;//表示图的节点的个数
    char[] data;//用于表示节点的名称
    int[][] weight;//用于存放权值

    public MGraph(int capacity) {
        this.capacity = capacity;
        data = new char[this.capacity];
        weight = new int[this.capacity][this.capacity];
    }
}
