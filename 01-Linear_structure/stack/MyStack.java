package com.geektech.linear_structure.stack;

import com.geektech.linear_structure.base.Vector;

/**
 *  underlying implementation of Stack,  date = 4/26 2019 ,  author = chensheng
 *  ADT: 构造器：constructor2个  => O(1)
 *        基本：getSize, isEmpty => O(1)
 *        增加：push(e) => 渐进复杂度最坏情况O(n),最好情况O(1); 均摊复杂度O(1)
 *        删除：pop() => O(1)
 *        取出栈顶元素：peek() =>O(1)
 *
 *  实现思想：1)基于动态数组，即向量去实现的，其本质还是维护一个静态数组。
 *          2)数组最后一个元素的地方是栈顶top
 *
 *  栈的应用：回撤功能、程序调用中的系统栈、计算式子的计算、括号匹配、递归等
 *
 */
public class MyStack<E> implements Stack<E>{

    private Vector<E> vector;

    public MyStack(int capacity){
        vector = new Vector<E>(capacity);
    }

    public MyStack(){
        vector = new Vector<E>();
    }

    @Override
    public int getSize() {
        return vector.getSize();
    }


    public int getCapacity(){
        return vector.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    @Override
    public void push(E e)  {
        vector.addLast(e);
    }

    @Override
    public E pop() {
        return vector.removeLast();
    }

    @Override
    public E peek() {
        return vector.getLast();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("stack: [");
        for(int i = 0; i < vector.getSize(); i++){
            str.append(vector.get(i));
            if(vector.getSize()-1 != i)
                str.append(",");
        }
        str.append(" ->");
        return str.toString();
    }
}
