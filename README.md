# Review data-structures and algorithms

*  Linear structures: Vector,ArrayList , Stack , Queue , LinkedList etc.
*  Semilinear structures: Binary search tree(BST), Spaly, B-tree, Hash-tree, Red-black tree, Trie, Binary heap(Qriority queue)
*  Nonlinear structures: Simple-graph, Hashmap
*  basic sort algorithms: Bubble-sort, Insertion-sort, Selection-sort,  etc.
*  basic search algorithms:binary search  etc.
*  some commonly tools for designing algorithms: Recursion and Dynamic programming, greedy method, backtracking etc.

** ArrayList-ADT:
 *  ADT: 构造器：constructor2个  => O(c)
 *        基本：getSize, getCapacity, isEmpty => O(1)
 *        增加：add(index,e), addFirst(e), addLast(e) => O(n)
 *        替换：set(index,e),setFirst(e), setLast(e) => O(1)
 *        得到：get(index),getFirst(),getLast()  => 已知索引：O(1)
 *        查找：find(e)=>index, contains(e)=>boolean => 未知索引：O(n)
 *        删除：remove(index)=>elem, removeFirst(), removeLast, removeElem(e)  => O(n)
 *        扩容：当size==capacity时，可以扩容2倍或者1.5倍都可以,java默认的是1.5倍  => O(n)
 *        缩容：装填因子=size/capacity <= 0.25时，使用缩容   => O(n)
 *        @Overrive toString=>重写Object类的toString方法  => O(n)
 *        TODO:  排序功能、 有序去重功能、 无序去重功能

