package com.atWSN.datastructures.stack;

public class ArrayStack<E> {
    private final int capacity;
    private int size;
    private E[] stack;
    private int top = -1;
    private int bottom = -1;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        stack = (E[]) new Object[capacity];
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
    public void push(E data) {
        if (isFully()) {
            System.out.println("栈满，无法添加！");
            return;
        }
        stack[++top] = data;
    }

    //出栈
    public E pop() {
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
    public E topValue() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有元素可以出栈！");
        }
        return stack[top];
    }
}
