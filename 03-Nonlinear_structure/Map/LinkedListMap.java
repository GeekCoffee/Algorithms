package com.geektech.Nonlinear_structure.Map;

/**
 *  基于单向链表实现的Map,  date = 5/5 2019 ,  author = chensheng
 *  ADT-构造器：constructor-Node3个 , LinkedListMap1个
 *      基本：getSize(), isEmpty()
 *      添加映射：add(k,v), 不能有重复元素
 *      查询映射：contains(k)
 *      得到某映射：get(k)
 *      替换某映射：set(k,newValue)
 *      删除映射：remove(e)
 *      辅助：getNode(k)->返回Node
 *
 *  映射小知识：1）映射，也叫字典，在Python中就有dict类型
 *            2）映射就是一个key-value，这样一对一对的数据结构
 *
 **/
public class LinkedListMap<K, V> implements Map<K, V> {

    //重新定义的内部节点类,一个Node对象存储一个<k,v>
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();
            return res.append(key + " : " + value).toString();
        }
    }

    private Node dummyHead;
    private int size;


    public LinkedListMap(){
        dummyHead = new Node(null,null,null);
        this.size = 0;
    }

    //辅助方法：在链表中，帮助找到key对应的Node
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.key.equals(key))  //找到对应的node
                return cur;
            cur = cur.next;
        }
        return null; //没有找到对应的node
    }

    @Override
    public void add(K key, V val) {
        if(getNode(key) == null){  //则在链表中没有Node节点,还没有key这个键
            dummyHead.next = new Node(key, val, dummyHead.next); // 插入一个新节点，头插法
            size ++;
        }else //若存在，默认替换val
            getNode(key).value = val;
    }

    @Override
    public V remove(K key) {
        if(getNode(key) == null){
            throw new IllegalArgumentException("key不存在，因此不能删除");
        }else{ //key存在，删除链表中相应的节点
            Node pre = dummyHead;
            Node cur = dummyHead.next;
            V ret = null;
            while(cur != null){
                if(cur.key.equals(key)){
                    ret = cur.value;
                    pre.next = cur.next;
                    cur.next = null;
                    cur = pre.next;
                }
                if(cur == null)
                    break;
                pre = cur;
                cur = cur.next;
            }
            size --;
            return ret;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) == null?false:true;
    }

    @Override
    public V get(K key) {
        return getNode(key) == null? null : getNode(key).value;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void set(K key, V value) {
        if(getNode(key) == null)
            throw new IllegalArgumentException("key不存在");
        else
            getNode(key).value = value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
