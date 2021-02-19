package com.atWSN.stack;

import java.util.Random;
import java.util.Scanner;

public class SingleLinkedListStackDemo {

    public static int randomNum() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    public static void main(String[] args) {
        //栈的测试代码：
        Scanner scanner = new Scanner(System.in);
        System.out.println("栈的测试：");
        System.out.println("请输入栈的规模");
        int capacity = scanner.nextInt();
        SingleLinkedListStack stack = new SingleLinkedListStack(capacity);
        String key = "";
        boolean loop = true;
        while (loop) {
            System.out.println("------------------------------------------");
            System.out.println("s(Show):显示栈");
            System.out.println("e(Exit):退出栈");
            System.out.println("p(Pop):弹出栈顶元素");
            System.out.println("pu(Push):往栈中加入元素");
            System.out.println("t(TopValue)：查看栈顶元素");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "t":
                    try {
                        int num = stack.topValue();
                        System.out.println(num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pu":
                    int num = randomNum();
                    System.out.println("要加入栈的元素是：" + num);
                    stack.push(num);
                    break;
                case "p":
                    try {
                        int num1 = stack.pop();
                        System.out.println(num1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s":
                    stack.list();
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
            }
        }
    }

    static class SingleLinkedListStack {
        private Node top;
        private final int capacity;

        public SingleLinkedListStack(int capacity) {
            this.capacity = capacity;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public boolean isFull() {
            return size() == capacity;
        }

        public int size() {
            int sum = 0;
            Node curNode = top;
            while (curNode != null) {
                sum++;
                curNode = curNode.next;
            }
            return sum;
        }

        public void push(int num) {
            if (isFull()) {
                System.out.println("栈满，无法加入！");
                return;
            }
            Node node = new Node(num);
            node.next = top;
            top = node;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈空，无法取出元素！");
            }
            int value = top.getNum();
            top = top.next;
            return value;
        }

        public int topValue() {
            return top.getNum();
        }

        public void list() {
            Node curNode = top;
            System.out.print("[");
            while (curNode != null) {
                System.out.print(curNode.getNum());
                if (curNode.next != null) {
                    System.out.print(",");
                }
                curNode = curNode.next;
            }
            System.out.println("]");
        }
    }

    static class Node {
        private int num;
        public Node next;

        public Node(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
