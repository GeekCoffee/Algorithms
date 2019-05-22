package com.geektech.Semilinear_structure;

import com.geektech.linear_structure.base.ArrayList;

import java.util.Random;

/**
 *  以完全二叉树表示的一维数组实现的最大二叉堆,  date = 16/5 2019 ,  author = chensheng
 *  最大二叉堆的定义：任何局部的子二叉树(3个节点)都满足根节点最大，左右孩子节点都不比根节点大
 *  ADT: 构造器：constructor3个，其中包含1个heapify()构造 => O(n)
 *        基本：getSize(), isEmpty() => O(1)
 *        辅助：parent(i), leftChird(i), rightChird(i) => O(1), 获取节点i的父节点和子节点位置
 *              上溢: shiftUp(lastIndex) ,下溢: shiftDown(0)
 *        增加：add(E e) => O(树高h), 即O(logn)；若添加一个数组长度的元素，需要O(nlogn)的时间复杂度
 *        查询：findMax() => O(1)
 *        删除：extractMax() => O(树高h),即O(logn)
 *        替换：replace(e) => O(logn)
 *
 *
 */
public class MaxHeap<E extends Comparable<E>> { //继承Comparable类，让E类型的对象增加可比较性

    //引入自制的动态数组,来表示完全二叉树
    private ArrayList<E> data;

    public MaxHeap(int capacity){ //开辟空间，但是还没有使用
        data = new ArrayList<>(capacity);
    }

    public MaxHeap(){
        data = new ArrayList<>();
    }

    //使用heapify建堆算法，即把一个无序数组转换为二叉堆所表示的数组
    //核心：直接把当前数组看做一个未满足特性的完全二叉树，从最后一个非叶子节点的元素开始向index=0，做下溢shiftDown操作
    //这样做就可以一开始减少一半左右的节点
    public MaxHeap(E[] arr){
        data = new ArrayList<>(arr);
        for(int i = parent(arr.length-1); i >= 0; i--) //i == 0 的时候，也要做一次下溢检查shifDown
            shiftDown(i);
    }


    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }


    //完全二叉树用一维数组来表示，获取节点i的父亲节点
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("0索引没有父节点，是最高的根节点");
        return (index-1)/2;  //1-1/2 = 0,即1节点的父节点就是0节点
    }

    //获取节点i的左孩子的节点位置
    private int leftChild(int index){
        return index*2 + 1;
    }


    //获取节点i的右孩子节点位置
    private int rightChild(int index){
        return index*2 + 2;
    }

    //向最大堆中添加元素
    public void add(E e){
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

    //逻辑上是完全二叉树的上溢过程，物理上是一维数组的不断swap
    private void shiftUp(int i){
        while( i > 0 && data.get(parent(i)).compareTo(data.get(i)) < 0 ){ //当父节点小于当前操作节点时，循环继续
            data.swap(i,parent(i));
            i = parent(i); //让i指向parent节点，继续检查parent的父节点
        }
    }


    //得到最大堆中的最大值
    public E findMax(){
        if(data.isEmpty())
            throw new IllegalArgumentException("最大堆为空null");
        return data.get(0);
    }

    public E extractMax(){
        E ret = findMax();

        //真正在完全二叉树中删除最大值节点，即在一维数组中删除最大值节点并保持二叉堆性质
        data.swap(0, data.getSize() - 1); //把最后一个节点与最大直接交换位置
        data.removeLast();  //在数组中删除换位后的最大值元素，维护好size

        //下溢操作，以保持完全二叉堆的性质
        shiftDown(0);
        return ret;
    }


    //下溢，k从0开始变化
    private void shiftDown(int k){

        //最极端情况就是k从0一直下溢到最后一个叶子节点上, 最后一个叶子节点的左孩子索引k*2+2肯定是大于size的
        while(leftChild(k) < data.getSize()){
            int biggish = leftChild(k); //首先假设左孩子比右孩子大, biggish指向左孩子

            //当k的右孩子rigthChild的索引小于size的时候，说明k有右孩子，自己画图比较一下就知道了
            if(rightChild(k) < data.getSize() &&
                    data.get(leftChild(k)).compareTo(data.get(rightChild(k))) < 0)  //当k有右孩子且右孩子比左孩子大，与把biggish指向右孩子
                biggish = rightChild(k);

            //当父节点的值比孩子节点的值更大或者等于时，已经保持二叉堆的特性
            if(data.get(k).compareTo(data.get(biggish)) >= 0)
                break;
            data.swap(k, biggish); //否则，父节点k与自己孩子节点交换值
            k = biggish; //把k指向交换的孩子节点biggish索引指向的孩子节点上
        }
    }

//    private int returnBiggishIndex(int k){
//        return data.get(leftChild(k)).compareTo(data.get(rightChild(k))) > 0?leftChild(k):rightChild(k);
//    }

    //取出堆中最大的元素，且替换为元素e
    public E replace(E e){
        E ret = findMax();
        data.set(0,e); //先把最大元素，也就是根节点的元素替换成元素e
        //然后做一下，下溢操作shiftDown
        shiftDown(0);
        return ret;

    }



    public static void main(String[] args){
        int n = 10;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(200));   //几乎无序的随机整数，使用4Byte的MAX_VALUES


        //简单二叉堆排序，每次都从堆里取出最大的元素，逆序排列
        int[] arr = new int[n];

        long startTime = System.nanoTime();
        for(int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();
        long endTime = System.nanoTime();

        double totalTime =  (endTime-startTime) / 1000000000.0;

        //检查堆排序的正确性
        for(int i = 1; i < n; i++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("堆排序后的结果，不正确！请检查代码逻辑");

        System.out.println("简单堆排序完成， 数据量："+n + "   用时："+ totalTime + " 秒");

        //打印一下排序后的数组
        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();




    }

}




