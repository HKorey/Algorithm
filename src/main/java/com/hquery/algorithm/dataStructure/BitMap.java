package com.hquery.algorithm.dataStructure;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hquery.huang
 * 2019/2/20 9:07:14
 */
public class BitMap {

    /**
     * char：2字节，16位
     */
    private char[] chars;

    private int maxNum;

    public BitMap(int maxNum) {
        this.chars = new char[maxNum / 16 + 1];
        this.maxNum = maxNum;
    }

    public void set(int num) {
        if (num > maxNum) return;
        int bytesIndex = num / 16;
        int bitIndex = num % 16;
        chars[bytesIndex] |= (1 << bitIndex);
    }

    public boolean get(int num) {
        if (num > maxNum) return false;
        int bytesIndex = num / 16;
        int bitIndex = num % 16;
        return (chars[bytesIndex] & (1 << bitIndex)) != 0;
    }

    /**
     * 基于位图的排序
     *
     * @param nums
     */
    public static void sort(int[] nums) {
        // 1、取到数组最大值
        int max = nums[0];
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
        }
        // 2、初始化位图
        BitMap bitMap = new BitMap(max);
        for (int num : nums) {
            bitMap.set(num);
        }
        // 3、遍历位图，寻找是1的标记位
        int index = 0;
        for (int i = 0; i < bitMap.chars.length; i++) {
            char aChar = bitMap.chars[i];
            for (int j = 0; j < 16; j++) {
                int i4 = 1 << j; // 如果该位置有值，则 & 运算必不等于0
                int i3 = aChar & i4; // 只取char的低j位
                if (i3 > 0) {
                    nums[index++] = i * 16 + j;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 7, 4, 2345, 64, 98, 8, 100};
        BitMap.sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
        BitMap bitMap2 = new BitMap(1413242342);
        bitMap2.set(100);
        System.out.println(bitMap2.get(100));
        List<String> list = Arrays.asList("A", "B", "C", "D");
//        ImmutableList<String> list = ImmutableList.of("A", "B", "C", "D");
        String collect = list.stream().collect(Collectors.joining("\",\"", "[\"", "\"]"));
        System.out.println(collect);
    }
}
