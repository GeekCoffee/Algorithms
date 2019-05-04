package com.geektech.linear_structure.stack;

/**
 * Created by chensheng on 2019/4/26.
 */

public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e) throws Exception;
    E pop() throws Exception;
    E peek() throws Exception;

    //TODO ... getMin()
}
