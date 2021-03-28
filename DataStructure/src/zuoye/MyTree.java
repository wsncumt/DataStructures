package zuoye;

import java.util.*;
import java.util.Queue;

/**
 * @program: DataStructures
 * @description
 * @author:戛剑生
 * @creat: 2021-03-18 20:09:22
 **/
public class MyTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    TreeNode root;

    //先序遍历：递归法
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历：递归法
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        System.out.println(root.val);
        preOrder(root.right);
    }

    //后序遍历：递归法
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        preOrder(root.right);
        System.out.println(root.val);
    }

    //求节点的个数
    public int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.right) + count(root.left);
    }

    //求叶子节点的个数
    public int leafNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return leafNodeCount(root.left) + leafNodeCount(root.right);
    }

    //求第k层节点的个数=求左子树的第k-1层节点个数+求右子树的k-1层节点个数
    public int getKLevelCount(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKLevelCount(root.left, k - 1) + getKLevelCount(root.right, k - 1);
    }

    //查找节点
    public TreeNode findNode(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode ret = findNode(root.left, val);
        if (ret != null) {
            return ret;
        }
        return findNode(root.right, val);
    }

    //求二叉树的高度
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    //层序遍历
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            System.out.println(tmp.val);
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
    }

    public boolean isCompleteTree(TreeNode root) {
        //1.针对二叉树进行层次遍历
        //2.要求每个节点必须有两棵子树
        //3.某个节点只有左子树或没有右子树，进入第二阶段(该阶段中的节点全为叶子节点，有子树就不是完全二叉树)
        //4.某个节点只有右子树，不是完全二叉树
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            if (!flag) {
                if (tmp.right != null && tmp.left != null) {
                    queue.offer(tmp.left);
                    queue.offer(tmp.right);
                } else if (tmp.left==null&&tmp.right != null) {
                    return false;
                } else if (tmp.left != null) {
                    queue.offer(tmp.left);
                    flag = true;
                } else {
                    flag = true;
                }
            } else {
                if (tmp.left != null || tmp.right!=null){
                    return false;
                }
            }
        }
        return true;
    }

    //迭代版本的前序遍历
    //思路：使用一个栈，打印当前节点，先把该节点的右子树推入栈中，在把该节点的左子树推入栈中
    //当栈不为空时，取出栈顶元素，重复上述操作
    public void preOrder1(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
    }

    //中序遍历
    public void inOrder1(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            System.out.println(node.val);
            cur = node.right;
        }
    }

    //后序
    public void postOrder1(TreeNode root){
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val);
        }

    }
}
