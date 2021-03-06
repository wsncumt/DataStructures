package com.atWSN.Algorithm.Horse;

import java.awt.*;
import java.util.ArrayList;

public class Horse {
    private static int col;//列
    private static int row;//行
    //创建一个棋盘数组，标记棋盘的某位置是否访问
    private static boolean[][] visited;
    //使用一个属性，标记棋盘所有的位置都被访问
    private static boolean finished;

    public static void main(String[] args) {
        col = 8;
        row = 8;
        int r = 1;
        int c = 1;
        int[][] chess = new int[col][row];
        visited = new boolean[col][row];
        traversalChessboard(chess,r-1,c-1,1);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.printf("%3d",chess[i][j]);
            }
            System.out.println();
        }
    }

    //step表示的是走的第几步，初始是1步
    public static void traversalChessboard(int[][] chessboard, int r, int c, int step) {
        chessboard[r][c] = step;
        visited[r][c] = true;
        ArrayList<Point> pointArrayList = next(new Point(r, c));
        while (!pointArrayList.isEmpty()) {
            Point p = pointArrayList.remove(0);//取出下一个可以走的位置
            if (!visited[p.x][p.y]) {
                traversalChessboard(chessboard, p.x, p.y, step + 1);
            }
        }

        //看是否完成任务
        //step < r * c:棋盘目前还未走完或者棋盘仍在回溯
        if (step < r * c && !finished) {
            chessboard[r][c] = 0;
            visited[r][c] = false;
        } else {
            finished = true;
        }
    }

    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> pointArrayList = new ArrayList<>();
        Point point = new Point();
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < row) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < row) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < col && (point.y = curPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < col && (point.y = curPoint.y + 1) < row) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < col && (point.y = curPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < col && (point.y = curPoint.y + 2) < row) {
            pointArrayList.add(new Point(point));
        }

        return pointArrayList;
    }
}

