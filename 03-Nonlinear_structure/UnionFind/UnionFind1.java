package com.geektech.Nonlinear_structure.UnionFind;

/**
 *  数组实现的并查集-版本1的UnionFind， date = 24/5 2019 ,  author = chensheng
 *  ADT-基本：getSize
 *      并操作：Union(p,q) => O(N)
 *      查询两点是否相连操作：isConnected(p,q)  => O(1)
 **/

public class UnionFind1 implements UnionFind{

    private int[] data;

    public UnionFind1(int size){
        data = new int[size];
        for(int i = 0; i < size; i++){ //初始化集合
            data[i] = i;
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }

    //查找元素p所在的集合的序号
    private int find(int p){
        if(p < 0 && p >= data.length)
            throw new IllegalArgumentException("p的索引非法");
        return data[p];
    }

    //若两个元素在同一个集合中，则返回true，否则false
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并p所在的集合和q所在的集合
    @Override
    public void unionElem(int p, int q) {
        int pId = find(p);  //查找p所在的集合序号
        int qId = find(q); //查找q所在的集合序号

        //若两个点在同一个集合中
        if(pId == qId)
            return;

        //不在同一个集合中，则合并两个集合，以pId所在的集合为例子
        for(int i = 0; i < data.length; i++)
            if(pId == data[i])  //若pId == data[i]，则改变data[i]的索引为qId，即达到两集合合并的目的
                data[i] = qId;
    }
}
