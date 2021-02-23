package com.atWSN.datastructures.hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        // 测试
        // 创建一个hash表
        HashTable hashTable = new HashTable(7);

        boolean flag = true;
        while (flag){
            System.out.println("----------------------------------------");
            System.out.println("a(add)：添加雇员！");
            System.out.println("l(list)：显示雇员！");
            System.out.println("f(find)：查找雇员！");
            System.out.println("d(del)：删除雇员");
            System.out.println("r(remove)：清空全部信息！");
            System.out.println("e(exit)：退出程序！");
            System.out.println("请输入选择：");
            String select = new Scanner(System.in).next();
            switch (select){
                case "e":
                    System.out.println("程序退出！");
                    flag = false;
                    break;
                case "a":
                    System.out.println("请输入员工id：");
                    int id = new Scanner(System.in).nextInt();
                    System.out.println("请输入员工姓名：");
                    String name = new Scanner(System.in).next();
                    Employee employee = new Employee(id,name);
                    hashTable.add(employee);
                    break;
                case "l":
                    hashTable.list();
                    break;
                case "f":
                    System.out.println("请输入要查找的雇员ID：");
                    int findId = new Scanner(System.in).nextInt();
                    Employee findEmployee = hashTable.findEmployeeById(findId);
                    if (findEmployee == null){
                        System.out.println("未找到该id对应的员工");
                    }else{
                        System.out.println(findEmployee);
                    }
                    break;
                case "d":
                    System.out.println("请输入要删除的雇员ID：");
                    int delId = new Scanner(System.in).nextInt();
                    hashTable.delEmployeeById(delId);
                    break;
                case "r":
                    hashTable.removeAll();
                    break;
                default:
                    System.out.println("输入错误，请重新输入：");
                    break;
            }
        }
    }
}
//一个雇员
class Employee{
    private int id;
    private String name;
    public Employee next;
    public Employee(){

    }
    public Employee(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "(id:" + id +
                ", name=" + name + ")";
    }
}

//创建employeeLinkedList，表示一条链表
class EmployLinkedList{
    private Employee head;

    //添加雇员到链表
    //添加雇员时，id总是增长的
    //所以把雇员添加在链表的末尾
    public void add(Employee employee){
        if (head == null){
            head = employee;
            return;
        }
        Employee preEmployee = head;
        while(preEmployee.next != null){
            preEmployee = preEmployee.next;
        }
        preEmployee.next = employee;
    }

    //删除雇员
    public void delEmployeeById(int id){
        Employee preEmployee = new Employee();
        Employee tmpEmployee;
        preEmployee.next = head;
        tmpEmployee = preEmployee;
        while(preEmployee.next != null){
            if (preEmployee.next.getId() == id){
                System.out.println("要删除的员工为：");
                System.out.println(preEmployee.next);
                preEmployee.next =  preEmployee.next.next;
                head = tmpEmployee.next;
                System.out.println("系统提示：删除成功");
                return;
            }
            preEmployee = preEmployee.next;
        }
        System.out.println("该雇员不在此表中，无法删除！");
        System.out.println("系统提示：删除失败");
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    //遍历链表的信息
    public void list(){
        if (head == null){
            System.out.print("[]");
            return;
        }

        Employee curEmployee = head;
        System.out.print("[");
        while (curEmployee != null){
            System.out.print(curEmployee.toString());
            if (curEmployee.next != null){
                System.out.print(",");
            }
            curEmployee = curEmployee.next;
        }
        System.out.print("]");
    }

    //查找某个员工
    public Employee findEmployeeById(int id){
        Employee curEmployee = head;

        while (curEmployee != null && curEmployee.getId() != id){
            curEmployee = curEmployee.next;
        }
        if (curEmployee != null){
            return curEmployee;
        }
        return curEmployee;
    }
}

//创建哈希表
class HashTable{
    private final int capacity;
    EmployLinkedList[] employLinkedLists;
    //构造方法
    public HashTable(int capacity){
        this.capacity = capacity;
        employLinkedLists = new EmployLinkedList[this.capacity];
        //此时该数组的每个元素都是null;要进行初始化
        for (int i = 0; i < capacity; i++) {
            employLinkedLists[i] = new EmployLinkedList();
        }
    }
    public void add(Employee employee){
        //根据员工的id，得到该员工应当添加到哪条链表
        employLinkedLists[hash(employee.getId())].add(employee);

    }

    public void list(){
        System.out.print("{");
        for (int i = 0; i < capacity; i++) {
            employLinkedLists[i].list();
            if (i != capacity - 1){
                System.out.print(",");
            }
        }
        System.out.println("}");
    }

    public Employee  findEmployeeById(int id){
        return employLinkedLists[hash(id)].findEmployeeById(id);
    }

    public void delEmployeeById(int id){
        employLinkedLists[hash(id)].delEmployeeById(id);
    }

    public void removeAll(){
        System.out.println("系统提示：清空所有信息，此操作不可撤销，是否真的要删除？（Y:删除，N：取消）");
        String  str = new Scanner(System.in).next();
        if ("Y".equalsIgnoreCase(str)){
            System.out.println("请再次确认是否要清空所有信息：（Y:确认，N：取消）");
            str = new Scanner(System.in).next();
            if (!("Y".equalsIgnoreCase(str))){
                return;
            }
            for (int i = 0; i < capacity; i++) {
                employLinkedLists[i].setHead(null);
            }
            System.out.println("系统提示：全部员工信息已删除！");
        }else{
            return;
        }

    }

    //编写散列函数：使用取模法来完成
    public int hash(int id){
        return id % this.capacity;
    }

}
