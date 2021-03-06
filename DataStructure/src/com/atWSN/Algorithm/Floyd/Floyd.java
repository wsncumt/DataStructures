package com.atWSN.Algorithm.Floyd;

public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int max = 65535;
        //邻接矩阵
        int[][] weight =
                {
                        //用0表示不连通
                        //   A  B  C  D  E  F  G
                        {0, 5, 7, max, max, max, 2},
                        {5, 0, max, 9, max, max, 3},
                        {7, max, 0, max, 8, max, max},
                        {max, 9, max, 0, max, 4, max},
                        {max, max, 8, max, 0, 5, 4},
                        {max, max, max, 4, 5, 0, 6},
                        {2, 3, max, max, 4, 6, 0}
                };
        Graph graph = new Graph(vertex.length, weight, vertex);
        graph.show();
        System.out.println("-----------------------------------");
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex;
    private int[][] dis;//保存从各个顶点出发到其他顶点的距离
    private char[][] pre;//保存到达目标顶点的前驱结点

    public Graph(int capacity, int[][] weight, char[] vertexs) {
        this.vertex = vertexs;
        this.dis = weight;
        this.pre = new char[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (i == j) {
                    pre[i][j] = vertex[i];
                } else {
                    pre[i][j] = '-';

                }
            }
        }
    }

    public void show() {
        System.out.print("   ");
        for (int i = 0; i < vertex.length; i++) {
            System.out.printf("%3c",vertex[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < dis.length; i++) {
            System.out.print(vertex[i]);
            System.out.print(": ");
            for (int j = 0; j < dis[0].length; j++) {
                if (dis[i][j] == 65535) {
                    System.out.print("  n");
                } else {
                    System.out.printf("%3d",dis[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
        System.out.print("   ");
        for (int i = 0; i < vertex.length; i++) {
            System.out.print(vertex[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < pre.length; i++) {
            System.out.print(vertex[i]);
            System.out.print(": ");
            for (int j = 0; j < pre[0].length; j++) {
                System.out.print(pre[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void floyd() {
        int len;
        for (int i = 0; i < vertex.length; i++) {//中间顶点
            for (int j = 0; j < vertex.length; j++) {//起点
                for (int k = 0; k < vertex.length; k++) {//终点
                    len = dis[i][j] + dis[i][k];
                    if (len < dis[j][k]) {
                        pre[j][k] = vertex[i];
                        dis[j][k] = len;
                    }
                }
            }
        }
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                if (pre[i][j] == '-'){
                    pre[i][j] = vertex[i];
                }
            }
        }
    }
}