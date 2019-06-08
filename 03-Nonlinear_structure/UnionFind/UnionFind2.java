package com.geektech.Nonlinear_structure.UnionFind;

/**
 *  数组实现的并查集-版本2的UnionFind， date = 28/5 2019 ,  author = chensheng
 *  逻辑结构上是一棵孩子节点指向父亲节点的树Tree，不一定是二叉树，实现还是用数组实现的，跟二叉堆、线段树一样都是用数组实现的
 *  ADT-基本：getSize
 *      辅助：find(i) => O(h), h为树高, 严格意义是：O(log*N) = 1+log(logN) (当N>1时，是一个递归的定义)
 *                                    => O(log*N)的性能逼近与O(1)复杂度
 *      并操作：Union(p, q) => O(h), 合并两个集合
 *      查询两点是否相连操作：isConnected(p, q) => O(h), 查询两个节点之间是否是同一个集合
 **/
public class UnionFind2 implements UnionFind{

    private int[] parent;  //i节点,parent[i]的值是i节点的父亲节点


    public UnionFind2(int size){
        parent = new int[size];

        for(int i = 0; i < size; i ++)
            parent[i] = i;   //初始化parent数组，一开始每个节点都是独立的，即是一个森林
    }

    //查找节点i所在的树中的最根节点
    private int find(int i){
        //异常检测
        if( i < 0 || i >= parent.length)
            throw new IllegalArgumentException("索引i非法");

        while(i != parent[i])  //当i == parent[i]的时候，就是最根节点是i
            i = parent[i];  //让i一直往二叉树的根节点方向上走
        return i;
    }

    // 返回树的所有节点，即返回集合中的元素个数
    @Override
    public int getSize() {
        return parent.length;
    }

    // 查询p和q节点是否在同一个二叉树中，或者说是两个节点是否在同一个集合中
    // 用于解决两点路径是否相连通的问题
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);  //返回pRoot和qRoot比较一下结果就知道是否是在同一个集合中了
    }

    // 合并p所在的集合和q所在的集合， 逻辑上是合并p所在的树和q所在的树
    // 具体是让pRoot指向qRoot，即让parent[pRoot] = qRoot
    @Override
    public void unionElem(int p, int q) {
        // step1: 找到p所在的Root节点和q所在的Root节点
        int pRoot = find(p);
        int qRoot = find(q);

        //step2：判断一个p和q是否是同一集合中
        if(pRoot == qRoot)
            return;

        //step3：p和q节点不在同一个集合中，开合并两棵树；让pRoot指向qRoot，那就是让parent[pRoot]的值改变
        parent[pRoot] = qRoot;
    }
}
