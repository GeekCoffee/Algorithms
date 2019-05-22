package com.geektech.linear_structure.queue;

import com.geektech.Semilinear_structure.MinHeap;

/**
 * 优先队列底层实现为MinHeap,  date = 4/26 2019 ,  author = chensheng
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
 *     最小二叉堆    O(logn)     O(logn)
 *
 */

public class PriorityQueue_ByMinHeap<E extends Comparable<E>> implements Queue<E>{

    //用最大二叉堆来实现优先队列
    private MinHeap<E> minHeap;

    public PriorityQueue_ByMinHeap(){
        minHeap = new MinHeap<>();
    }

    //使用heapify建堆操作
    public PriorityQueue_ByMinHeap(E arr[]){
        minHeap = new MinHeap<>(arr);
    }

    @Override
    public int getSize() {
        return minHeap.getSize();
    }

    @Override
    public void enQueue(E e) {
        minHeap.add(e);
    }

    @Override
    public E deQueue() {
        return minHeap.extractMin();
    }

    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return minHeap.findMin();
    }


}










