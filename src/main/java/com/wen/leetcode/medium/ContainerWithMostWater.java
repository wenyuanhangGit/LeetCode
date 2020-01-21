package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-21
 */
public class ContainerWithMostWater {

    @Test
    public void test() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea2(height));
    }

    /**
     * 比较笨的方法，时间复杂度O(n*n)。
     * @param height 非负整数数组
     * @return 最大容器
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    /**
     * 这个是看的评论区中其他人的思路，真的很巧妙
     *
     * 基本的表达式:
     *          area = min(height[i], height[j]) * (j - i)
     * 使用两个指针，值小的指针向内移动，这样就减小了搜索空间
     * 因为面积取决于指针的距离与值小的值乘积，如果值大的值向内移动，距离一定减小，
     * 而求面积的另外一个乘数一定小于等于值小的值，因此面积一定减小，
     * 而我们要求最大的面积，因此值大的指针不动，而值小的指针向内移动遍历
     * @param height 非负整数数组
     * @return 最大容器
     */
    public int maxArea2(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

}
