package com.geektech.linear_structure.queue;

import com.geektech.Semilinear_structure.MaxHeap;

/**
 * 优先队列底层实现为MaxHeap,  date = 4/26 2019 ,  author = chensheng
 * java.util.PriorityQueue内部是最小堆
 *  ADT: 构造器：constructor2个  => O(1)
 *        基本：getSize, isEmpty => O(1)
 *        入队：enQueue(e) => 渐进复杂度最坏情况 O(树高h), 即O(logn)
 *        出队：deQueue() => O(树高h),即O(logn)
 *        取出队列的队首元素：getFront() => O(1)
 *
 *
 *      问题：为什么不用线性数组直接做优先队列的底层实现？
 *                   入队          出队
 *      线性数组      O(1)        O(n)，需要扫描数组
 *     最大二叉堆    O(logn)     O(logn)
 *
 */

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{

    //用最大二叉堆来实现优先队列
    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    //使用heapify建堆操作
    public PriorityQueue(E arr[]){
        maxHeap = new MaxHeap<>(arr);
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public void enQueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E deQueue() {
        return maxHeap.extractMax();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }


}










