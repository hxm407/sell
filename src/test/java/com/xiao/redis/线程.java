package com.xiao.redis;

public class 线程 {
    public static void main(String args[]){
        Student student1 = new Student("lili");
        System.out.println("--------------------");
        Student student2 = new Student();
    }
}
class Person{
    static {
        System.out.println("执行Person静态代码块");
    }
    {
        System.out.println("执行Person构造代码块");
    }
    public Person(){
        System.out.println("执行Person无参构造方法");
    }
    public Person(String name){
        System.out.println("执行Person构造方法"+ name);
    }
}

class Student extends Person{
    static {
        System.out.println("执行Student静态代码块");
    }
    {
        System.out.println("执行Student构造代码块");
    }
    public Student(String name){
        super(name);
        System.out.println("执行Student构造方法" + name);
    }
    public Student(){
        super();
        System.out.println("执行Student无参构造方法");
    }
}
