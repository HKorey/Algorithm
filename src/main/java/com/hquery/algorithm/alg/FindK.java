package com.hquery.algorithm.alg;

/**
 * 找到第K小的数（快排思想）
 *
 * 快排思想，选取序列的一个key进行划分，小于key的分在左边，大于key的在右边，（key的位置 - low + 1）就是最后一个元素是key的序列中元素的数量，
 * 当元素数量大于K时，就在左半部分递归找，等于时 arr[key]就是第K
 * 大的元素，小于K时，在右边递归找第 k - num 大的元素
 *
 * @author hquery.huang
 * 2018/12/12 16:41:59
 */
public class FindK {

    public static int partition(int[] a, int low, int high) {
        int val = a[low];
        while (low < high) {
            while (low < high && a[high] >= val)
                high--;
            a[low] = a[high];
            while (low < high && a[low] <= val)
                low++;
            a[high] = a[low];
        }
        a[low] = val;
        return low;
    }

    public static int quickSelect(int[] a, int low, int high, int k) {
        if (low == high)
            return a[low];
        int keyPos = partition(a, low, high);
        int num = keyPos - low + 1;
        if (num == k) {
            return a[keyPos];
        } else if (k < num) {
            return quickSelect(a, low, keyPos - 1, k);
        } else {
            return quickSelect(a, keyPos + 1, high, k - num);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 3, 7, 9, 32, 12, 77, 1, 52, 10};
        System.out.println(quickSelect(a, 0, a.length - 1, 10));
    }

}
