package com.hquery.algorithm.alg;

/**
 * @author hquery.huang
 * 2018/5/19 15:20
 */
public class QuickSort {

    public static int getMid(int[] a, int low, int high) {
        int val = a[low];
        while (low < high) {
            while (low < high && a[high] >= val)
                high--;
            a[low] = a[high];
            while (low < high && a[low] <= val)
                low++;
            a[high] = a[low];
        }
        // 此时 low == high
        a[low] = val;
        return low;
    }

    public static void quickSort(int[] a, int low, int high) {
        // 最终low = high 递归终止
        if (low == high)
            return;
        int mid = getMid(a, low, high);
        quickSort(a, 0, mid);
        quickSort(a, mid + 1, high);
    }

    public static void main(String[] args) {
        int[] a = new int[]{32, 4, 55, 10, 5};
        quickSort(a, 0, a.length - 1);
        for (int a1 : a)
            System.out.print(a1 + " ");
    }

}