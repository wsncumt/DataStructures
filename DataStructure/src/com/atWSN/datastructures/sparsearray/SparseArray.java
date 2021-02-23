package com.atWSN.datastructures.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[7][8] = 2;
        System.out.println("原始的二维数组：");
        for (int[] row: chessArray1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //把二维数组转为稀疏数组
        //1.遍历二维数组，得到非零数据的个数
        int sum = 0;
        for (int[] row: chessArray1) {
            for (int data : row) {
                if (data != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        //创建对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("输出稀疏数组");
        for (int[] row: sparseArray) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //稀疏数组转二维数组
        System.out.println("-------------------------");
        System.out.println("稀疏数组转二维数组:");
        int chessArray2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        //遍历稀疏数组，把稀疏数组中存的信息还原回去
        for (int i = 1; i <= sparseArray[0][2]; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        for (int[] row: chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
