package com.atWSN.datastructures.AVLtree;

import java.util.Random;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();
        for (int a : arr
        ) {
            avlTree.add(new Node(a));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.root.height());
        System.out.println("--------------------");
        System.out.println(avlTree.root.leftHeight());
        System.out.println(avlTree.root.rightHeight());
    }
}

//创建AVL树
class AVLTree {
    Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    //查找要删除的节点
    public Node search(int target) {
        if (root == null) {
            return null;
        }
        return root.search(target);
    }

    //查找删除节点的父节点
    public Node searchParent(int target) {
        if (root == null || root.num == target) {
            return null;
        }
        return root.searchParent(target);
    }

    //删除节点
    public void delNode(int del) {
        //空树
        if (root == null) {
            System.out.println("树为空，无法删除");
            return;
        }
        Node delNode = search(del);
        Node parentNode = searchParent(del);
        //没有找到节点
        if (delNode == null) {
            System.out.println("未找到节点！");
            return;
        }

        //待删除节点为叶子结点
        if (delNode.left == null && delNode.right == null) {
            //如果该树只有一个节点，且该节点为待删除节点
            //此时该节点的父节点为null
            if (parentNode == null) {
                root = null;
                return;
            }
            if (delNode == parentNode.left) {
                parentNode.left = null;
                return;
            }
            if (delNode == parentNode.right) {
                parentNode.right = null;
                return;
            }
        }
        //待删除节点有两棵子树
        if (delNode.right != null && delNode.left != null) {
            //随机填入该节点左树的最大值或右树的最小值。
            int random = new Random().nextInt(2);
            switch (random) {
                case 0:
                    int min = delRightTreeMin(delNode);
                    delNode.num = min;
                    break;
                case 1:
                    int max = delLeftTreeMin(delNode);
                    delNode.num = max;
                    break;
            }
            return;
        }
        //删除节点只有一个子树
        if (delNode.left == null) {
            if (parentNode == null) {
                root = root.right;
                return;
            }
            if (delNode == parentNode.left) {
                parentNode.left = delNode.right;
                return;
            }
            if (delNode == parentNode.right) {
                parentNode.right = delNode.right;
                return;
            }
        }
        if (delNode.right == null) {
            if (parentNode == null) {
                root = root.left;
                return;
            }
            if (delNode == parentNode.left) {
                parentNode.left = delNode.left;
                return;
            }
            if (delNode == parentNode.right) {
                parentNode.right = delNode.left;
                return;
            }
        }
    }

    /**
     * 功能：
     * 1.以node为根节点的右子树的最小值
     * 2.同时删除这个最小节点
     *
     * @param node 二叉排序树的根节点
     * @return 以node为根节点的右子树的最小值
     */
    public int delRightTreeMin(Node node) {
        int tmp = 0;
        Node curNode = node;
        if (node.right != null) {
            node = node.right;
        }
        while (node.left != null) {
            node = node.left;
        }
        tmp = node.num;
        delNode(tmp);
        return tmp;
    }

    /**
     * 功能：
     * 1.以node为根节点的左子树的最大值
     * 2.同时删除这个最大节点
     *
     * @param node 二叉排序树的根节点
     * @return 以node为根节点的左子树的最大值
     */
    public int delLeftTreeMin(Node node) {
        int tmp = 0;
        Node curNode = node;
        if (node.left != null) {
            node = node.left;
        }
        while (node.right != null) {
            node = node.right;
        }
        tmp = node.num;
        delNode(tmp);
        return tmp;
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        root.infixOrder();
    }

    public AVLTree() {

    }
}

//一个节点
class Node {
    public int num;
    public Node left;
    public Node right;

    public Node() {

    }

    public Node(int num) {
        this.num = num;
    }

    //左旋-zag
    public void zag() {
        Node newNode = new Node(this.num);
        //新节点的左子树为当前节点的左子树
        newNode.left = this.left;

        if (this.right != null) {
            //新节点的右子树为当前节点右子树的左子树
            newNode.right = this.right.left;
            //当前节点的值置为右子节点的值
            this.num = this.right.num;
            //把当前节点的右子树设为当前节点右子树右子树的右子树
            this.right = this.right.right;
        }
        //当前节点的左子树设置为新的节点
        this.left = newNode;

    }

    //右旋-zig
    public void zig() {
        Node newNode = new Node(this.num);
        //新节点的右子树为当前节点的右子树
        newNode.right = this.right;

        if (this.left != null) {
            //新节点的左子树为当前节点左子树的右子树
            newNode.left = this.left.right;
            //当前节点的值置为左子节点的值
            this.num = this.left.num;
            //把当前节点的左子树设为当前节点左子树左子树的右子树
            this.left = this.left.left;
        }
        //当前节点的右子树设置为新的节点
        this.right = newNode;

    }

    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    /**
     * 功能： 返回以node为根节点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //添加节点的方法
    //递归的形式添加节点
    //必须满足二叉排序树
    public void add(Node node) {
        if (node == null) {
            System.out.println("节点指向为空，无法添加");
            return;
        } else if (this.num >= node.num) {//传入节点的值和当前子树根节点的值的关系
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
//当右子树高度高于左子树时：即(rightHeight - leftHeight)>1，要左旋
        if ((this.rightHeight() - this.leftHeight()) > 1) {
            if (this.right != null && this.right.rightHeight() < this.right.leftHeight()) {
                this.right.zig();
            }
            zag();
            return;
        }
        //顺时针旋转
        if ((this.leftHeight() - this.rightHeight()) > 1) {
            if (this.left != null && this.left.right.rightHeight() > this.left.leftHeight()) {
                this.left.zag();
            }
            zig();
        }
    }

    //传入要删除的节点

    /**
     * @param target 希望删除节点的值
     * @return 找到返回对应的节点，找不到则返回null
     */
    public Node search(int target) {
        if (target == this.num) {//找到
            return this;
        } else if (target < this.num) {
            if (this.left != null) {
                return this.left.search(target);
            }
        } else {
            if (this.right != null) {
                return this.right.search(target);
            }
        }
        return null;
    }
    //要删除节点的父节点

    /**
     * @param target 要找的节点的值
     * @return 目标节点的父节点
     */
    public Node searchParent(int target) {
        if ((this.left != null && this.left.num == target) || (this.right != null && this.right.num == target)) {
            return this;
        }
        //如果查找的值小于当前节点的值，并且当前节点的左子树存在，递归的向左查找
        if (target < this.num && this.left != null) {
            return this.left.searchParent(target);
        }
        //如果查找得值大于当前节点的值，并且当前节点的右子树存在，递归的向右查找
        if (target > this.num && this.right != null) {
            return this.right.searchParent(target);
        }
        return null;
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this.num);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }
}