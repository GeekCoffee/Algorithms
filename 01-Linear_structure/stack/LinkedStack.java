package com.geektech.linear_structure.stack;

import com.geektech.linear_structure.base.LinkedList;

/**
 *  LinkedStack,  date = 4/27 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个  => O(1)
 *        基本：getSize, isEmpty => O(1)
 *        增加：push(e) => 渐进复杂度最坏情况O(c)，new 空间需要一点时间
 *        删除：pop() => O(1)
 *        取出栈顶元素：peek() =>O(1)
 *
 *  实现思想：1)基于单链表实现
 *          2)单链表头是栈顶top
 *
 *  栈的应用：回撤功能、程序调用中的系统栈、计算式子的计算、括号匹配、递归等
 *
 */

public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedStack(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public void push(E e){
        list.addFirst(e);
    }

    @Override
    public E pop(){
        return list.removeFirst();
    }

    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Stack top ");
        str.append(list);
        return list.toString();
    }

}
