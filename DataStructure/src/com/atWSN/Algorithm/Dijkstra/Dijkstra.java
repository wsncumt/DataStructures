package com.atWSN.Algorithm.Dijkstra;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//        int capacity = data.length;
        final int max = 65535;
        //邻接矩阵
        int[][] weight =
                {
                        //用0表示不连通
                        //   A  B  C  D  E  F  G
                        {max, 5, 7, max, max, max, 2},
                        {5, max, max, 9, max, max, 3},
                        {7, max, max, max, 8, max, max},
                        {max, 9, max, max, max, 4, max},
                        {max, max, 8, max, max, 5, 4},
                        {max, max, max, 4, 5, max, 6},
                        {2, 3, max, max, 4, 6, max}
                };
        Graph graph = new Graph(data, weight);
        graph.print();
        graph.dijkstra(6);
    }
}

class Graph {
    private char[] vertex;//存放顶点的数组
    private int[][] weight;//邻接矩阵
    private VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] weight) {
        this.vertex = vertex;
        this.weight = weight;
//        this.visitedVertex = new VisitedVertex(vertex.length,);
    }

    public void print() {
        for (int[] w : weight
        ) {
            System.out.println(Arrays.toString(w));
        }
    }

    /**
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点都周围顶点的距离和前驱
        for (int i = 0; i < vertex.length; i++) {
            index = visitedVertex.updateArr();//选择并返回新的访问顶点
            update(index);
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
    private void update(int index) {
        int len = 0;
        //遍历当前节点对应的邻接矩阵
        //更新顶点的前驱和距离
        for (int i = 0; i < weight[index].length; i++) {
            //len：出发顶点到index顶点的距离+index到顶点i的距离
            len = visitedVertex.getDistance(index) + weight[index][i];
            //如果index没有被访问过并且len小于出发顶点到i顶点的距离
            if (!visitedVertex.in(i) && len < visitedVertex.getDistance(i)) {
                this.visitedVertex.updatePre(i, index);//更新i的前驱节点为index顶点
                visitedVertex.upDateDis(i, len);//更新出发顶点到顶点i的距离
            }
        }
    }
}

class VisitedVertex {
    //记录顶点是否被访问
    public boolean[] already_arr;
    //记录该顶点的前驱顶点的下标（通过哪个节点访问的），动态更新
    public int[] pre_visited;
    //记录出发点到其他所有顶点的距离，动态更新
    public int[] distance;

    //index表示从哪个顶点开始构造
    //capacity表示点的个数
    public VisitedVertex(int capacity, int index) {
        this.already_arr = new boolean[capacity];
        this.already_arr[index] = true;//设置出发顶点被访问过
        this.pre_visited = new int[capacity];
        this.distance = new int[capacity];
        //初始化各数组
        Arrays.fill(this.pre_visited, -1);
        this.pre_visited[index] = index;

        Arrays.fill(this.distance, 65535);
        this.distance[index] = 0;
    }

    /**
     * 判断index是否被访问
     *
     * @param index
     * @return 如果访问过，返回true，否则返回false
     */
    public boolean in(int index) {
        return already_arr[index];
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void upDateDis(int index, int len) {
        this.distance[index] = len;
    }

    /**
     * 更新index顶点的前驱为pre
     *
     * @param pre
     * @param index
     */
    public void updatePre(int index, int pre) {
        pre_visited[index] = pre;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDistance(int index) {
        return this.distance[index];
    }

    //选择并返回新的访问节点，比如这里的G访问完后，就是A作为新的访问顶点
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (!already_arr[i] && distance[i] < min) {
                min = distance[i];
                index = i;
            }
        }
        already_arr[index] = true;
        return index;
    }
}