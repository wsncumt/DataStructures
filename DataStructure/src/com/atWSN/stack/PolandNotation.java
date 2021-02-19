package com.atWSN.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String suffixExpression = "30 4 + 5 * 6 -";
        List<String> list = getList(suffixExpression);
        System.out.println(suffixExpression + " = " + cal(list));
    }
    //将逆波兰表达式放入顺序表中
    public static List<String> getList(String str){
        List<String> list = new ArrayList<>();
        int index = 0;
        String str1[] = str.split(" ");
        for (String s:str1) {
            list.add(s);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    public static int cal(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String ch: list) {
            if (ch.matches("\\d+")){//匹配多位数
                stack.push(ch);
            }else{
                if ("!".equals(ch)){
                    int num = Integer.parseInt(stack.pop());
                    stack.push("" + fac(num));
                }else{
                    int num2 = Integer.parseInt(stack.pop());
                    int num1 = Integer.parseInt(stack.pop());
                    int ret = 0;
                    switch (ch){
                        case "+":
                            ret = num1 + num2;
                            break;
                        case "-":
                            ret = num1 - num2;
                            break;
                        case "*":
                            ret = num1 * num2;
                            break;
                        case "/":
                            ret = num1 / num2;
                            break;
                        case "^":
                            ret = (int)Math.pow((double)num1,(double)num1);
                            break;
                        default:
                            throw new RuntimeException("运算符有误");
                    }
                    stack.push("" + ret);
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private static int fac(int num) {
        if (num == 0 || num == 1){
            return 0;
        }
        return num * fac(num -1);
    }
}
