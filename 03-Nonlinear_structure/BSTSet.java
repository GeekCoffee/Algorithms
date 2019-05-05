package com.geektech.Nonlinear_structure;
import com.geektech.Semilinear_structure.BST;
/**
 *  基于搜索树实现的Set，都是有序集合
 *  基于BST实现的Set,属于更加高级的数据结构  date = 4/5 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个 => O(1)
 *        基本：getSize, isEmpty => O(1)
 *        增加：add(e),不能有重复元素  => O(h): 平均时间性能O(logn) ， 最差退化成链表 O(n) 。 n为节点数
 *        查询：contains(e)  => O(h)
 *        删除：remove(e)  => O(h)
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
