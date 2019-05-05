package com.geektech.Nonlinear_structure.Map;

/**
 *  Map的接口,  date = 5/5 2019 ,  author = chensheng
 *  ADT-基本：getSize(), isEmpty()
 *      添加映射：add(k,v), 不能有重复元素
 *      查询映射：contains(k)
 *      得到某映射：get(k)
 *      替换某映射：set(k,newValue)
 *      删除映射：remove(e)
 *
 *  映射小知识：1）映射，也叫字典，在Python中就有dict类型
 *            2）映射就是一个key-value，这样一对一对的数据结构
 *
 **/

public interface Map<K,V> {

    void add(K key, V val);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    int getSize();
    void set(K key, V value);
    boolean isEmpty();

}













