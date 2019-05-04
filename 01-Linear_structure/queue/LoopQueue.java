package com.geektech.linear_structure.queue;

/**
 *  LoopQueue,  date = 4/26 2019 ,  author = chensheng
 *  ADT: 构造器：constructor2个  => O(1)
 *        基本：getSize, isEmpty => O(1)
 *        入队：enQueue(e) => 渐进复杂度最坏情况O(n)-resize,最好情况O(1); 均摊复杂度O(1)
 *        出队：deQueue() => 均摊O(1)
 *        取出队列的队首元素：getFront() => O(1)
 *
 *  实现思想：1）维护一个静态数组，不用vector。
 *          2）设置front和tail指针。
 *          3）队空条件：front == tail ,队满                                   条件： (tail+1)%length == front
 *          4）有意识地牺牲一个空间，去换取时间性能上的提高
 *          5）front总是指向对头元素，tail指向最后一个元素的后一个位置
 *          6）data.length、capacity、size三个概念要分清楚
 *
 *  取余知识：1） 8%12 = 8 ，13%12 = 1
 *          2）用mod的方式避免了front和tail的越界情况
 *          3）front++ => (front+1)%data.lentgh，tail同理
 *
 *  队列的应用：CPU的任务队列、网络数据包队列等
 *
 */

public class LoopQueue<E> implements Queue<E>{
    private E[] data;
    private int front;
    private int tail;
    private int size;

    private void resize(int capacity){
        E[] newData = (E[])new Object[capacity+1];
        for(int i = 0; i < getSize(); i++, front = (front+1)%data.length){
            newData[i] = data[front];
        }
        data = newData;
        front = 0;
        tail = size; //tail指向的是最后一个元素的后一个位置
    }

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10); //调用有参构造器
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return this.size;
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public void enQueue(E e){
        //情况1：
        if((tail+1)%data.length == front) //队满情况
            resize(getCapacity() * 2); //扩容

        //情况3：tail还在front后面，或者tail在front前面都成立
        data[tail] = e;
        tail = (tail+1) % data.length; // tail++
        size ++;
    }

    @Override
    public E deQueue(){
        if(isEmpty())
            throw new IllegalArgumentException("loopQueue is empty...");
        if(size == data.length >>> 2 && data.length >>> 1 != 0){
            this.resize(data.length >>> 1);  // 缩小为自身的0.5倍
        }
        E ret = data[front];
        front = (front+1)%data.length;  // front++
        size --;
        return ret;
    }

    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("loopQueue is empty...");
        return data[front];
    }


    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("loopQueue: size = %d, capacity = %d \n",getSize(),getCapacity()));
        str.append("front [");
        for(int i = front; i != tail; i = (i+1)%data.length){
            str.append(data[i]);
            if((i+1)%data.length != tail)
                str.append(",");
        }
        str.append("] tail");
        return str.toString();
    }


    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for(int i = 0; i < 30; i ++){
            loopQueue.enQueue(i);
            System.out.println(loopQueue.toString());
//            if(i % 3 == 2)
//                loopQueue.deQueue();
        }
    }
}
