package com.geektech.Semilinear_structure;

import com.geektech.linear_structure.base.ArrayList;
import com.geektech.linear_structure.queue.LoopQueue;
import com.geektech.linear_structure.queue.Queue;
import com.geektech.linear_structure.stack.Stack;
import com.geektech.linear_structure.stack.LinkedStack;

/**
 *  BinarySearchTree - BST,  date = 1/5 2019 ,  author = chensheng
 *  ADT: 构造器：constructor1个  => O(c)
 *        基本：getSize, isEmpty => O(1)
 *        增加：add(e)，add2(e)
 *        查询：contains(e),findMax, findMin()
 *        删除：removeMin, removeMax, remove(e)
 *        遍历：preOrderRecur,inOrderRecur,postOrderRecur,levelOrder-借助queue-图的广度优先遍历
 *        非递归：preOrder_DP,preOrder_DP_Command,inOrder_DP_Command,postOrder_DP_Command
 *        时间复杂度：O(n)，取决于node的数量; 空间复杂度：O(h),取决于树的高度
 *        //TODO  Morris遍历
 *        Z字形打印BST：
 *
 *
 *
 *  BST的特性 ：1）存储的元素要有可比较性，所以泛型要集成Comparable类，<E extends Comparable<E>>
 *            2）BST中不包含重复元素，若插入一个BST中已存在的元素，则不做任何改变。
 *
 *  关键点：1）层序遍历中，出队后的元素要马上入队其的左右孩子！
 *        2）remove最大值或者最小值，用递归实现，用小数据手工模拟一下过程，就知道了怎么变化的了,
 *         有可能remove的是root，所以有可能重构树
 *       3）一直向左走，直到走不动了，就是最小值，不一定是叶子节点，有可能是根节点、或者中间节点。
 *
 *  其他：1）前中后遍历都是图的深度优先遍历，层序遍历是图的广度优先遍历
 *       2）广度优先遍历的意义在于：①更快找到问题的解  ②常用于算法设计中的-最短路径
 *
**/
public class BST<E extends Comparable<E>>{

    //内部节点类
    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private class Command{
        public String s;  // go, print
        public Node node;
        public Command(String s, Node node){
            this.s = s;
            this.node = node;
        }
    }

    private Node root;  //树的根节点,以根节点唯一标识一棵树
    private int size;  //树的节点数

    public BST(){
        root = null;
        size =  0;
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

        System.out.print(node.e + " "); //根-左子树-右子树
        preOrderRecur(node.left); //向左探
        preOrderRecur(node.right);
    }

    //前序遍历，非递归实现
    //step1：先把root入栈，出栈后压入root的右孩子，再压入左孩子
    //step2：然后出栈leftNode，再压入leftNode的右孩子，再压入左孩子...以此类推
    public void preOrder_DP(){
        Stack<Node> stack = new LinkedStack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.print(cur.e + " ");
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    //模拟系统栈实现, 根-左子树-右子树
    public void preOrder_DP_Command(){
        ArrayList<Integer> res = preOrder_DP_Command(root);
        for(int i = 0; i < res.getSize(); i++)
            System.out.print(res.get(i) + " ");
    }

    private ArrayList<Integer> preOrder_DP_Command(Node node){
        ArrayList<Integer> res = new ArrayList<>();
        if(node == null)
            return res;
        Stack<Command> stack = new LinkedStack<>();
        stack.push(new Command("go", node));  // 压入Command,目的是访问根节点
        while(!stack.isEmpty()){
            Command command = stack.peek();
            stack.pop();
            if(command.s.equals("print")) // print-command
                res.addLast((Integer) command.node.e);  //访问结果存入ArrayList中
            else{ // go-command
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right)); // 先压入右节点
                if(command.node.left != null)
                    stack.push(new Command("go", command.node.left)); // 再压入左节点
                //最后，先访问，后入栈, 入栈print指令
                stack.push(new Command("print", command.node));
            }

        }
        return res;
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

    //模拟系统栈，非递归实现
    public void inOrder_DP_Command(){ // 左子树-根节点-右子树
        ArrayList<Integer> res = inOrder_DP_Command(root);
        for(int i = 0; i < res.getSize(); i++)
            System.out.print(res.get(i) + " ");
    }

