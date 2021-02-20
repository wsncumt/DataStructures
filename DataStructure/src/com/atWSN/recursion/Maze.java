package com.atWSN.recursion;

public class Maze {
    public static void main(String[] args) {
        //先创建一个二维数组模拟迷宫
        final int row = 8;
        final int col = 8;
        //迷宫地图
        int[][] map = new int[row][col];
        //使用1来表示迷宫的墙壁
        for (int i = 0; i < col; i++) {
            map[0][i] = 1;
            map[row - 1][i] = 1;
        }
        for (int i = 1; i < row - 1; i++) {
            map[i][0] = 1;
            map[i][col - 1] = 1;
        }
        //地图中的挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
//        map[2][2] = 1;
        //输出地图
        printMaze(map);

        //使用递归回溯来给小球找路
        System.out.println("----------------------------");
        setWay(map,1,1);
        printMaze(map);
    }
    //打印迷宫地图
    public static void printMaze(int[][] map){
        for (int[] arr: map) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i != arr.length-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    //小球找路
    //map表示地图
    //出口为地图的右下角，即map[clo - 1 - 1][row - 1 - 1]
    //当map[i][j]为0，表示该点没有走过
    //当map[i][j]为1，表示该点为墙
    //当map[i][j]为2，表示该点走过，且是通路
    //当map[i][j]为3，表示该点走过，但是不是通路
    //走迷宫时，需要确定一个策略：先下再右再上再左。如果该点走不通，再回溯
    /**
     *
     * @param map 表示地图
     * @param i 表示开始位置的横坐标
     * @param j 表示开始位置的横坐标
     * @return 找路结果，找到为true
     */
    public static boolean setWay(int[][] map,int i,int j){
        int x = map.length;
        int y = map[0].length;
        if(map[x - 1 - 1][y - 1 - 1] == 2){//通路已找到
            return true;
        }else{
            if (map[i][j] == 0){//当前的点还没走过
                //按照策略玩
                map[i][j] = 2;//假定该点可以走通
                if(setWay(map,i+1,j)){//向下
                    return true;
                }else if(setWay(map,i,j+1)){//向右
                    return true;
                }else if(setWay(map,i-1,j)){//向上
                    return true;
                }else if(setWay(map,i,j-1)){//向左
                    return true;
                }else{//向四个方向都没走通，说明该点是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{//某点不等于0，可能为1（墙）、2（通路）、3（死路）
                    return false;
            }
        }
    }
}
