package com.atWSN.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//中缀表达式转后缀表达式
public class ToRPN {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> list = toList(expression);
        System.out.println(list);
        System.out.println(parsrSuffixExpreesionToRPN(list));
    }

    //中缀表达式转成对应的list
    public static List<String> toList(String str) {
        List<String> list = new ArrayList<>();
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (index < str.length()) {
            if (str.charAt(index) == ' ') {
                index++;
            } else if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                    stringBuilder.append(str.charAt(index));
                    index++;
                }
                list.add(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            } else {
                list.add("" + str.charAt(index));
                index++;
            }
        }
        return list;
    }

    //中缀表达式转为逆波兰表达式
    public static List<String> parsrSuffixExpreesionToRPN(List<String> list) {
        List<String> rpn = new ArrayList<>();//存储中间结果和
        Stack<String> stack1 = new Stack<>();//符号栈
        for (String str : list) {
            if (str.matches("\\d+")) {
                rpn.add(str);
            } else if ("(".equals(str)) {
                stack1.push(str);
            } else if (")".equals(str)) {
                while (!stack1.peek().equals("(")) {
                    rpn.add(stack1.pop());
                }
                stack1.pop();
            } else {
                //符号栈不为空或者符号栈栈顶不是(或者该符号优先级不大于栈顶符号
                while (!(stack1.size() == 0 || stack1.peek().equals("(")) && !(getValue(str) > getValue(stack1.peek()))) {
                    rpn.add(stack1.pop());
                }
                //否则符号入栈
                stack1.push(str);
            }
        }
        while (!stack1.isEmpty()) {
            rpn.add(stack1.pop());
        }
        return rpn;
    }

    //优先级
    public static int getValue(String str) {
        int add = 1;
        int sub = 1;
        int mul = 2;
        int div = 2;

        int ret = 0;
        switch (str) {
            case "+":
                ret = add;
                break;
            case "-":
                ret = sub;
                break;
            case "*":
                ret = mul;
                break;
            case "/":
                ret = div;
                break;
        }
        return ret;
    }
}