    private ArrayList<Integer> inOrder_DP_Command(Node node){
        ArrayList<Integer> res = new ArrayList<>();
        if(node == null)
            return res;
        Stack<Command> stack = new LinkedStack<>();
        stack.push(new Command("go", node));  // 压入Command,目的是访问根节点
        while(!stack.isEmpty()){
            Command command = stack.peek();
            stack.pop();
            if(command.s.equals("print"))
                res.addLast((Integer) command.node.e);
            else{
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right)); // 后访问，先入栈
                stack.push(new Command("print", command.node));
                if(command.node.left != null)
                    stack.push(new Command("go", command.node.left)); // 先访问，后入栈
            }
        }
        return res;
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

    public void postOrder_DP_Command(){
        ArrayList<Integer> res = postOrder_DP_Command(root);
        for(int i = 0; i < res.getSize(); i++)
            System.out.print(res.get(i) + " ");
    }

    private ArrayList<Integer> postOrder_DP_Command(Node node){
        ArrayList<Integer> res = new ArrayList<>();
        if(node == null)
            return res;
        Stack<Command> stack = new LinkedStack<>();
        stack.push(new Command("go", node));
        while(!stack.isEmpty()){
            Command command = stack.pop();
            if(command.s.equals("print")) // print-command, 当然也可以是其他什么的操作
                res.addLast((Integer) command.node.e);
            else{ // go-command
                // 后访问，先入栈 , 最后访问根节点，就最先压入print指令
                stack.push(new Command("print", command.node));
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right));
                if(command.node.left != null)
                    stack.push(new Command("go",command.node.left));
            }
        }
        return res;
    }


    //树的层序遍历， 使用queue辅助完成
    //step1：在循环之前入队root，循环条件while(!queue.isEmpty())
    //step2：node出队后，入队左右孩子
    //step3：左孩子出队后，入队左孩子的左右孩子，然后依次类推
    public void levelOrder(){
       Queue<Node> queue = new LoopQueue<>();
       queue.enQueue(root);
       while(!queue.isEmpty()){ //当queue非空时
           Node cur = queue.deQueue();
           System.out.print(cur.e + " ");
           if(cur.left != null)
               queue.enQueue(cur.left);
           if(cur.right != null)
               queue.enQueue(cur.right);
       }
    }


    //一直向左走，直到走不动了，就是最小值，不一定是叶子节点，有可能是根节点、或者中间节点。
    //寻找最小值
    public E findMin(){
        if(size == 0 || root == null)
            throw new IllegalArgumentException("BST is empty!");
        return findMin(root).e;
    }

    private Node findMin(Node node){
        if(node.left == null)
            return node;
        return findMin(node.left); //node左孩子不为空，则继续向左探索
    }

    //寻找最大值
    public E findMax(){
        if(size == 0 || root == null)
            throw new IllegalArgumentException("BST is empty!");
        return findMax(root).e;
    }

    private Node findMax(Node node){  // 找最大值，向右探
        if(node.right == null)
            return node;
        return findMax(node.right);
    }

    public E removeMin(){
        E ret = findMin();
        root = removeMin(root);  //树的root唯一指定一棵树
        return ret;
    }

    private Node removeMin(Node node){
        if(node.left == null){ //递归终止条件， 此时已经是最小元素
            Node rightNode = node.right; // 把删除节点的右子树赋值给rightNode
            node.right = null;
            size --;
            return rightNode;  //返回删除节点的右子树
        }
        node.left = removeMin(node.left);// 把右子树继续成为该删除节点的父亲节点的左子树
        return node;
    }

    public E removeMax(){
        E ret = findMax();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if(node.right == null){ // 递归终止条件，向右链探，已经是最大元素
            Node leftNode = node.left; // 该删除元素的左子树索引保存下来
            node.left = null; // 该删除元素的做子孩子域赋值为空null
            return leftNode; // 返回该删除元素的左子树索引
        }
        node.right = removeMax(node.right); // 把左子树继续成为该删除节点的父亲节点的右子树
        return node;
    }



    public static void main(String[] args){
        int[] nums = {3,4,5,1,2,0};
        BST<Integer> bst = new BST<>();
        for(int n: nums)
            bst.add(n);

//        bst.preOrderRecur();
//        System.out.println();
//        bst.preOrder_DP_Command();

          bst.inOrderRecur();
          System.out.println();

//        bst.inOrder_DP_Command();
//        bst.postOrderRecur();
//        System.out.println();
//        bst.postOrder_DP_Command();
//        bst.levelOrder();
//        Integer min = bst.findMin();
//        System.out.println(min);
//        Integer max = bst.findMax();
//        System.out.println(max);

          bst.removeMax();
          bst.inOrderRecur();
          System.out.println();
    }

}











