package com.atWSN.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphDemo {
    //存储顶点的信息，使用ArrayList
    public List<String> vertexList;
    private int[][] edges;//存储各条边的信息
    private int numEdges;//表示边的条数

    //节点访问状态
    public boolean visit[];

    public static void main(String[] args) {
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        GraphDemo graph = new GraphDemo(n);
        for (String vertex :
                vertexValue) {
            graph.insertVertex(vertex);
        }
        graph.showGraph();
        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        System.out.println("-------------------------------------");
        graph.showGraph();
        graph.dfs();
        System.out.println("-------------------------------------");
        graph.bfs();
    }

    //构造器
    public GraphDemo(int n) {
        //初始化矩阵和ArrayList
        vertexList = new ArrayList<>();
        edges = new int[n][n];

    }

    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        //没有就返回-1
        return -1;
    }
    //根据前一个邻接节点的下标来获取下一个邻接节点

    /**
     * @param v1 当前节点
     * @param v2 当前节点的邻接节点
     * @return 下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法

    /**
     * @param index 从第index个节点开始访问
     * @param visit 节点的访问状态
     */
    public void dfs(int index, boolean visit[]) {
        //访问当前节点
        System.out.println(vertexList.get(index));
        //把当前节点访问状态变为已访问
        this.visit[index] = true;
        //获取当前节点的邻接节点
        int w = getFirstNeighbor(index);
        //如果该邻接节点存在
        while (w != -1) {//说明找到了邻接顶点
            //判断该邻接顶点是否被访问
            if (!this.visit[w]) {//没有被访问，就以该节点为新的节点进行搜索
                dfs(w, this.visit);
            } else {//如果w被访问过，就找index的下一个邻接节点
                w = this.getNextNeighbor(index, w);
            }
        }
        //邻接节点不存在,如何访问全部的节点
        //通过重载，对所有的节点进行dfs
        //方法如下
    }

    public void dfs() {
        visit = new boolean[vertexList.size()];
        //遍历所有的节点，进行dfs
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visit[i]) {
                dfs(i, this.visit);
            }
        }
    }

    //广度优先遍历
    //对一个节点进行广度优先遍历
    private void bfs(int index, boolean[] visit) {
        //取出队列的头结点
        int u;
        //u的邻接点的下标w
        int w;
        //队列，用于记录节点的访问顺序
        Queue<Integer> queue = new LinkedList<>();
        //访问当前节点
        System.out.println(vertexList.get(index));
        //更改当前节点的访问状态
        visit[index] = true;
        //当前节点入队
        queue.add(index);
        //队列不为空时，进行访问
        while (!queue.isEmpty()) {
            //取出队首元素进行bfs
            u = queue.poll();
            //得到队首的第一个邻接节点
            w = getFirstNeighbor(u);
            while(w != -1){
                if (!visit[w]) {
                    //当前邻接节点没被访问
                    //则进行访问，同时入队列
                    System.out.println(vertexList.get(w));
                    visit[w] = true;
                    queue.add(w);
                }else{
                    w = getNextNeighbor(u,w);//广度优先
                }
            }
        }
    }

    //遍历所有的节点都进行广度优先搜索
    public void bfs(){
        visit = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!this.visit[i]){
                bfs(i,this.visit);
            }
        }
    }

    //插入顶点
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }
    //添加边

    /**
     * @param v1     点1的下标
     * @param v2     点2的下标
     * @param weight 边的权值，0或1
     */
    public void insertEdge(int v1, int v2, int weight) {
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        this.numEdges++;
    }

    //图的常用方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges() {
        return numEdges;
    }

    //返回节点i对应的数据,i指的是在节点顺序表中的下标
    public String getVertexByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        System.out.print("   ");
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i));
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i));
            System.out.print(": ");
            for (int j = 0; j < vertexList.size(); j++) {
                System.out.print(edges[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}

