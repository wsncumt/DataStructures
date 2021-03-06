package com.atWSN.Algorithm.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法求解集合覆盖问题
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到map
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();
        HashSet<String> strings1 = new HashSet<>();
        strings1.add("北京");
        strings1.add("上海");
        strings1.add("天津");
        broadCasts.put("k1", strings1);
        HashSet<String> strings2 = new HashSet<>();
        strings2.add("北京");
        strings2.add("广州");
        strings2.add("深圳");
        broadCasts.put("k2", strings2);
        HashSet<String> strings3 = new HashSet<>();
        strings3.add("成都");
        strings3.add("上海");
        strings3.add("杭州");
        broadCasts.put("k3", strings3);
        HashSet<String> strings4 = new HashSet<>();
//        strings1.add("北京");
        strings4.add("上海");
        strings4.add("天津");
        broadCasts.put("k4", strings4);
        HashSet<String> strings5 = new HashSet<>();
        strings5.add("杭州");
        strings5.add("大连");
//        strings1.add("天津");
        broadCasts.put("k5", strings5);
        //存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("杭州");
        allAreas.add("成都");
        allAreas.add("大连");
        //存放所有的电台集合
        //存放的是最终的电台
        ArrayList<String> selects = new ArrayList<>();
        //定义临时的集合，保存在遍历的过程中，存放遍历过程中电台覆盖的地区与当前没有覆盖地区的交集
        HashSet<String> tmpSet = new HashSet<>();
        //定义maxKey，用于记录在一次遍历过程中，能够覆盖最大未覆盖地区的那个电台
        //如果maxKey不为空，则加入到select中
        String maxKey = null;
        while (!allAreas.isEmpty()) {//不为空，表示还没覆盖完
            maxKey = null;
            tmpSet.clear();
            //遍历broadcasts
            for (String key : broadCasts.keySet()) {
                HashSet<String> areas = broadCasts.get(key);//当前的k能够覆盖的地区
                tmpSet.addAll(areas);//还未被覆盖的地区
                //和未覆盖地区取交集
                tmpSet.retainAll(allAreas);
                if (!tmpSet.isEmpty() && (maxKey == null || tmpSet.size() > broadCasts.get(maxKey).size())) {
                    //后边的那个条件体现了贪心算法
                    //每次都选择最优的
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的那个广播电台覆盖的地区从select清空
                allAreas.removeAll(tmpSet);
            }
        }
    }
}
