package com.example.aopdemo;

public class Main {
    public static void main(String args[]){
      A a = new A();
        a.m1();
        a.m1();
    }
}
class A{
    public void  m1(){
        System.out.println("Inside A");
    }
}
class B extends  A{
    public void m1(){
        System.out.println("inside B");
    }
}
