package com.geektech.Nonlinear_structure.UnionFind;

/**
 * 数组实现的并查集-版本5的UnionFind， date = 29/5 2019 ,  author = chensheng
 *  是在版本4-UnionFind的基础上，优化了Find时候的操作
 *  => 优化点：在Find方法中，添加了路径压缩子算法，寻找目标节点的同时，把树高给压缩更低
 *  => 时间复杂度在常系数意义上得到了进一步的优化
 *  ADT-基本：getSize
 *      辅助：find(i) => O(h), h为树高, 严格意义是：O(log*N) = 1+log(logN) (当N>1时，是一个递归的定义)
 *                                    => O(log*N)的性能逼近与O(1)复杂度
 *      并操作：Union(p, q) => O(h), 合并两个集合
 *      查询两点是否相连操作：isConnected(p, q) => O(h), 查询两个节点之间是否是同一个集合
 **/
public class UnionFind5_PathCompress implements UnionFind{

    private int[] parent;  //i节点,parent[i]的值是i节点的父亲节点
    private int[] rank;  //表示以节点i为根节点的树的层数表示为rank


    public UnionFind5_PathCompress(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i ++) {
            parent[i] = i;
            rank[i] = 1;  //初始化时，每个树都是一个节点，形成了一个森林，一个节点，树层数就是1
        }

    }

    //查找节点i所在的树中的最根节点
    private int find(int i){
        //异常检测
        if( i < 0 || i >= parent.length)
            throw new IllegalArgumentException("索引i非法");

        while(i != parent[i]){   //当i == parent[i]的时候，就是最根节点是i
            parent[i] = parent[parent[i]];  //让i节点指向i的祖父节点，即跨一个节点指到祖父节点的位置
            i = parent[i];  //让i一直往二叉树的根节点方向上走
        }
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

        //step3：合并之前判断一下哪个树的层数更低
        // 把更低的树合并到更高的树中,降低了树高
        if (rank[pRoot] < rank[qRoot]) {  //当pRoot比qRoot的树层数更小时，把pRoot的树合并到qRoot上
            parent[pRoot] = qRoot;
            // 因为pRoot-tree是小于qRoot-tree的层数的，所以合并后以qRoot的树高为标准，rank[qRoot]不变
        } else if(rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }else{  //当qRoot树和pRoot树层数一样，或者说是一样高的话，对谁合并都可以，但是要记得层数加1
            parent[qRoot] = pRoot;  //把qRoot树合并到pRoot树
            rank[pRoot] += 1; //所以pRoot树高要加1
        }

    }

}
