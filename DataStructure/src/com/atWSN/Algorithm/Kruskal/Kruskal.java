package com.atWSN.Algorithm.Kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    private int edgeCapacity;//边的条数
    private char[] vertexs;//顶点的集合
    final private int vertexCapacity;//记录顶点的个数
    private int[][] matrix;
    private static final int max = Integer.MAX_VALUE;//用此值表示不能连通

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//        int[][] matrix = new int[][]
//                {       //0表示自己与自己连通
//                        {0, 12, max, max, max, 16, 14},
//                        {12, 0, 10, max, max, 7, max},
//                        {max, 10, 0, 3, 5, 6, max},
//                        {max, max, 3, 0, 4, max, max},
//                        {max, max, 5, 4, 0, 2, 8},
//                        {16, 7, 6, max, 2, 0, 9},
//                        {14, max, max, max, 8, 9, 0}
//                };
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
        //创建Kruskal实例对象
        Kruskal graph = new Kruskal(vertexs, weight);
        graph.print();
        List<EData> eData = new ArrayList<>();
        eData = graph.getEdges();
        System.out.println(eData);
        System.out.println("-----------------------------------");
        Collections.sort(eData);
        System.out.println(eData);
        List<EData> miniTree = graph.kruskal();
        System.out.println(miniTree);
    }

    public Kruskal(char[] vertexs, int[][] matrix) {
        //初始化顶点的个数
        this.vertexCapacity = vertexs.length;
        //初始化顶点
        this.vertexs = new char[this.vertexCapacity];
        for (int i = 0; i < this.vertexCapacity; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[this.vertexCapacity][this.vertexCapacity];
        for (int i = 0; i < this.vertexCapacity; i++) {
            for (int j = 0; j < this.vertexCapacity; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < this.vertexCapacity; i++) {
            for (int j = i; j < this.vertexCapacity; j++) {
                if (this.matrix[i][j] < max) {
                    this.edgeCapacity++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵：");
        System.out.print("   ");

        for (int i = 0; i < this.vertexCapacity; i++) {
            System.out.printf("%3c", this.vertexs[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < this.vertexCapacity; i++) {
            System.out.print(this.vertexs[i]);
            System.out.print(": ");
            for (int j = 0; j < this.vertexCapacity; j++) {
                if (matrix[i][j] == max) {
                    System.out.print("inf");
                } else {
                    System.out.printf("%3d", matrix[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * @param ch 顶点的值
     * @return 返回该顶点的下标，找不到返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < this.vertexCapacity; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：获取图中的边，放到EData顺序表中
     * 通过权值数组(邻接矩阵)来遍历
     */
    public List<EData> getEdges() {
        List<EData> getEdges = new ArrayList<>();
        for (int i = 0; i < this.vertexCapacity; i++) {
            for (int j = i + 1; j < this.vertexCapacity; j++) {
                if (this.matrix[i][j] > 0 && this.matrix[i][j] < max) {
                    getEdges.add(new EData(this.vertexs[i], this.vertexs[j], this.matrix[i][j]));
                }
            }
        }
        return getEdges;
    }

    /**
     * 功能：获取下标为i的顶点的终点
     * 用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的终点是哪个，该数组是在遍历的过程中逐步形成的
     * @param i    传入的顶点对应的下标
     * @return 下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


    /**
     * Kruskal主算法
     */
    public List<EData> kruskal() {
        //表示最后结果的顺序表的索引
        int index;
        //用于存放某已有最小生成树中的每个顶点在最小生成树中的数组
        int[] ends = new int[this.edgeCapacity];
        //存放最小生成树的集合
        List<EData> miniTree = new ArrayList<>();

        //获取图中所有边的集合
        List<EData> edges = getEdges();
        //按边的权值进行排序
        Collections.sort(edges);
        //遍历所有的边，将权值最小的边添加到生成树时
        //判断是否形成了回路：没有构成回路就加入最小生成树
        for (EData edge :
                edges) {
            //获取该边的两个端点
            int p1 = getPosition(edge.start);
            int p2 = getPosition(edge.end);
            //获取p1这个顶点在已有的最小生成树中对应的终点
            int m1 = getEnd(ends, p1);

            int m2 = getEnd(ends, p2);
            //m1!=m2，说明不构成回路
            if (m1 != m2) {
                ends[m1] = m2;//设置m1在已有最小生成树的终点
                miniTree.add(edge);
            }
        }
        return miniTree;
    }
}

//创建一个边类，用于表示两点和这两点之间的边（权值）
class EData implements Comparable<EData> {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.start + "<-" + this.weight + "->" + this.end;
    }

    @Override
    public int compareTo(EData o) {
        return this.weight - o.weight;
    }
}