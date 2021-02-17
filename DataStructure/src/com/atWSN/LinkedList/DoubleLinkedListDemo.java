package com.atWSN.LinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("--------------------------------------");
        System.out.println("双向链表的测试：");
        HeroNode2 node1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 node2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 node3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 node4 = new HeroNode2(4,"林冲","豹子头");
        DoubleLinkedList doubleLinkedList1 = new DoubleLinkedList();
        //添加
        doubleLinkedList1.add(node1);
        doubleLinkedList1.add(node2);
        //doubleLinkedList1.add(node3);
        doubleLinkedList1.add(node4);
        doubleLinkedList1.list();
//        //修改
//        HeroNode2 newNode = new HeroNode2(2, "公孙胜", "入云龙");
//        doubleLinkedList1.update(newNode);
//        System.out.println("--------------------------------------");
//        System.out.println("双向链表的测试：(修改链表内容)");
//        doubleLinkedList1.list();
//        //删除
//        doubleLinkedList1.delete(node2);
//        doubleLinkedList1.list();
//        doubleLinkedList1.delete(node4);
//        doubleLinkedList1.list();
        //
        System.out.println("--------------------------------------");
        System.out.println("测试：添加相同元素");
        HeroNode2 addNode1 = new HeroNode2(4,"林冲","豹子头");
        doubleLinkedList1.addByOrder(addNode1);
        doubleLinkedList1.list();
        //HeroNode2 addNode1 = new HeroNode2(4,"林冲","豹子头");
        System.out.println("--------------------------------------");
        System.out.println("往中间添加元素：");
        doubleLinkedList1.addByOrder(node3);
        doubleLinkedList1.list();
        System.out.println("--------------------------------------");
        System.out.println("往末尾添加元素：");
        HeroNode2 node5 = new HeroNode2(5,"鲁智深","花和尚");
        doubleLinkedList1.addByOrder(node5);
        doubleLinkedList1.list();
        System.out.println("--------------------------------------");
        System.out.println("往开头添加元素：");
        HeroNode2 node0 = new HeroNode2(0,"0","0");
        doubleLinkedList1.addByOrder(node0);
        doubleLinkedList1.list();
    }
}

//双向链表类
class DoubleLinkedList {
    //初始化
    private HeroNode2 head = new HeroNode2();

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("[]");
            return;
        }
        HeroNode2 curNode = head.next;
        System.out.print("[");
        while (true) {
            if (curNode == null) {
                break;
            }
            System.out.print(curNode.toString());
            if (curNode.next != null) {
                System.out.print(",");
            }
            curNode = curNode.next;
        }
        System.out.println("]");
    }

    //尾插
    public void add(HeroNode2 heroNode) {
        HeroNode2 curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        curNode.next = heroNode;
        heroNode.pre = curNode;
    }

    //方式2：按编号的顺序添加
    //如果编号存在，抛出异常
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 curNode = head.next;
        while(curNode != null && curNode.getNum() < heroNode.getNum()){
            curNode = curNode.next;
        }
        if(curNode == null){
            add(heroNode);
            return;
        }
        if (curNode.getNum() == heroNode.getNum()){
            System.out.println("链表中已有数据，无法添加。");
            return;
        }
        heroNode.next = curNode;
        heroNode.pre = curNode.pre;
        curNode.pre.next = heroNode;
        curNode.pre = heroNode;
    }

    //修改链表中节点的信息：根据编号来改变名字和昵称
    public void update(HeroNode2 heroNode) {//根据heraNode的num来修改
        if (head.next == null) {
            System.out.println("链表中无节点，无法修改！");
            System.out.println("修改失败！");
            return;
        }
        HeroNode2 curNode = head.next;
        while (curNode != null) {
            if (curNode.getNum() == heroNode.getNum()) {
                curNode.setName(heroNode.getName());
                curNode.setNickName(heroNode.getNickName());
                return;
            }
            curNode = curNode.next;
        }
        System.out.println("没有找到相关节点，无法修改！");
        System.out.println("修改失败！");
    }

    public void delete(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表中无节点，无法删除！");
            System.out.println("删除失败！");
            return;
        }
        HeroNode2 curNode = head.next;
        while (curNode != null) {
            if (curNode.getNum() == heroNode.getNum()) {
                curNode.pre.next = curNode.next;
                if (curNode.next != null) {
                    curNode.next.pre = curNode.pre;
                }
                return;
            }
            curNode = curNode.next;
        }
        System.out.println("链表中无该节点，无法删除！");
        System.out.println("删除失败！");
    }
}

class HeroNode2 {
    private int num;
    private String name;
    private String nickName;
    public HeroNode2 pre;
    public HeroNode2 next;


    //构造器
    //无参版
    public HeroNode2() {

    }

    //有参版
    public HeroNode2(int num, String name, String nickName) {
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNum() {
        return this.num;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + getNum() +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
