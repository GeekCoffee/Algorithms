<<<<<<< HEAD:TestUtil/Until.java
package com.geektech.Tools;
=======
package com.geektech.Utils;
>>>>>>> 8bcd01e1f1717c850db52709e40ae718f83a4725:TestUtil/Util.java

import com.geektech.Nonlinear_structure.UnionFind.*;
import com.geektech.Semilinear_structure.MaxHeap;
import com.geektech.linear_structure.queue.Queue;
import com.geektech.linear_structure.stack.Stack;

import java.util.Random;

/**
 *  工具类,  date = 4/26 2019 ,  author = chensheng
 *  功能：1）测试各类数据结构的具体运行时间，查看具体时间性能的差异
 */
<<<<<<< HEAD:TestUtil/Until.java
public class Tool {
=======
public class Util {
>>>>>>> 8bcd01e1f1717c850db52709e40ae718f83a4725:TestUtil/Util.java


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
        return (endTime-startTime) / 1000000000.0;
    }

    /**
     * 测试普通建堆和heapify算法建堆的时间性能
     * @param arr
     * @param isHeapify
     * @return
     */
    public static double testMaxHeap(Integer[] arr, boolean isHeapify){
        MaxHeap<Integer> maxHeap;
        long startTime = System.nanoTime();
        if(isHeapify)
            maxHeap = new MaxHeap<Integer>(arr);  //heapify建堆
        else{  //必须要有else，不能同时建2个堆
            maxHeap = new MaxHeap<>(); //普通建堆
            for(int i = 0; i < arr.length; i++)
                maxHeap.add(arr[i]); //从数组的第一个元素开始，从零从空建堆
        }

        int[] newArray = new int[arr.length];
        for(int i = 0; i < newArray.length; i++) //建完堆后，两者再进行简单的堆排序
            newArray[i] = maxHeap.extractMax();

        //检测排序是否正确，从大到小的堆排序
        for(int i = 1; i < newArray.length; i++)
            if(newArray[i-1] < newArray[i])
                throw new IllegalArgumentException(isHeapify?"heapify建堆的排序顺序性有错误！":"普通建堆的排序顺序性有错误！");
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    /**
     * 测试不同版本的并查集性能
     * @param uf
     * @param ops 操作次数
     * @return
     */
    public static double testUnionFind(UnionFind uf, int ops){

        int size = uf.getSize();

        Random random = new Random();
        long startTime = System.nanoTime();

        //先做ops次的合并操作
        for(int i = 0 ; i < ops; i ++){
            int a = random.nextInt(size);  //在集合中随机一个数，[0,size)前闭后开
            int b = random.nextInt(size);
            uf.unionElem(a, b);
        }

        //再做ops次的查询两点之间是否相连操作
        for(int i = 0; i < ops; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }


        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;  //把ns转换为s

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

        //test MaxHeap不同建堆算法的时间性能
//        int dataSize = 1000000;  //100万数据集
//        Integer[] testArray = new Integer[dataSize];
//        Random random = new Random();
//        for(int i = 0; i < testArray.length; i++){
//            testArray[i] = random.nextInt(Integer.MAX_VALUE); // 随机范围[0,MAX_VALUE]
//        }
//        double heapifyTime = testMaxHeap(testArray, true);
//        System.out.println("数据集：" + dataSize + " , heapify建堆：" + heapifyTime + " 秒");
//
//        double maxHeapTime = testMaxHeap(testArray, false);
//        System.out.println("数据集：" + dataSize + " , 普通建堆：" + maxHeapTime + " 秒");


        //test UnionFind不同版本下的时间性能
        int dataSize = 10000000;
        int ops = 10000000;    //操作次数
//        UnionFind uf1 = new UnionFind1(dataSize);
//        double time1 = testUnionFind(uf1, ops);
//        System.out.println("并查集UnionFind1版本，总时间：" + time1 + " s");
//
//        UnionFind uf2 = new UnionFind2(dataSize);
//        double time2 = testUnionFind(uf2, ops);
//        System.out.println("并查集UnionFind2版本，总时间：" + time2 + " s");

        UnionFind uf3 = new UnionFind3(dataSize);
        double time3 = testUnionFind(uf3, ops);
        System.out.println("并查集UnionFind3版本，总时间：" + time3 + " s");

        UnionFind uf4 = new UnionFind4(dataSize);
        double time4 = testUnionFind(uf4, ops);
        System.out.println("并查集UnionFind4版本，总时间：" + time4 + " s");

        UnionFind uf5Pc = new UnionFind5_PathCompress(dataSize);
        double time5 = testUnionFind(uf5Pc, ops);
        System.out.println("并查集UnionFind5版本，总时间：" + time5 + " s");

        UnionFind uf6PcRecur = new UnionFind6_PathCompression_Recur(dataSize);
        double time6 = testUnionFind(uf6PcRecur, ops);
        System.out.println("并查集UnionFind6版本，总时间：" + time6 + " s");


    }
}











