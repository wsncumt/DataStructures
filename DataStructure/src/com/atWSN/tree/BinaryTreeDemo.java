package com.atWSN.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode treeNodeRoot = new TreeNode(1, "宋江");
        TreeNode treeNode1 = new TreeNode(2, "吴用");
        TreeNode treeNode2 = new TreeNode(3, "卢俊义");
        TreeNode treeNode3 = new TreeNode(4, "林冲");
        TreeNode treeNode4 = new TreeNode(5, "关胜");
        binaryTree.setRoot(treeNodeRoot);
        addLeft(treeNodeRoot, treeNode1);
        addRight(treeNodeRoot, treeNode2);
        addRight(treeNode2, treeNode3);
        addLeft(treeNode2, treeNode4);

//        System.out.println("-------------------");
//        binaryTree.preOrder();
//        System.out.println("-------------------");
//        binaryTree.infixOrder();
//        System.out.println("-------------------");
//        binaryTree.postOrder();
//        System.out.println("-------------------");
//        int target = 0;
//        System.out.println(binaryTree.preOrderSearch(target));;
//        System.out.println("-------------------");
//        System.out.println(binaryTree.infixOrderSearch(target));;
//        System.out.println("-------------------");
//        System.out.println(binaryTree.postOrderSearch(target));;
        System.out.println("-------------------");
        System.out.println("删除前：前序遍历");
        binaryTree.preOrder();
        //删除
        int del = 5;
        binaryTree.delNode2(del);
        System.out.println("-------------------");
        System.out.println("删除后：前序遍历");
        binaryTree.preOrder();
    }

    //插入左孩子
    public static void addLeft(TreeNode parentNode, TreeNode leftNode) {
        if (parentNode.leftChild != null) {
            System.out.println("该节点有左孩子，无法插入");
            return;
        }
        parentNode.leftChild = leftNode;
        leftNode.parent = parentNode;
    }

    //插入右孩子
    public static void addRight(TreeNode parentNode, TreeNode rightNode) {
        if (parentNode.rightChild != null) {
            System.out.println("该节点有左孩子，无法插入");
            return;
        }
        parentNode.rightChild = rightNode;
        rightNode.parent = parentNode;
    }
}

class BinaryTree {
    private TreeNode root;

    //删除1
    public void delNode1(int del){
        if (root == null){
            System.out.println("系统提示：空树，无法删除！");
            System.out.println("删除失败！");
            return;
        }
        if (root.getNum() == del){
            System.out.println("删除成功！");
            root = null;
            return;
        }
        root.delTreeNode(del);

    }
    //删除2
    public void delNode2(int del){
        TreeNode tmpNode = new TreeNode();
        tmpNode.leftChild = root;
        tmpNode.delTreeNode(del);
        root = tmpNode.leftChild;

    }
    //前序遍历
    public void preOrder() {
        System.out.println("前序遍历的结果为：");
        if (this.root == null) {
            System.out.println("{}");
            return;
        }
        this.root.preOrder();
    }

    public TreeNode preOrderSearch(int target) {
        return root.preOrderSearch(target);
    }

    //中序遍历
    public void infixOrder() {
        System.out.println("中序遍历的结果为：");
        if (this.root == null) {
            System.out.println("{}");
            return;
        }
        this.root.infixOrder();
    }

    public TreeNode infixOrderSearch(int target) {
        return root.infixOrderSearch(target);
    }

    //后序遍历
    public void postOrder() {
        System.out.println("后序遍历的结果为：");
        if (this.root == null) {
            System.out.println("{}");
            return;
        }
        this.root.postOrder();
    }

    public TreeNode postOrderSearch(int target) {
        return root.postOrderSearch(target);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}

