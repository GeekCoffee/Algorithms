package com.geektech.Nonlinear_structure;

import com.geektech.linear_structure.base.LinkedList;

/**
 *  基于链表或者哈希表实现的Set，都是无序集合
 *  基于LinkedList实现的Set,属于更加高级的数据结构  date = 5/5 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个 => O(1)
 *        基本：getSize, isEmpty  => O(1)
 *        增加：add(e),不能有重复元素 => O(n) + O(1) = O(n)
 *        查询：contains(e)  => O(n)
 *        删除：remove(e)   => O(n)
 **/

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) { //不能有重复的元素
        if(!list.contains(e))  // O(n)
            list.addFirst(e);  //头添加，O(1)
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }


}
