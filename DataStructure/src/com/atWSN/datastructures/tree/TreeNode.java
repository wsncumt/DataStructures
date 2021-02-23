package com.atWSN.datastructures.tree;

public class TreeNode {
    private int num;
    private String name;
    public TreeNode leftChild;
    public TreeNode rightChild;
    public TreeNode parent;

    public TreeNode() {

    }

    public TreeNode(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    //删除节点
    //假设不是空树
    public void delTreeNode(int del){
        if (this.leftChild != null){
            if (this.leftChild.num == del){
                this.leftChild = null;
                return;
            }
        }
        if (this.rightChild != null){
            if (this.rightChild.num == del){
                this.rightChild = null;
                return;
            }
        }
        if (this.leftChild != null) {
            this.leftChild.delTreeNode(del);
        }
        if (this.rightChild != null) {
            this.rightChild.delTreeNode(del);
        }
    }


    //前序遍历的方法,迭代法
    public void preOrder() {
        System.out.println(this);
        if (!(this.leftChild == null)) {
            this.leftChild.preOrder();
        }
        if (!(this.rightChild == null)) {
            this.rightChild.preOrder();
        }
    }

    //前序遍历查找指定节点

    /**
     * @param target 目标编号
     * @return 返回对应节点
     */
    public TreeNode preOrderSearch(int target) {
        if (this == null || this.num == target) {
            return this;
        }
        TreeNode targetNode = null;
        if (!(this.leftChild == null)) {
            targetNode = this.leftChild.preOrderSearch(target);
        }
        if (targetNode != null) {
            return targetNode;
        }
        if (!(this.rightChild == null)) {
            targetNode = this.rightChild.preOrderSearch(target);
        }
        return targetNode;
    }

    //中序遍历：递归法
    public void infixOrder() {
        if (!(this.leftChild == null)) {
            this.leftChild.infixOrder();
        }
        System.out.println(this);
        if (!(this.rightChild == null)) {
            this.rightChild.infixOrder();
        }
    }

    //中序遍历查找
    public TreeNode infixOrderSearch(int target) {
        if (this == null) {
            return this;
        }
        TreeNode targetNode = null;
        if (!(this.leftChild == null)) {
            targetNode = this.leftChild.infixOrderSearch(target);
        }
        if (targetNode != null) {
            return targetNode;
        }
        if (this.num == target) {
            return this;
        }
        if (!(this.rightChild == null)) {
            targetNode = this.rightChild.infixOrderSearch(target);
        }
        return targetNode;
    }

    //后序遍历
    public void postOrder() {
        if (!(this.leftChild == null)) {
            this.leftChild.postOrder();
        }

        if (!(this.rightChild == null)) {
            this.rightChild.postOrder();
        }
        System.out.println(this);
    }

    public TreeNode postOrderSearch(int target) {
        if (this == null) {
            return this;
        }
        TreeNode targetNode = null;
        if (!(this.leftChild == null)) {
            targetNode = this.leftChild.postOrderSearch(target);
        }
        if (!(targetNode == null)) {
            return targetNode;
        }
        if (!(this.rightChild == null)) {
            targetNode = this.rightChild.postOrderSearch(target);
        }
        if (!(targetNode == null)) {
            return targetNode;
        }
        if (this.num == target) {
            return this;
        }
        return targetNode;
    }
}
