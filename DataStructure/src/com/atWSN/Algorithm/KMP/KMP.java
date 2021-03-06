package com.atWSN.Algorithm.KMP;

/**
 * 字符串匹配KMP算法
 */
public class KMP {
    public static void main(String[] args) {
        String t = "001001";
        String p = "00";
        int ret = KMP(t, p);
        System.out.println(ret);
    }

    //构建next[]表
    private static int[] getNext(String str) {
        int[] next = new int[str.length()];
        //第一个永远初始化为-1
        next[0] = -1;
        int index = 0;//指向next表的指针，文本串
        int t = -1;//模式串
        while (index < str.length() - 1) {
            if (t == -1 || str.charAt(index) == str.charAt(t)) {
                t++;
                index++;
                next[index] = (str.charAt(index) == str.charAt(t) ? next[t] : t);
            } else {
                t = next[t];
            }
        }
        return next;
    }

    //
    public static int KMP(String t, String p) {
        int[] next = getNext(p);
        int i = 0;
        int j = 0;
        while (i < t.length() && j < p.length()) {
            if (j == -1 || t.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) {
            return i - j;
        }
        return -1;

    }
}
