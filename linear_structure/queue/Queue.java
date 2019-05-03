package com.geektech.linear_structure.queue;

/**
 * Created by chensheng on 2019/4/26.
 */
public interface Queue<E> {

    int getSize();
    void enQueue(E e);
    E deQueue(); // 出队
    boolean isEmpty();
    E getFront();

}
