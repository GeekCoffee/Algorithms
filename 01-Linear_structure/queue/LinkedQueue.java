package com.geektech.linear_structure.queue;

/**
 *  LinkedQueue,  date = 4/27 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个  => O(1)
 *        基本：getSize, isEmpty => O(1)
 *        增加：enQueue(e) => 渐进复杂度最坏情况O(1) ,涉及到动态空间开辟new
 *        删除：deQueue() => O(1)
 *        取出队列的队首元素：getFront()
 *
 *  实现思想：1）基于链表结构实现链队列
 *          2）链表head为队首front，链表tail为队尾tail。
 *          3）从front出队，从tail入队
 *
 *  关键：  1）没有dummyHead，只有真正的head和tail
 *         2）只有一个节点删除的时候，需要特殊处理tail一下
 *
 *  队列的应用：CPU的任务队列、网络数据包队列等
 *
 */

public class LinkedQueue<E> implements Queue<E>{

    //Node设置为内部类
    private class Node<E>{
        public E e;
        public Node next;
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enQueue(E e) {
        if(tail == null){  //当链队列为空时，进队第一个元素
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E deQueue() {
        if(isEmpty())
            throw new IllegalArgumentException("your linkedQueue is empty");
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if(head == null) //当只有一个元素被删除的时候，需要tail也归null，不然tail变成野指针
            tail = null;
        size --;
        return (E)retNode.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        return null;
    }

}
