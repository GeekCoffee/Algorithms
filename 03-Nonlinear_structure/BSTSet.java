package com.geektech.Nonlinear_structure;
import com.geektech.Semilinear_structure.BST;
/**
 *  基于BST实现的Set,属于更加高级的数据结构  date = 4/5 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个
 *        基本：getSize, isEmpty
 *        增加：add(e),不能有重复元素
 *        查询：contains(e)
 *        删除：remove(e)
 **/
public class BSTSet<E extends Comparable<E>> implements Set<E>{

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) { //不能重复元素
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    public static void main(String[] args) {

    }
}
