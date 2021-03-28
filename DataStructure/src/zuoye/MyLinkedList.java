package zuoye;

import java.util.Stack;

/**
 * @program: DataStructures
 * @description 实现LinkedList类
 * @author:戛剑生
 * @creat: 2021-03-18 17:00:10
 **/
class Node {
    String val;
    Node prev;
    Node next;

    public Node() {
    }

    public Node(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" + val + '}';
    }
}

public class MyLinkedList {
    private Node head;
    private Node last;
    private int size;

    public MyLinkedList() {
        head = null;
        last = null;
        size = 0;
    }

    //链表元素个数
    public int size() {
        return size;
    }

    //链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //头插
    public void addFirst(String val) {
        Node tmp = new Node(val);
        if (isEmpty()) {
            head = tmp;
            last = tmp;
            size++;
            return;
        }
        tmp.next = head.next;
        head.prev = tmp;
        size++;
        head = tmp;
    }

    //尾插
    public void add(String val) {
        Node tmp = new Node(val);
        if (isEmpty()) {
            head = tmp;
            last = tmp;
            size++;
            return;
        }
        last.next = tmp;
        tmp.prev = last;
        last = last.next;
        size++;
    }

    //指定位置插入
    public void add(int index, String val) {
        if (index < 0 || index > size) {
            System.out.println("下标越界");
            return;
        }
        if (index == 0) {
            addFirst(val);
            return;
        }
        if (index == this.size) {
            add(val);
            return;
        }
        Node tmp = new Node(val);
        Node pos = getNode(index);
        tmp.next = pos;
        tmp.prev = pos.prev;
        tmp.prev.next = tmp;
        pos.prev = tmp;
        size++;
    }

    //查找指定下标节点
    private Node getNode(int index) {
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    //删除最后一个节点
    public void remove() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        if (this.size == 1) {
            head = null;
            last = null;
            size--;
            return;
        }
        last = last.prev;
        last.next = null;
        size--;
    }

    //删除头结点
    private void removeFirst() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        if (this.size == 1) {
            head = null;
            last = null;
            size--;
            return;
        }
        head = head.next;
        head.prev = null;
        size--;
    }

    //按下标删除节点
    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("下标越界");
            return;
        }
        if (index == size - 1) {
            remove();
            return;
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        Node del = getNode(index);
        del.prev.next = del.next;
        del.next.prev = del.prev;
        size--;
    }

    //按值删除
    public void remove(String val) {
        int index = indexOf(val);
        if (index == -1) {
            System.out.println("未找到指定元素！");
            return;
        }
        remove(index);
    }

    public int indexOf(String val) {
        Node tmp = head;
        for (int i = 0; i < size; i++) {
            if (tmp.val.equals(val)) {
                return i;
            }
            tmp = tmp.next;
        }
        return -1;
    }

    //查找：按下标查找
    public String get(int index) {
        return getNode(index).val;
    }

    //修改指定下标的值
    public void set(int index, String val) {
        if (index < 0 || index >= size) {
            System.out.println("下标越界");
            return;
        }
        getNode(index).val = val;
    }

}

