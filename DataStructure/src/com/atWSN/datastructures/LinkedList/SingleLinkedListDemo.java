package com.atWSN.datastructures.LinkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");


        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
//        singleLinkedList1.list();
        singleLinkedList1.addByOrder(heroNode1);
//        singleLinkedList1.list();
        singleLinkedList1.addByOrder(heroNode2);
        singleLinkedList1.addByOrder(heroNode4);
//        singleLinkedList1.list();
        singleLinkedList1.addByOrder(heroNode3);
        singleLinkedList1.list();
        HeroNode newHeroNode = new HeroNode(2, "李逵", "黑旋风");
        singleLinkedList1.update(newHeroNode);
        singleLinkedList1.list();
        singleLinkedList1.delete(heroNode1);
        singleLinkedList1.list();
        singleLinkedList1.delete(heroNode2);
        singleLinkedList1.list();
        singleLinkedList1.delete(heroNode3);
        singleLinkedList1.list();
        singleLinkedList1.delete(heroNode1);
        singleLinkedList1.delete(heroNode4);
        singleLinkedList1.list();
        singleLinkedList1.delete(heroNode1);
        singleLinkedList1.list();
    }
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头结点
    private HeroNode head =  new HeroNode();

    //尾插
    public void add(HeroNode heroNode){
        HeroNode curNode = head;
        while(true){
            if (curNode.next == null) {
                break;
            }
            curNode = curNode.next;
        }
        curNode.next = heroNode;
    }

    //方式2：按编号的顺序添加
    //如果编号存在，抛出异常
    public void addByOrder(HeroNode heroNode){
        HeroNode curNode = head;
        boolean flag = false;//标识添加的编号是否存在，默认不存在
        while(true){
            if (curNode.next == null){//说明curNode在链表的最后
                break;
            }
            if(curNode.next.getNum() > heroNode.getNum() ) {//位置找到了，就在curNode的后边
                break;
            }else if (curNode.next.getNum() == heroNode.getNum()){//说明编号存在
                System.out.println("节点" + heroNode.getNum() + "已存在在链表中！");
                System.out.println("添加失败！");
                return;
            }
            curNode = curNode.next;
        }
        heroNode.next = curNode.next;
        curNode.next = heroNode;
    }
    //显示链表
    public void list(){
        if (head.next == null){
            System.out.println("[]");
            return;
        }
        HeroNode curNode = head.next;
        System.out.print("[");
        while (true){
            if (curNode == null){
                break;
            }
            System.out.print(curNode.toString());
            if(curNode.next != null){
                System.out.print(",");
            }
            curNode = curNode.next;
        }
        System.out.println("]");
    }

    //修改链表中节点的信息：根据编号来改变名字和昵称
    public void update(HeroNode heroNode){//根据heraNode的num来修改
        if (head.next == null){
            System.out.println("链表中无节点，无法修改！");
            System.out.println("修改失败！");
            return;
        }
        HeroNode curNode = head.next;
        while (curNode != null){
            if (curNode.getNum() == heroNode.getNum()){
                curNode.setName(heroNode.getName());
                curNode.setNickName(heroNode.getNickName());
                return;
            }
            curNode = curNode.next;
        }
        System.out.println("没有找到相关节点，无法修改！");
        System.out.println("修改失败！");
    }

    //删除节点
    public void delete(HeroNode heroNode){
        if (head.next == null){
            System.out.println("链表中无节点，无法删除！");
            System.out.println("删除失败！");
            return;
        }
        HeroNode curNode = head;
        while (curNode.next != null){
            if (curNode.next.getNum() == heroNode.getNum()){
                curNode.next = curNode.next.next;
                return;
            }
            curNode = curNode.next;
        }
        System.out.println("链表中无该节点，无法删除！");
        System.out.println("删除失败！");
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    private int num;
    private String name;
    private String nickName;
    public HeroNode next;

    //构造器
    //无参版
    public HeroNode(){

    }
    //有参版
    public HeroNode(int num,String name,String nickName){
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