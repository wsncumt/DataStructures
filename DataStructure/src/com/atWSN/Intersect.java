package com.atWSN;

import java.util.Random;

/**
 * @program: DataStructures
 * @description 给定两个链表头结点，判断该链表是否相交
 * @author:戛剑生
 * @creat: 2021-03-06 10:15:58
 **/
public class Intersect {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        ListNode l9 = new ListNode(9);
        ListNode headA = l1;
        l1.next = l2;
        l2.next = l3;
//        l3.next = l4;
//        l3.next = l7;
        l4.next = l5;

        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l6;
        ListNode headB = l4;
        //方法1
        ListNode ret = getIntersectionNode(headA, headB);
        ListNode ret2 = getIntersectionNode2(headA, headB);
        ListNode ret3 = getIntersectionNode3(headA, headB);
        System.out.println("---------------------");
        System.out.print("方法一：");
        if (ret == null) {
            System.out.println("未找到相交的节点");
        } else {
            System.out.println(ret);
        }
        System.out.println("---------------------");
        System.out.print("方法二：");
        if (ret2 == null) {
            System.out.println("未找到相交的节点");
        } else {
            System.out.println(ret2);
        }
        System.out.println("---------------------");
        System.out.print("方法三：");
        if (ret3 == null) {
            System.out.println("未找到相交的节点");
        } else {
            System.out.println(ret3);
        }
    }

    /**
     * 找到两个链表相交的起始节点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 有交点返回相交的起始节点，没有返回null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //处理边界：一个链表为空
        if (headA == null || headB == null) {
            return null;
        }
        //1.先判断两链表是否带环
        boolean flagA = isCircle(headA);
        boolean flagB = isCircle(headB);
        //2.有环就求出环入口节点，备用
        ListNode entranceA = null;
        ListNode entranceB = null;
        if (flagA) {
            entranceA = circleEntrancePoint(headA);
        }
        if (flagB) {
            entranceB = circleEntrancePoint(headB);
        }
        //3.判断交点
        //(1).不带环的
        if (flagA == false && flagB == false) {
//            return noCircleGetIntersectionNode(headA, headB);
            return noCircleGetIntersectionNode(headA, headB, null);
        }
        //(2)一个带环一个不带环
        if (flagA == false || flagB == false) {
            //两链表有交点，一个带环则另一个必带环。否则一定无交点
            return null;
        }
        //(3)两个都带环
        //(3.1)相交在环外
        if (entranceA == entranceB) {
            return noCircleGetIntersectionNode(headA, headB, entranceA);
        }
        //(3.2)相交在环上
        ListNode tmp = entranceA;//也可以从entranceB开始找
        do {
            if (tmp == entranceB) {
                //相交在环上，A的环入口节点或B的环入口节点都是相交起始点（看对谁来说）
                //所以随机返回一个
                return new Random().nextInt(2) == 0 ? entranceA : entranceB;
            }
            tmp = tmp.next;
        } while (tmp != entranceA);
        //(3.3)没有交点
        return null;
    }

    /**
     * 找到两个链表相交的起始节点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 有交点返回相交的起始节点，没有返回null
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //处理边界：一个链表为空
        if (headA == null || headB == null) {
            return null;
        }
        //1.先判断两链表是否带环
        boolean flagA = isCircle(headA);
        boolean flagB = isCircle(headB);
        //2.有环就求出环入口节点，备用
        ListNode entranceA = null;
        ListNode entranceB = null;
        if (flagA) {
            entranceA = circleEntrancePoint(headA);
        }
        if (flagB) {
            entranceB = circleEntrancePoint(headB);
        }
        //3.判断交点
        //(1).不带环的
        if ((flagA == false && flagB == false) || entranceA == entranceB) {
//            return noCircleGetIntersectionNode(headA, headB);
            return noCircleGetIntersectionNode(headA, headB, entranceA);
        }
        //(2)一个带环一个不带环
        if (flagA == false || flagB == false) {
            //两链表有交点，一个带环则另一个必带环。否则一定无交点
            return null;
        }
        //(3)两个都带环
//        //(3.1)相交在环外
//        if (entranceA == entranceB){
//            return noCircleGetIntersectionNode(headA, headB, entranceA);
//        }
        //(3.2)相交在环上
        ListNode tmp = entranceA;//也可以从entranceB开始找
        do {
            if (tmp == entranceB) {
                //相交在环上，A的环入口节点或B的环入口节点都是相交起始点（看对谁来说）
                //所以随机返回一个
                return new Random().nextInt(2) == 0 ? entranceA : entranceB;
            }
            tmp = tmp.next;
        } while (tmp != entranceA);
        //(3.3)没有交点
        return null;
    }

    /**
     * 找到两个链表相交的起始节点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 有交点返回相交的起始节点，没有返回null
     */
    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode entranceA = circleEntrancePoint1(headA);
        ListNode entranceB = circleEntrancePoint1(headB);

        if (entranceA == entranceB) {
//            return noCircleGetIntersectionNode(headA, headB);
            return noCircleGetIntersectionNode(headA, headB, entranceA);
        }
        //(2)一个带环一个不带环
        if (entranceA == null || entranceB == null) {
            //两链表有交点，一个带环则另一个必带环。否则一定无交点
            return null;
        }
        //(3)两个都带环
//        //(3.1)相交在环外
//        if (entranceA == entranceB){
//            return noCircleGetIntersectionNode(headA, headB, entranceA);
//        }
        //(3.2)相交在环上
        ListNode tmp = entranceA;//也可以从entranceB开始找
        do {
            if (tmp == entranceB) {
                //相交在环上，A的环入口节点或B的环入口节点都是相交起始点（看对谁来说）
                //所以随机返回一个
                return new Random().nextInt(2) == 0 ? entranceA : entranceB;
            }
            tmp = tmp.next;
        } while (tmp != entranceA);
        //(3.3)没有交点
        return null;
    }

    /**
     * 判断链表是否带环
     *
     * @param head 链表头结点
     * @return 带环返回true，否则返回false
     */
    private static boolean isCircle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 求带环链表的入口节点
     *
     * @param head 带环链表头结点
     * @return 返回入口节点
     */
    private static ListNode circleEntrancePoint(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //链表已经带环，不用判断是否带环
        //快慢指针重合在环内某位置，但开始时刻快慢指针在头结点重合
        //所以先do让指针走一次
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        ListNode tmp = head;
        while (tmp != slow) {
            tmp = tmp.next;
            slow = slow.next;
        }
        return slow;
    }

    //

    /**
     * 不带环的两链表相交的起始节点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 有交点返回相交的起始节点，没有返回null
     */
    public static ListNode noCircleGetIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA == null || headB == null) {
//            return null;
//        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == null ? headB : a.next);
            b = (b == null ? headA : b.next);
        }
        return a;
    }

    //带环判断
    public static ListNode noCircleGetIntersectionNode(ListNode headA, ListNode headB, ListNode intersect) {
//        if (headA == null || headB == null) {
//            return null;
//        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == intersect ? headB : a.next);
            b = (b == intersect ? headA : b.next);
        }
        return a;
    }

    private static int length(ListNode head, ListNode cirEntrance) {
        int len = 0;
        for (ListNode cur = head; cur != cirEntrance; cur = cur.next) {
            len++;
        }
        return len;
    }

    private static ListNode circleEntrancePoint1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);

        ListNode cur = head;
        while (cur != slow) {
            cur = cur.next;
            slow = slow.next;
        }
        return slow;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
