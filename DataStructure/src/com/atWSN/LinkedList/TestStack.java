package com.atWSN.LinkedList;

import java.util.Stack;

//演示栈的相关操作
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        //出栈
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
