package com.geektech.Nonlinear_structure.Map;

import com.geektech.Semilinear_structure.BST;

/**
 *  基于Binary Search Tree实现的Map，有序映射，无序映射-Hash表实现,  date = 11/5 2019 ,  author = chensheng
 *  ADT-构造器：constructor-Node1个 , BSTMap1个
 *      基本：getSize(), isEmpty()
 *      添加映射：add(k,v), 不能有重复元素,默认做value的替换操作 => O(h),平均O(logn),最坏O(n)
 *      查询映射：contains(k) => O(h),平均O(logn),最坏O(n)
 *      得到某映射：get(k) => O(h),平均O(logn),最坏O(n)
 *      替换某映射：set(k,newValue) => O(h),平均O(logn),最坏O(n)
 *      删除映射：remove(e) => O(h),平均O(logn),最坏O(n)
 *      辅助：getNode(k)->返回Node(k,v) => O(h),平均O(logn),最坏O(n)
 *
 *  映射小知识：1）映射，也叫字典，在Python中就有dict类型
 *            2）映射就是一个key-value，这样一对一对的数据结构
 *
 **/
public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    //私有Node类
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    //在BST中寻找K所在的Node
    private Node getNode(Node node, K key){
        if(node == null) //找不到node的情况，或者root为null的情况
            return null;

        if(key.compareTo(node.key) == 0){ //找到node
            return node;
        }else if(key.compareTo(node.key) < 0){ // key小于node.key，说明可能在左子树中，向左探
           return getNode(node.left, key);
        }else{ //key大于node.key,说明目标node可能在右子树中
            return getNode(node.right, key);
        }
    }

    @Override
    public void add(K key, V val) { //有重复key时，做默认的value替换
        root = add(root, key, val);
    }

    private Node add(Node node, K key, V value){
        if(node == null){ //说明root为null或者BST中没有key对应的node,添加key
            size ++;
            return new Node(key,value);
        }

        //若key存在,找出key所在的Node
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        }else //node.key == key时
            node.value = value;

        return node;
    }


    /**
     * 当key有左右子树的时候，使用Hibbard提出的Hibbard Deletion
     * 1)设待删除元素为d(k,v), 找到s = findMin(d->rigth)
     * 2)s是d的后继元素，当然也可以使用d的前驱最大的元素
     * 3)s->rigth = removeMin(d->right) //返回待删除元素的右子树中删除了最小元素min，之后的node索引
     * 4)s->left = d->left
     * 5)s是代替d的新节点
     * @param key
     */
    //在BST中删除key的node
    @Override
    public V remove(K key){
        Node node = getNode(root, key);
        if(node != null){ //node不等于0，说明node(k,v)在BST中，可以进行删除
            root = remove(root, key);
            return node.value;
        }
        return null; //没有在BST中找到key
    }

    private Node remove(Node node, K key){
        if(node == null) //递归基，没有找到node(key,value)元素，或者root为null
            return null;

        //先找到待删除节点, 以node为根的二叉树
        if(node.key.compareTo(key) > 0){ // 若传入的key比node.key小，则说明待删除的节点在左子树中,向左探索
            node.left = remove(node.left, key); //若没有找到key，则node.left还是null的，返回node，BST结构不变
            return node;
        }
        else if(node.key.compareTo(key) < 0){
            node.right = remove(node.right, key);
            return node;
        }
        else{ // key == node.key , 已经找到待删除节点

            //情况1：当待删除节点只有右子树，左子树为null时 or 左右子树都为null时
            //复用removeMin()算法
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode; // 返回待删除元素的右子树索引
            }


            //情况2：当待删除节点只有左子树,右子树为null时 or 左右子树都为null时
            //复用removeMax()算法
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            //情况3：当待删除节点有左右子树时，使用Hibbard Deletion - 1962年
            //successor是继承者的意思，取代待删除节点的位置，以保持BST的特性
            //不用size--了，因为在removeMin中已经删除了一个元素了，并且size--了
            Node successor = findMin(node.right);   //step1：找到e右子树中元素最小的元素，并赋给successor
            successor.right = removeMin(node.right); //step2：删除e右子树中最小的元素，并把以node为根的子树句柄赋值给successor
            successor.left = node.left; // step3：把待删除节点e的左子树索引赋值给successor.left
            node.left = node.right = null; // step4：把待删除节点置为null，GC会自动把它从内存在回收起来，就达到删除的目的了
            return successor;
        }
    }

    private Node findMin(Node node){
        if(node.left == null)
            return node;
        return findMin(node.left); //node左孩子不为空，则继续向左探索
    }

    private Node removeMin(Node node){
        if(node.left == null){ //递归终止条件， 此时已经是最小元素
            Node rightNode = node.right; //把要删除节点的右子树赋值给rightNode
            node.right = null;
            size --;
            return rightNode;  //返回删除节点的右子树
        }
        node.left = removeMin(node.left);//把右子树继续成为该删除节点的父亲节点的左子树
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null; //不等于null说明有Node返回，则为true
    }

    @Override
    public V get(K key) { //key有可能找不到，所以需要做null的判断
        Node node = getNode(root, key);
        return node == null?null:node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null) //要set的key不存在，做一下异常判断
            throw new IllegalArgumentException("输入的key: "+ key + " 不存在");
        node.value =value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printMap(){ //遍历Map其实就是遍历一棵树
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null)
            return;

        System.out.println(node.key+ " : "+node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args){
        Map<String,String> map = new BSTMap<>();
        map.add("id","20152211");
        map.add("name","陈圣");
        map.add("age","23");
        map.add("coding skills","Golang、java、Docker etc...");
        map.printMap();

        map.set("name","陈云飞");
        map.printMap();
        System.out.println("size: " + map.getSize());

        System.out.println("-----------");
        map.remove("age");
        map.printMap();
    }
}
