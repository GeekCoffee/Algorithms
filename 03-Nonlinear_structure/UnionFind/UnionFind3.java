package com.geektech.Nonlinear_structure.UnionFind;

/**
 *  数组实现的并查集-版本3的UnionFind， date = 28/5 2019 ,  author = chensheng
 *  是在版本2UnionFind的基础上，优化了Union时候的操作，优化点是合并之前判断哪个树更小，把更小的树指向更大的树的root
 *  => 这样的并查集所表示的树就可以避免退化成为单链表
 *  => 避免让树变高变深，时间复杂度会受到影响
 *  => 时间复杂度在常系数意义上得到了优化
 *  ADT-基本：getSize
 *      辅助：find(i) => O(h), h为树高, 严格意义是：O(log*N) = 1+log(logN) (当N>1时，是一个递归的定义)
 *                                    => O(log*N)的性能逼近与O(1)复杂度
 *      并操作：Union(p, q) => O(h), 合并两个集合
 *      查询两点是否相连操作：isConnected(p, q) => O(h), 查询两个节点之间是否是同一个集合
 **/
public class UnionFind3 implements UnionFind{

    private int[] parent;  //i节点,parent[i]的值是i节点的父亲节点
    private int[] sz;  //表示以节点i为根节点的树一共有多少个节点


    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        for(int i = 0; i < size; i ++) {
            parent[i] = i;
            sz[i] = 1;  //初始化时，每个树都是一个节点，形成了一个森林，即1个节点
        }

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
    // 优化点：合并之前判断一个哪个树更小，把小的树指向更大的树上去，避免让树变高变深，时间复杂度会受到影响。
    @Override
    public void unionElem(int p, int q) {
        // step1: 找到p所在的Root节点和q所在的Root节点
        int pRoot = find(p);
        int qRoot = find(q);

        //step2：判断一个p和q是否是同一集合中
        if (pRoot == qRoot)
            return;

        //step3：合并之前判断一下树的节点数
        if (sz[pRoot] < sz[qRoot]) {  //当pRoot比qRoot小时，把pRoot指向qRoot
            parent[pRoot] = qRoot;

            // 这个操作很重要，不要加反了，合并完之后，检查sz[root]就能知道以root为根节点的树的大小了
            sz[qRoot] += sz[pRoot];

        } else {  //当pRoot数 >= qRoot数时，让qRoot指向pRoot
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    } //end method.

}
