package com.atWSN.stack;

public class Calculator {

    public static void main(String[] args) {
        String expression = "13+2*(6-2)+5/2";
        ArrayStack<Double> numStack = new ArrayStack<>(100);
        ArrayStack<Character> opeStack = new ArrayStack<>(100);
        int index = 0;
        char ope = '0';
        char ch = ' ';//将每次扫描得到的char保存到ch中
        opeStack.push('\0');
        while (!(opeStack.isEmpty())) {
            if(index < expression.length()){
                ch = expression.charAt(index);
            }else{
                ch = '\0';
            }
            if (isNum(ch)) {
                int[] arr = readNumber(expression, index);
                double x = (double)arr[0];
                numStack.push(x);
                index = arr[1];
            } else {
                if (opeStack.isEmpty()) {
                    opeStack.push(ch);
                    index++;
                } else {
                    switch (orderBetween(opeStack.topValue(), ch)) {
                        case '>':
                            ope = opeStack.pop();
                            if (ope == '!') {
                                double x = numStack.pop();
                                numStack.push((double)fac((int)x));
                            }else{
                                double y = numStack.pop();
                                double x = numStack.pop();
                                double value = cal(x,y,ope);
                                numStack.push(value);
                            }
                            break;
                        case '<':
                            opeStack.push(ch);
                            index++;
                            break;
                        case '=':
                            opeStack.pop();
                            index++;
                            break;
                    }
                }
            }
        }
        System.out.println(numStack.pop());
    }
    public static double cal(double x, double y, char ope) {
        double ret = 0;
        switch (ope) {
            case '/':
                if (y == 0) {
                    throw new RuntimeException("除数为0");
                }
                ret = x / y;
                break;
            case '*':
                ret = x * y;
                break;
            case '-':
                ret = x - y;
                break;
            case '+':
                ret = x + y;
                break;
            case '^':
                ret = Math.pow(x,y);
                break;
        }
        return ret;
    }

    public static int[] readNumber(String str, int index) {
        int ret = 0;
        while (index < str.length() && isNum(str.charAt(index))) {
            int x = str.charAt(index) - '0';
            ret = ret * 10 + x;
            index++;
        }
        return new int[]{ret, index};
    }

    //判断是否为运算符
    public static boolean isNum(char val) {
        return val >= '0' && val <= '9';
    }

    public static int opeNum(char ch) {
        int ret = -1;
        switch (ch) {
            case '+':
                ret = 0;
                break;
            case '-':
                ret = 1;
                break;
            case '*':
                ret = 2;
                break;
            case '/':
                ret = 3;
                break;
            case '^':
                ret = 4;
                break;
            case '!':
                ret = 5;
                break;
            case '(':
                ret = 6;
                break;
            case ')':
                ret = 7;
                break;
            case'\0':
                ret = 8;
                break;
        }
        return ret;
    }

    //
    public static char orderBetween(char ch1, char ch2) {
        int num1 = opeNum(ch1);
        int num2 = opeNum(ch2);
        char pri[][] =  //运算符优先等级[栈顶][当前]
                {       //     |----------当前运算符-----------|
                        //竖的为栈顶运算符
                        //               +    -    *    /    ^    !    (    )
                        /* + */        {'>', '>', '<', '<', '<', '<', '<', '>','>'},
                        /* - */        {'>', '>', '<', '<', '<', '<', '<', '>','>'},
                        /* * */        {'>', '>', '>', '>', '<', '<', '<', '>','>'},
                        /* / */        {'>', '>', '>', '>', '<', '<', '<', '>','>'},
                        /* ^ */        {'>', '>', '>', '>', '>', '<', '<', '>','>'},
                        /* ! */        {'>', '>', '>', '>', '>', '>', ' ', '>','>'},
                        /* ( */        {'<', '<', '<', '<', '<', '<', '<', '=',' '},
                        /* ) */        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',' '},
                        /* \0 */       {'<', '<', '<', '<', '<', '<', '<', ' ','='}
                };
        return pri[num1][num2];
    }



    private static int fac(int num) {
        if (num == 0 || num == 1){
            return 1;
        }
        return num * fac(num - 1);
    }

    public static void print(ArrayStack arrayStack) {
        arrayStack.list();
    }
}

