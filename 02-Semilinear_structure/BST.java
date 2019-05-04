package com.geektech.Semilinear_structure;

import com.geektech.linear_structure.queue.LinkedQueue;
import com.geektech.linear_structure.queue.LoopQueue;
import com.geektech.linear_structure.queue.Queue;

/**
 *  BinarySearchTree - BST,  date = 1/5 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个  => O(c)
 *        基本：getSize, isEmpty => O(1)
 *        增加：add(e)，add2(e)
 *        查询：contains(e)
 *        遍历：preOrder,inOrder,postOrder,levelOrder-图的广度优先遍历
 *
 *
 *  BST的特性 ：1）存储的元素要有可比较性，所以泛型要集成Comparable类，<E extends Comparable<E>>
 *            2）BST中不包含重复元素，若插入一个BST中已存在的元素，则不做任何改变。
 *
 *  核心：1）层序遍历中，出队后的元素要马上入队其的左右孩子！
 *
 *  其他：1）前中后遍历都是图的深度优先遍历，层序遍历是图的广度优先遍历
 *       2）广度优先遍历的意义在于：①更快找到问题的解  ②常用于算法设计中的-最短路径
 *
**/
public class BST<E extends Comparable<E>>{

    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;  //树的根节点,以根节点唯一标识一棵树
    private int size;  //树的节点数

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //向BST中插入一个不重复的元素
    public void add(E e){
        if(root == null){  //当根节点为null
            root = new Node(e);
            size ++;
        }else{ //当root不为null时
            add(root, e);
        }
    }

    //以node为根节点的BST
    private void add(Node node, E e){
        if(node.e.compareTo(e) == 0) //BST中已经存在此元素
            return;
        else if(node.e.compareTo(e) > 0 && node.left == null){ //当node比e大，且node.left为null时
            node.left = new Node(e);
            size ++;
        }else if(node.e.compareTo(e) < 0 && node.right == null){ //当node比e小，且node.rigth为null时
            node.right = new Node(e);
            size ++;
        }

        //当node都有left和right子孩子时，进行递归操作
        if(node.e.compareTo(e) > 0 && node.left != null){
            add(node.left, e); //向左延伸
        }else if(node.e.compareTo(e) < 0 && node.right != null){
            add(node.right, e); //向右延伸
        }

    }

    public void add2(E e){
        root = add2(root, e);
    }

    //手工模拟一下BST的插入过程，就可以写出以下递归算法
    public Node add2(Node node, E e){
        if(node == null){ //向左或者向右延伸到null时插入元素
           size ++;
           return new Node(e);
        }

        if(node.e.compareTo(e) > 0) //当node比e大，向左延伸
            node.left = add2(node.left, e);
        else if(node.e.compareTo(e) < 0) //当node比e小时，向右延伸
            node.right = add2(node.right, e);

        return node; //返回插入新节点后BST的根
    }


    //查看BST中是否包含e元素
    public boolean contains(E e){
        if(root == null) // 此时根节点为null
            return false;
        else{ //当树不为null时
            return contains(root, e);
        }
    }

    //以node为根节点的BST
    private boolean contains(Node node, E e){
        if(node.e.compareTo(e) == 0)
            return true;
        else if(node.e.compareTo(e) > 0 && node.left != null){ // 若node.left为null，则返回false
            contains(node.left, e);  //向左探索
        }else if(node.e.compareTo(e) < 0 && node.right != null){
            contains(node.right, e); //向右探索
        }

        return false; //都探索完了，没有发现相同元素，则返回false
    }



    //树的前序遍历-根左右,递归实现
    public void preOrderRecur(){
        preOrderRecur(root);
    }

    private void preOrderRecur(Node node){
        if(node == null) //若root为null，则返回return
            return;

        System.out.print(node.e + " "); //根左右
        preOrderRecur(node.left); //向左探
        preOrderRecur(node.right);
    }

    //树的中序遍历-左根右，递归实现
    public void inOrderRecur(){
        this.inOrderRecur(root);
    }

    private void inOrderRecur(Node node){
        if(node == null)
            return;

        inOrderRecur(node.left); //先向左链探
        System.out.print(node.e + " "); // 只做输出操作,其实可以做一个传入函数操作
        inOrderRecur(node.right); //最后向右链探
    }

    //树的后续遍历-左右根，递归实现
    public void postOrderRecur(){
        postOrderRecur(root);
    }

    //设置一个内部方法，不让外界可以直接调用，完成算法逻辑
    private void postOrderRecur(Node node){
        if(node == null)
            return;
        postOrderRecur(node.left); //先向左链探
        postOrderRecur(node.right); //再向右链探
        System.out.print(node.e + " "); //最后做以node为根的访问操作
    }


    //树的层序遍历， 使用queue辅助完成
    //step1：在循环之前入队root，循环条件queue!=null
    //step2：root出队后，入队左右孩子
    //step3：左孩子出队后，入队左孩子的左右孩子，然后依次类推
    public void levelOrder(){
        Queue<Node> queue = new LoopQueue<>();
        queue.enQueue(root);
        while(!queue.isEmpty()){ //以size元素来判断是否为null
            Node cur = queue.deQueue();
            System.out.print(cur.e + " ");
            if(cur.left != null)
                queue.enQueue(cur.left);
            if(cur.right != null)
                queue.enQueue(cur.right);
        }
    }





    public static void main(String[] args){
        int[] nums = {3,4,5,1,2};
        BST<Integer> bst = new BST<>();
        for(int n: nums)
            bst.add(n);

        bst.preOrderRecur();
        System.out.println();
        bst.inOrderRecur();
        System.out.println();
        bst.postOrderRecur();
        System.out.println();
        bst.levelOrder();
    }

}











