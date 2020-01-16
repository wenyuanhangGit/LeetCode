package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-16
 */
public class ProductOfArrayExceptSelf {

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4};
        int[] output = productExceptSelf3(nums);
        for (int i : output) {
            System.out.print(i + " ");
        }
    }

    /**
     * 需要时间很久
     * @param nums 数组
     * @return 结果
     */
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = product(nums, i);
        }
        return output;
    }

    private int product(int[] nums, int except) {
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i != except) {
                product *= nums[i];
            }
        }
        return product;
    }

    /**
     * 内存溢出
     * @param nums 数组
     * @return 结果
     */
    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] output = new int[length];
        int[][] matrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            matrix[i][i] = nums[i];
        }
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                matrix[i][j] = matrix[i][j-1] * nums[j];
            }
        }
        output[0] = matrix[1][length-1];
        output[length-1] = matrix[0][length-2];
        for (int i = 1; i < length - 1; i++) {
            output[i] = matrix[0][i-1] * matrix[i+1][length-1];
        }
        return output;
    }

    /**
     * 0 <= i <= n
     * positiveSequence[i]:从下标为0到i的所有数组元素的乘积。
     * invertSequence[i]  :从下标为n到i的所有数组元素的乘积。
     * @param nums 数组
     * @return 结果
     */
    public int[] productExceptSelf3(int[] nums) {
        int length = nums.length;
        int[] output = new int[length];
        int[] positiveSequence = new int[length];
        int[] invertSequence = new int[length];
        //positiveSequence[i]:从下标为0到i的所有数组元素的乘积。
        positiveSequence[0] = nums[0];
        for (int i=1; i<length; i++) {
            positiveSequence[i] = nums[i] * positiveSequence[i-1];
        }
        //invertSequence[i]  :从下标为n到i的所有数组元素的乘积。
        invertSequence[length - 1] = nums[length - 1];
        for (int i=length-2; i>=0; i--) {
            invertSequence[i] = nums[i] * invertSequence[i+1];
        }
        //计算output
        output[0] = invertSequence[1];
        output[length - 1] = positiveSequence[length - 2];
        for (int i=1; i<length-1; i++) {
            output[i] = positiveSequence[i-1] * invertSequence[i+1];
        }
        return output;
    }

}
