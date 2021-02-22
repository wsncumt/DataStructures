package com.atWSN.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
int[] arr = {1,3,6,8,10,14};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        int index = 0;
        System.out.println("先序");
        arrBinaryTree.preOrder();
        System.out.println("中序");
        arrBinaryTree.infixOrder();
        System.out.println("后序");
        arrBinaryTree.postOrder();
    }
}
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    //编写一个方法，完成顺序存储二叉树的遍历
    //先序
    public void preOrder(){
        preOrder(0);
    }
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("[]");
            return;
        }
        System.out.println(arr[index]);
        int l = 2*index + 1;
        if(l < arr.length){
            preOrder(l);
        }
        int r = 2*index+2;
        if (r < arr.length){
            preOrder(r);
        }
    }
    //中序
    public void infixOrder(){
        infixOrder(0);
    }
    public void infixOrder(int index){
        if (arr == null || arr.length == 0){
            return;
        }
        if (2*index + 1<arr.length){
            infixOrder(2*index+1);
        }
        System.out.println(arr[index]);
        if (2*index + 2<arr.length){
            infixOrder(2*index+2);
        }
    }

    //后序
    public void postOrder(){
        postOrder(0);
    }
    public void postOrder(int index){
        if (arr == null || arr.length == 0){
            return;
        }
        if (2*index + 1<arr.length){
            postOrder(2*index+1);
        }
        if (2*index + 2<arr.length){
            postOrder(2*index+2);
        }
        System.out.println(arr[index]);
    }
}
