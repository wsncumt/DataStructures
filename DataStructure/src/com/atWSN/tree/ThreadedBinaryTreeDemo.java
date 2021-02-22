package com.atWSN.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        TreeNode1 root = new TreeNode1(1, "tom");
        TreeNode1 node1 = new TreeNode1(3, "jack");
        TreeNode1 node2 = new TreeNode1(6, "smith");
        TreeNode1 node3 = new TreeNode1(8, "marry");
        TreeNode1 node4 = new TreeNode1(10, "king");
        TreeNode1 node5 = new TreeNode1(14, "dim");
        root.leftChild = node1;
        node1.leftChild = node3;
        node1.rightChild = node4;
        root.rightChild = node2;
        node2.leftChild = node5;
        ThreadedBinaryTree treeNode1 = new ThreadedBinaryTree();
        treeNode1.setRoot(root);
        treeNode1.threadedNodes();
        System.out.println(node4.leftChild);
        System.out.println(node4.rightChild);
        treeNode1.threadedList();
    }
}

class ThreadedBinaryTree{
    private TreeNode1 root;
    //为了实现线索化，需要一个指向当前节点前驱结点的引用
    //在递归过程中，preNode始终指向该节点的前驱结点
    private TreeNode1 preNode = null;
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
        TreeNode1 tmpNode = new TreeNode1();
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

    public TreeNode1 preOrderSearch(int target) {
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

    public TreeNode1 infixOrderSearch(int target) {
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

    public TreeNode1 postOrderSearch(int target) {
        return root.postOrderSearch(target);
    }

    public TreeNode1 getRoot() {
        return root;
    }

    public void setRoot(TreeNode1 root) {
        this.root = root;
    }


    //线索化二叉树后的遍历
    public void threadedList(){
        //定义一个临时变量来存储当前访问的节点
        //遍历是从root开始的
        TreeNode1 tmpNode =root;
        while(tmpNode != null){
            //循环遍历找到第一个leftType为1的节点
            //node随着遍历会变化
            //当leftType为1的时候，该节点是按照线索化处理后的有效节点
            while(tmpNode.leftType == 0){
                tmpNode = tmpNode.leftChild;
            }
            //打印当前节点
            System.out.println(tmpNode);
            //如果当前节点的右指针指向后继节点
            while (tmpNode.rightType == 1){
                tmpNode = tmpNode.rightChild;
                System.out.println(tmpNode);
            }
            //如果当前节点的右指针指向的是树
            //把当前节点替换为它的右孩子
            tmpNode = tmpNode.rightChild;
        }
    }

    public void threadedNodes(){
        threadedNodes(root);
    }

    //对二叉树进行中序线索化的方法
    public void threadedNodes(TreeNode1 node) {
        //如果当前节点为空，不能进行线索化
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(node.leftChild);
        //再线索化当前节点
        //先处理当前节点的前驱节点
        if (node.leftChild == null) {
            //如果当前节点的左孩子为空，就让其左孩子的引用指向其前驱节点
            //将当前节点的leftType改为1
            node.leftChild = preNode;
            node.leftType = 1;
        }
        //处理后继节点，下次处理的
        if (preNode != null && preNode.rightChild == null) {
            preNode.rightChild = node;
            preNode.rightType = 1;
        }
        //每处理一个节点后，让当前节点的前驱节点是下一个节点的前驱结点
        preNode = node;
        //再线索化右子树
        threadedNodes(node.rightChild);
    }
}

//创建节点
class TreeNode1 {

    //leftType == 0 ，表示指向左子树
    //leftType == 1，表示指向前驱结点
    //rightType == 0 ，表示指向右子树
    //rightType == 1，表示指向后继结点
    public int leftType;
    public int rightType;
    private int num;
    private String name;
    public TreeNode1 leftChild;
    public TreeNode1 rightChild;
    public TreeNode1 parent;


    public TreeNode1() {

    }

    public TreeNode1(int num, String name) {
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
    public void delTreeNode(int del) {
        if (this.leftChild != null) {
            if (this.leftChild.num == del) {
                this.leftChild = null;
                return;
            }
        }
        if (this.rightChild != null) {
            if (this.rightChild.num == del) {
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
    public TreeNode1 preOrderSearch(int target) {
        if (this == null || this.num == target) {
            return this;
        }
        TreeNode1 targetNode = null;
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
    public TreeNode1 infixOrderSearch(int target) {
        if (this == null) {
            return this;
        }
        TreeNode1 targetNode = null;
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

    public TreeNode1 postOrderSearch(int target) {
        if (this == null) {
            return this;
        }
        TreeNode1 targetNode = null;
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
