package com.geektech.linear_structure.base;
/**
 *  underlying implementation of ArrayList,  date = 4/19 2019 ,  author = chensheng
 *  ArrayList 就是 ArrayList
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
 *
 *        TODO:  排序功能、 有序去重功能、 无序去重功能
 *
 *  泛型知识复习： 1）泛型只支持类和对象，不支持基本数据类型，所以java开发了包装类来对基本数据类型的转化
 *              2）使用方法：Array<Integer> arr = new Array<Integer>(capacity)
 *              3）创建方法：data = (E[])new Object[capacity]
 *              4）==是引用比较，两个对象之间用equals，是值比较
 *
 *  渐进时间复杂度： 1）O(n)中的n，指的是当n趋向于∞，所以常系数在这个情况下是没有意义的
 *                2）当n数据规模不大的时候，常系数是有意思的，可以利用常系数带来的性能优化，就比如n=20这个数据规模，
 *                 insertion_sort就比selection_sort快
 *                3）渐进复杂度一般都考虑是最坏情况
 *
 *  均摊时间复杂度：1）均摊计算，一个比较耗时的操作，只要能保证每次执行其他操作不触发它的话，均摊时间复杂度是有意义的。
 *               2）addLast和removeLast操作的均摊复杂度都是O(1)，即平均，每次addLast操作，就会进行2次基本操作。
 *               如：capacity=8，则addLast需要做9次添加操作，此时resize，8次赋值操作
 *               总基本操作=9+8=17次，则平均下来17/9=2次基本操作
 *               推广一下：capacity=n,addLast需要n+1次添加操作，n次赋值操作
 *               总基本操作=n+1+n=2n+1次，则平均下来2n+1/n=2次基本操作
 *               总结：addLast的均摊时间复杂度与n的取值无关，所以为O(1)；removeLast一样分析。
 *               3）有时候，均摊计算比计算最坏情况有意义，以上addLast例子为例，并不是每次addLast都会触发resize的。
 *               4）什么时候用均摊复杂度呢？不是每次都会触发耗时操作的操作的时候，以addLast为例。
 *
 * 复杂度震荡： 1）当设置size==data.length/2时，先addLast然后removeLast，就会发生2次resize内部操作，
 *              这时就是发生了一次复杂度震荡的现象。
 *           2）解决方案：lazy ， 把装填因子设置为0.25，即size == data.length/4的情况下，才发生缩容操作。
 *
 * java知识： 1）>>表示有符号位移， >>>表示无符号位移
 *           2）8>>>1(8/2) = 4, 8>>>2(8/4) = 2 , 8>>>3(8/8) = 1,
 */

public class ArrayList<E> {

    private E[] data;  //底层维护一个数组
    private int size;  //实际容量, 利用 capacity = data.length功能，让java帮维护一个capacity数组
    // 静止时，size总指向空闲位置

    /**
        * 动态伸缩静态数组，属于类内部操作，不需要给外部提供API接口
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;  //data转移指向, 完成伸缩
    }

    /**
     * constructor-1
     * @param capacity
     */
    public ArrayList(int capacity){
        data = (E[])new Object[capacity];  //强制类型转换
        this.size = 0;
    }

    /**
     * 无参数constructor, 默认有10个元素容量
     */
    public ArrayList(){
        this(10);
        this.size = 0;
    }

    public int getSize(){ return this.size; }

    public int getCapacity(){ return this.data.length; }

    /**
     * 判断vector是否为空
     * @return
     */
    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * 向vector指定位置插入元素e
     * @param index
     * @param e
     */
    public void add(int index, E e){
        //step1: 异常处理
        if(index > this.size || index < 0){
            throw new IllegalArgumentException("插入位置不合法.... ");
        }

        if(this.size == this.data.length){
           this.resize(2*data.length);  //java是1.5倍，其实都可以
        }

        //step2：集体向后移动元素，腾出index指向的位置，并在index位置插入e
        for(int i = size-1; i >= index; i--)
            data[i+1] = data[i];
        data[index] = e;
        this.size++;
    }

    /**
     * add的特殊情况
     * @param e
     */
    public void addFirst(E e) {
        this.add(0,e);
    }

    public void addLast(E e) {
        this.add(size,e); // 可以直接在size位置插入
    }

    /**
     * 在index位置替换元素
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if(index < 0 || index >= this.size){ // 注意边界处理，有等号=
            throw new IllegalArgumentException("index位置不合法");
        }
        data[index] = e;
    }

    public void setFirst(E e) {
        this.set(0,e);
    }

    public void setLast(E e) {
        this.set(size-1,e);
    }

    /**
     * 通过index获得元素e
     * @param index
     * @return
     */
    public E get(int index) {
        //step1: 异常检测
        if(index < 0 || index >= this.size){ // 注意边界处理，有等号=
            throw new IllegalArgumentException("index位置不合法");
        }
        return data[index];
    }

    /**
     * 得到数组的第一个元素
     * @return
     * @throws Exception
     */
    public E getFirst() {
        return get(0);
    }


    /**
     * 得到数组最后的一个元素
     * @return
     * @throws Exception
     */
    public E getLast() {
        return get(this.getSize()-1);
    }

    /**
     * 在vector中查找e，并返回index
     * @param e
     * @return
     */
    public int find(E e){
        for(int i = size-1; i >= 0; i--)
            if(data[i].equals(e))
                return i;
        return -1; //查找失败
    }


    /**
     * 判断vector中是否存在e，并返回Boolean
     * @param e
     * @return
     */
    public boolean contains(E e){
        for(int i = size-1; i >= 0; i--)
            if(data[i].equals(e))
                return true;
        return false; //查找失败
    }


    /**
     * 从vector中删除指定index的元素，并返回被删除元素
     * @param index
     * @return
     */
    public E remove(int index) {
        //step1：异常检测
        if(index < 0 || index >= this.size){
            throw new IllegalArgumentException("index输入非法");
        }
        //当装填因子小于等于0.25时，进行缩容； length/2 == 0时，就是length<2时才发生的
        if(size == data.length >>> 2 && data.length >>> 1 != 0){
            this.resize(data.length >>> 1);  // 缩小为自身的0.5倍
        }

        //step2:备份
        E temp = data[index];

        //step3：逻辑处理，向前覆盖,size-1
        for(int i = index+1; i <= size-1; i++)
            data[i-1] = data[i];
        this.size--;
        data[size] = null;  //java垃圾回收机制
        return temp;
    }

    /**
     * 删除vector中有e的元素，没有e就return
     * @param e
     */
    public void removeElem(E e) {
        this.remove(this.find(e));
    }

    /**
     * 删除vector头元素
     * @return
     * @throws Exception
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除vector尾元素
     * @return
     * @throws Exception
     */
    public E removeLast() {
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        str.append(String.format("ArrayList: size=%d , capactiy=%d \n",this.size,this.data.length));
        str.append("[");
        for(int i=0; i < size; i++){
            str.append(data[i]);
            if(i != size-1)
                str.append(",");
    }
        str.append("]\n");
        return str.toString();
    }

}




















