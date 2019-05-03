package com.geektech.linear_structure.base;
/**
 *  LinkedList，内存中不连续,  date = 4/27 2019 ,  author = chensheng
 *  ADT: 构造器：Node-constructor3个 ,LinkedList-constructor1个
 *        基本：getSize, isEmpty =>  O(1)
 *        增加：add , addLast ==> 渐进复杂度 O(n) , addFirst ==> 渐进复杂度O(1)
 *        删除：
 *        查找：contains、get、getLast ==> 渐进复杂度 O(n) ， getFirst ==> O(1)
 *        替换：set ==> O(n)
 *
 *
 * 单向链表要点：1）add的时候，关键是要找到index之前的节点，即index-1的节点prev, 所以for循环要多走一步
 *            2）为了在index=0的地方插入节点，不需要特殊处理，就需要设置一个dummyHead
 *            3）虚拟头结点，在内存中真正存在的，真正的head头结点在虚拟头结点(dummyHead)节点之后
 *            4）prev是前一个节点的意思，cur为当前节点的意思
 *            5）所有操作的索引都从0开始，dummyHead所在的index=-1
 *      重点： 6）tail一端删除元素不容易，添加容易；所以单向链表中还是使用dummyHead来删除元素
 *
 *
 */
public class LinkedList<E> {

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

    private Node dummyHead;  //虚拟头结点，在内存中真正存在的，用于更方便插入操作，真正的头结点在dummyHead节点之后
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index,E e){
        if(index < 0 || index > size){ //index=size是可以的，就是在最后位置插入一个元素
            throw new IllegalArgumentException("index非法...");
        }

        Node prev = dummyHead; //初始化prev

        //找到index之前的节点prev,因为是从dummyHead开始的，所以要多走一步
        for(int i = 0; i < index; i++){
            prev = prev.next;  //prev++
        }

//        Node newNode = new Node(e,null);
//        newNode.next = prev.next;
//        prev.next = newNode;

        prev.next = new Node(e, prev.next);  //简洁写法
        size ++;

    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index非法...");
        }

        Node cur = dummyHead.next; //初始化cur为index=0
        for(int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }


    /**
     * 获得index位置的元素值
     * @param index
     * @return
     */
    public E get(int index){
        if(index < 0 || index >= size){ //index=size是可以的，就是在最后位置插入一个元素
            throw new IllegalArgumentException("index非法...");
        }
        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        return (E)cur.e;
    }

    public E getFirst(){
       return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    /**
     * 查看链表中是否包含与e元素值相同的元素
     * @param e
     * @return
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }


    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index非法...");
        }

        Node prev = dummyHead;
        for(int i = 0; i < index; i++)
            prev = prev.next;

        Node retNode = prev.next;

        //删除操作
        prev.next = retNode.next;
        retNode.next = null;
        size --;
        return (E)retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            str.append(cur + "->");
            cur = cur.next;
        }
        str.append("NULL");
        return str.toString();
    }





}
