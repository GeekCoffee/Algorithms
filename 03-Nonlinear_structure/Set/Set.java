package com.geektech.Nonlinear_structure.Set;

/**
 *  Set的接口,  date = 4/5 2019 ,  author = chensheng
 *  ADT-基本：getSize, isEmpty
 *      增加：add(e), 不能有重复元素
 *      查询：contains(e)
 *      删除：remove(e)
 **/

public interface Set<E> {

    void add(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
    boolean contains(E e);

}
