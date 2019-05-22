package com.geektech.linear_structure.queue;

/**
 *  Queue,  date = 4/26 2019 ,  author = chensheng
 *  ADT: 构造器：constructor2个
 *        基本：getSize, isEmpty
 *        入队：enQueue(e)
 *        出队：deQueue()
 *        取出队列的队首元素：getFront()
 */
public interface Queue<E> {

    int getSize();
    void enQueue(E e);
    E deQueue(); // 出队
    boolean isEmpty();
    E getFront();

}
