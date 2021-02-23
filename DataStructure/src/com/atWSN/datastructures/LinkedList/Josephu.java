package com.atWSN.datastructures.LinkedList;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(125);
        circleSingleLinkedList.list();
        System.out.println(circleSingleLinkedList.size());
        circleSingleLinkedList.josephu(10,20,circleSingleLinkedList.size());
    }
}

//创建环形单向链表
class CircleSingleLinkedList{
    //创建first节点
    private BoyNode first;

    //添加新的节点，构建一个环形链表
    public void add(int nums) {
        if (nums < 1){
            return;
        }
        //使用for循环创建环形链表
        BoyNode curNode = null;
        for (int i = 0; i < nums; i++) {
            BoyNode newNode = new BoyNode(i + 1);
            if (i == 0){
                first = newNode;
                first.next =  first;
                curNode = first;
            }else {
                newNode.next = curNode.next;
                curNode.next = newNode;
                curNode = curNode.next;
            }
        }
    }

    //遍历当前的环形链表
    public void list(){
        if (first == null){
            System.out.println("[]");
            return;
        }
        BoyNode curNode = first;
        System.out.print("[");
        while(curNode.next != first){
            System.out.print(curNode.getNum());
            System.out.print(",");
            curNode = curNode.next;
        }
        System.out.print(curNode.getNum());
        System.out.println("]");
    }

    //节点个数
    public int size(){
        if (first == null){
            return 0;
        }
        int sum = 0;
        BoyNode curNode = first;
        while(true){
            sum++;
            if (curNode.next == first){
                break;
            }
            curNode = curNode.next;
        }
        return sum;
    }

    //约瑟夫问题
    /**
     *
     * @param start 从哪个小孩开始数
     * @param m 每次数几个
     * @param sum 表示最初共有几个小孩
     */
    public void josephu(int start, int m, int sum) {
        if (first == null || start < 1 || start > sum) {
            System.out.println("参数输入有误，请重新输入：");
            return;
        }
        BoyNode preNode = first;
        BoyNode curNode = first;
        while(preNode.next != first){
            preNode = preNode.next;
        }
        //移动到第start个小孩
        for (int i = 0; i < start - 1; i++) {
            first = first.next;
            preNode = preNode.next;
        }
        System.out.print("[");
        while(first.next != first){
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                preNode = preNode.next;
            }
            System.out.print(first.getNum());
            System.out.print(",");
            preNode.next = first.next;
            first = first.next;
        }
        System.out.print(first.getNum());
        System.out.println("]");
    }

    public BoyNode getFirst() {
        return first;
    }
}
//创建一个节点
class BoyNode{
    private int num;
    public BoyNode next;

    public BoyNode() {
    }
    public BoyNode(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
