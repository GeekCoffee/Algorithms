package com.geektech.Nonlinear_structure.UnionFind;

/**
 *  UnionFind并查集，可以高效解决一个图中两个点是否相连的问题， date = 24/5 2019 ,  author = chensheng
 *  ADT-基本：getSize
 *      并操作：Union(p,q)  //合并两个集合成一个集合，数学上集合的并操作
 *      查询两点是否相连操作：isConnected(p,q)
 **/


public interface UnionFind {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElem(int p, int q);

}
