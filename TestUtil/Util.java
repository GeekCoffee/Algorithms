package com.geektech.Untils;

import com.geektech.linear_structure.queue.ArrayQueue;
import com.geektech.linear_structure.queue.LinkedQueue;
import com.geektech.linear_structure.queue.LoopQueue;
import com.geektech.linear_structure.queue.Queue;
import com.geektech.linear_structure.stack.ArrayStack;
import com.geektech.linear_structure.stack.LinkedStack;
import com.geektech.linear_structure.stack.Stack;

import java.util.Random;

/**
 *  工具类,  date = 4/26 2019 ,  author = chensheng
 *  功能：1）测试各类数据结构的具体运行时间，查看具体时间性能的差异
 */
public class Until {


    /**
     * 1） LoopQueue是O(n),ArrayQueue是O(n^2)，LinkedQueue当数据量大也是属于O(n)级别的，但是常系数比LoopQueue更低.
     * 2） 不管什么数据量，都推荐使用LinkedQueue
     * 3）千万级别数据量的时候，LinkedQueue比LoopQueue效果性能更优
     * @param q
     * @param dataSize
     * @return
     */
    public static double testQueue(Queue<Integer> q, int dataSize){
        long startTime = System.nanoTime(); //返回的是纳秒数
        Random random = new Random();
        for(int i = 0; i < dataSize; i ++)
            q.enQueue(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0; i < dataSize; i ++)
            q.deQueue();
        long endTime = System.nanoTime();
        return (endTime-startTime) / 1000000000.0;  //从纳秒转换为秒
    }


    /**
     * 千万级别的数据量推荐使用arrayStack, 千万级数据量以下的推荐使用LinkedStack
     * @param stack
     * @param dataSize
     * @return
     */
    public static double testStack(Stack<Integer> stack, int dataSize) {
        long startTime = System.nanoTime();
        Random random = new Random();
        try{
            for(int i = 0; i < dataSize; i ++)
                stack.push(random.nextInt(Integer.MAX_VALUE));
            for(int i = 0; i < dataSize; i ++)
                stack.pop();
        }catch (Exception e){
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }


    public static void main(String[] args) {

        //把注释取消，就可以使用了

        //test Stack的时间性能
//        int dataSize = 10000000;
//        LinkedStack<Integer> linkedStack = new LinkedStack<>();
//        double time1 = Tool.testStack(linkedStack, dataSize);
//        System.out.println("linkedStack , time: "+time1 + "秒");
//
//        ArrayStack<Integer> arrayStack = new ArrayStack<>();
//        double time2 = Tool.testStack(arrayStack, dataSize);
//        System.out.println("arrayStack , time: "+time2 + "秒");



        //test Queue的时间性能
//        int dataSize = 100000000;
//        LoopQueue<Integer> loopQueue = new LoopQueue<>();
//        double time1 = Tool.testQueue(loopQueue, dataSize);
//        System.out.println("LoopQueue , time: "+time1+" 秒");
//
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//        double time2 = Tool.testQueue(arrayQueue, dataSize);
//        System.out.println("arrayQueue , time: "+time2+" 秒");
//
//        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
//        double time3 = Tool.testQueue(linkedQueue, dataSize);
//        System.out.println("linkedQueue , time: "+time3+" 秒");

    }
}











