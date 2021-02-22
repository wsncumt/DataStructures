package com.atWSN.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        creatHuffmanTree(arr);
    }

    //创建赫夫曼树的方法
    public static Node creatHuffmanTree(int[] arr) {
        //第一步，为了操作方便，遍历arr
        //将arr的每一个元素构成一个node
        //将Node放入到Arraylist中
        List<Node> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(new Node(arr[i]));
        }
        while (arrayList.size() > 1) {
            //排序:权值从小到大
            Collections.sort(arrayList);
            //System.out.println(arrayList);
            //取出根节点权值最小的两颗二叉树

            Node left = arrayList.get(0);
            Node right = arrayList.get(1);
            //构建出一棵新的二叉树
            Node root = new Node(left.value + right.value);
            root.left = left;
            root.right = right;
            //从链表移除已构建的两个节点
            arrayList.remove(left);
            arrayList.remove(right);
            //把root添加到链表中
            arrayList.add(root);
        }
        return arrayList.get(0);
    }
}

class Node implements Comparable<Node> {
    int value;//节点的权值
    Node left;
    Node right;

    public Node() {

    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大进行排序
        return this.value - o.value;
    }
}