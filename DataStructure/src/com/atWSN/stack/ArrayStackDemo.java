package com.atWSN.stack;

import java.util.Random;
import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //栈的测试代码：
        Scanner scanner = new Scanner(System.in);
        System.out.println("栈的测试：");
        System.out.println("请输入栈的规模");
        int capacity = scanner.nextInt();
        ArrayStack<Integer> arrayStack = new ArrayStack(capacity);
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
                        int num = arrayStack.topValue();
                        System.out.println(num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pu":
                    int num = randomNum();
                    System.out.println("要加入栈的元素是：" + num);
                    arrayStack.push(num);
                    break;
                case "p":
                    try {
                        int num1 = arrayStack.pop();
                        System.out.println(num1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s":
                    arrayStack.list();
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
            }
        }
    }

    public static int randomNum() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }
}

class ArrayStack1 {
    private final int capacity;
    private int size;
    private int[] stack;
    private int top = -1;
    private int bottom = -1;

    public ArrayStack1(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFully() {
        return top == capacity - 1;
    }

    //栈的元素
    public int size() {
        return top + 1;
    }

    //入栈
    public void push(int data) {
        if (isFully()) {
            System.out.println("栈满，无法添加！");
            return;
        }
        stack[++top] = data;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有元素可以出栈！");
        }
        return stack[top--];
    }

    //遍历栈:从栈顶向栈底显示元素
    public void list() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = top; i > bottom; i--) {
            System.out.print(stack[i]);
            if (i != 0) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    //显示栈顶元素
    public int topValue() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有元素可以出栈！");
        }
        return stack[top];
    }
}