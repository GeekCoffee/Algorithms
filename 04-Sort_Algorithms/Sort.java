package com.geektech.Sort_Algorithms;

/**
 * O(n²)渐进复杂度算法： bubbleSort、selectSort、inserionSort
 * O(nlgn)渐进复杂度算法：twoWayQuickSort、ThreeWayQuickSort[适用于重复元素较多的场景]、mergeSort、ShellSort
 */
public class Sort<E> {


    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    //冒泡排序
    //关键：给临近较大前者元素换位置，每一次换完，最大元素归位最后一位，有效问题规模从右开始缩减
    public void bubbleSort(int[] arr){
        int n = arr.length;
        for(int j = 0; j < n; j++){ //需要n大次趟，每一趟完成，有效问题规模减1，最大元素归位

            //每for循环完成一次，最大元素归位，有效问题规模减1
            for(int i = 1; i < n-j; i++){
                if(arr[i] < arr[i - 1]) { //若前者比后者大，则一次swap
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            }

        }
    }

    //选择排序
    //关键：扫描全部元素找出最小值，与有效问题第一个元素swap
    public void selectSort(int[] arr){
        int n = arr.length;
        for(int i = 0,j; i < n; i++){
             int min = i;

             //step1:findMin()算法, 寻找[i,n)的最小值，i是会变的，n是不会变的，所以有效问题规模从左边开始缩减的
            for(j = i; j < n-i; j++){
                if(arr[j] < arr[min]) //当max指向的元素比j指向的元素大的时候，更新max的值
                    min = j;
            }

            //step2:交换元素位置,有效问题规模是[i,n),i从0开始，到n-1结束
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    //java是从堆开辟空间，有GC垃圾自动回收装置
    public static void main(String[] args){
        Sort<Integer> sortHandle = new Sort<>();
        int[] arr ={2,3,1,4,5,6};
        printArray(arr);
//        sortHandle.selectSort(arr);
//        sortHandle.printArray(arr);
        sortHandle.bubbleSort(arr);
        printArray(arr);
    }
}
