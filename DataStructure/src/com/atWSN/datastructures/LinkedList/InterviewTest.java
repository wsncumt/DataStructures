package com.atWSN.datastructures.LinkedList;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class InterviewTest {
    public static void main(String[] args) {
        Node head = new Node();
        Node cur = head;
        for (int i = 1; i <= 3; i++) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        //求链表的有效节点长度
        //带傀儡节点版
        System.out.println("------------------------------------");
        System.out.println(getLength1(head));
        //不带傀儡节点版
        System.out.println("------------------------------------");
        System.out.println(getLength2(head.next));
        System.out.println("------------------------------------");
//        System.out.println("您要求倒数第几个节点？");
//        Scanner scanner = new Scanner(System.in);
        int num = 1;
        System.out.println("求倒数第" + num +"个节点：方法一（头结点为有效节点的前一个）");
        Node ret = findLastIndexNode1(head,num);
        if (ret != null) {
            System.out.println(ret.num);
        }else{
            System.out.println("ret指向为null");
        }
        System.out.println("------------------------------------");
        System.out.println("求倒数第" + num +"个节点：方法一（头结点为有效节点）");
        Node ret1 = findLastIndexNode2(head.next,num);
        if (ret1 != null) {
            System.out.println(ret1.num);
        }else{
            System.out.println("ret1指向为null");
        }

        System.out.println("------------------------------------");
        System.out.println("求倒数第" + num +"个节点：方法二（头结点下一个为有效节点）快慢指针1");
        Node ret3 = findLastIndexNode3(head,num);
        if (ret3 != null) {
            System.out.println(ret3.num);
        }else{
            System.out.println("ret3指向为null");
        }

        System.out.println("------------------------------------");
        System.out.println("求倒数第" + num +"个节点：方法二（头结点下一个为有效节点）快慢指针2");
        Node ret4 = findLastIndexNode4(head,num);
        if (ret4 != null) {
            System.out.println(ret4.num);
        }else{
            System.out.println("ret4指向为null");
        }

        System.out.println("-----------------------------");
        head = reverse1(head);
        printLinkedList(head.next);
        System.out.println("-----------------------------");
        head = reverse2(head.next);
        printLinkedList(head);
        System.out.println("-----------------------------");
        reversePrint(head);
        System.out.println("-----------------------------");
        System.out.println("合并有序链表");
        Node head1 = new Node();
        Node cur1 = head1;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("请输入head1的长度");
        int i = scanner.nextInt();
        System.out.println("请输入head1的初始值");
        int number = scanner.nextInt();;
        Node tmp = null;
        head1.num = number;

        while (i > 0){
            cur1.next = new Node(cur1.num + random.nextInt(10) + 1);
            cur1 = cur1.next;
            i--;
        }
        Node head2 = new Node();
        cur1 = head2;
        System.out.println("请输入head2的长度");
        i = scanner.nextInt();
        System.out.println("请输入head2的初始值");
        number = scanner.nextInt();;
        head2.num = number;
        while (i > 0){
            cur1.next = new Node(cur1.num + random.nextInt(10) + 1);
            cur1 = cur1.next;
            i--;
        }
        System.out.println("打印链表1：");
        printLinkedList(head1);
        System.out.println("打印链表2：");
        printLinkedList(head2);
        Node mergeNode = mergeLinkedList(head1,head2);
        System.out.println("打印合并后的链表：");
        if (mergeNode != null){
            printLinkedList(mergeNode);
        }else{
            System.out.println("合并的链表为空");
        }
    }
    //1.求单链表中有效节点的个数
    //如果是带头结点的，头结点要去掉

    /**
     *
     * @param head 是链表的头结点（第一个有效节点前的那个节点）
     * @return 返回有效节点的个数
     */
    public static int getLength1(Node head){
        if (head.next == null){
            return 0;
        }
        int sum = 0;
        Node curNode = head.next;
        while (curNode != null){
            sum++;
            curNode = curNode.next;
        }
        return sum;
    }

    /**
     *
     * @param head 是链表第一个有效节点
     * @return 返回有效节点的个数
     */
    public static int getLength2(Node head){
        if (head == null){
            return 0;
        }
        int sum = 0;
        Node curNode = head;
        while (curNode != null){
            sum++;
            curNode = curNode.next;
        }
        return sum;
    }

    //查找单链表的倒数第K个节点

    //方法一：
    //1.写一个方法接收head好项目和K
    //2.先遍历链表得到链表的长度sum
    //3.从链表的头部开始遍历找链表的第sum - k个节点即可
    //4.找到后就依题意返回，这里返回对应节点的引用

    /**
     *
     * @param head 是链表的头结点（第一个有效节点前的那个节点）
     * @return 返回对应节点的引用,没有则返回null
     */
    public static Node findLastIndexNode1(Node head,int k){
        if (head.next == null){
            return null;
        }
        int sum  = getLength1(head);
        if(k <= 0 || k > sum){
            return null;
        }
        Node curNode = head.next;
        for (int i = 0; i < sum - k; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }
    /**
     *
     * @param head 是链表的第一个有效节点
     * @return 返回对应节点的引用,没有则返回null
     */
    public static Node findLastIndexNode2(Node head,int k){
        if (head == null){
            return null;
        }
        int sum  = getLength2(head);
        if(k <= 0 || k > sum){
            return null;
        }
        Node curNode = head;
        for (int i = 0; i < sum - k; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    //方法二：使用快慢指针1
    //不求链表的长度
    //快慢指针同时指向第一个有效数据节点，快指针向后移动k - 1步，走到第k个节点。
    //快慢指针同时向后走，直到慢指针走到链表最后一个节点

    /**
     *
     * @param head
     * @param k
     * @return
     */
    public static Node findLastIndexNode3(Node head,int k){
        if (head.next == null){
            return null;
        }
        if(k <= 0){
            return null;
        }
        Node fast = head.next;
        Node slow = head.next;
        for (int i = 0; i < k - 1; i++) {
            if(fast == null){
                return null;
            }
            fast = fast.next;
        }
        if (fast == null){
            return null;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //方法二：使用快慢指针1
    //不求链表的长度
    //快慢指针同时指向头节点（有效数据的前一个节点），快指针移动k步。
    //快慢指针同时向后走，直到快指针指向为null

    /**
     *
     * @param head
     * @param k
     * @return
     */
    public static Node findLastIndexNode4(Node head,int k){
        if (head.next == null){
            return null;
        }
        if(k <= 0){
            return null;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++) {
            if(fast == null){
                return null;
            }
            fast = fast.next;
        }
        if (fast == null){
            return null;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //单链表翻转
    /**
     * 方法一：头插法
     * @author 王松年
     * @param head 的下一个节点是有效节点
     * @return 新的头结点
     */
    public static Node reverse1(Node head){
        if(head.next == null || head.next.next == null){
            return head;
        }
        Node newHead = new Node();
        Node curNode = head.next;
        Node nextNode = curNode.next;
        while (curNode != null){
            nextNode = curNode.next;
            curNode.next = newHead.next;
            newHead.next = curNode;
            curNode = nextNode;
        }
        return newHead;
    }

    /**
     * 方法一：头插法
     * @author 王松年
     * @param head 是有效节点
     * @return 新的头结点
     */
    public static Node reverse2(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node newHead = new Node();
        Node curNode = head;
        Node nextNode = curNode.next;
        while (curNode != null){
            nextNode = curNode.next;
            curNode.next = newHead.next;
            newHead.next = curNode;
            curNode = nextNode;
        }
        return newHead.next;
    }

    /**
     * 方法二：三指针
     * @author 王松年
     * @param head 是有效节点
     * @return 新的头结点
     */
    public static Node reverse3(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node preNode = null;
        Node curNode = head;
        Node nextNode = curNode.next;
        while(curNode != null){
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    //从尾到头打印单链表

    /**
     * head为有效数据
     */
    //注意不要改变原有链表的结构（即不能翻转链表）
    //方法：使用栈
    public static void reversePrint(Node head){
        if (head == null){
            System.out.println("[]");
            return;
        }
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.add(head.num);
            head = head.next;
        }
        System.out.print("[");
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
            if ((stack.size()>=1)){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    //合并两个有序的链表，要求合并后的链表依旧有序

    /**
     *
     * @param head1 第一个有效节点
     * @param head2 第一个有效节点
     * @return
     */
    public static Node mergeLinkedList(Node head1,Node head2){
        Node newHead = new Node();
        Node cur1 = head1;
        Node cur2 = head2;
        Node cur = newHead;
        if (head1 == null && head2 == null){
            return null;
        }
        if (cur1 == null){
            return head2;
        }
        if(cur2 == null){
            return head1;
        }
        while(cur1 != null && cur2 != null){
            if (cur1.num <= cur2.num){
                cur.next = cur1;
                cur = cur.next;
                cur1 = cur1.next;
            }else{
                cur.next = cur2;
                cur = cur.next;
                cur2 = cur2.next;
            }
        }
        if (cur1 != null){
            cur.next = cur1;
        }
        if (cur2 != null){
            cur.next = cur2;
        }
        return newHead.next;
    }


    //显示链表:第一个节点为有效节点
    public static void printLinkedList(Node head) {
        System.out.print("[");
        while (head != null){
            System.out.print(head.num);
            if (head.next != null){
                System.out.print(",");
            }
            head = head.next;
        }
        System.out.println("]");
    }
}




class Node{
    public int num;
    public Node next;

    public Node(int num) {
        this.num = num;
    }
    public Node() {
        //TODO
    }
}
