package com.geektech.Nonlinear_structure;

import com.geektech.linear_structure.base.LinkedList;

/**
 *  基于LinkedList实现的Set,属于更加高级的数据结构  date = 5/5 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个
 *        基本：getSize, isEmpty
 *        增加：add(e),不能有重复元素
 *        查询：contains(e)
 *        删除：remove(e)
 **/
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) { //不能有重复的元素
        if(!list.contains(e))
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
