package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-11 13:42
 */
public class Permutations {

    @Test
    public void test() {
        System.out.println(permute2(new int[]{1, 2, 3}));
    }

    /**
     * 全排列，用到回溯搜索。
     * “回溯搜索”算法即“深度优先遍历 + 状态重置 + 剪枝”（这道题没有剪枝）
     * @param nums 数组
     * @return 结果
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int[] visited = new int[nums.length];
        findPermute(nums, visited, new ArrayList<>(),  result);
        return result;
    }

    private void findPermute(int[] nums, int[] visited, List<Integer> tmp, List<List<Integer>> result) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            //标识已访问
            visited[i] = 1;
            tmp.add(nums[i]);
            findPermute(nums, visited, tmp, result);
            //状态重置
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 使用递归的思想如下，个人感觉没有回溯算法经典：
     * 首先，排在第 1 位，可能的情况有 n 种，剩下的 n - 1 位数的排列可以递归求解；
     * 不过要解决的 1 个问题是，剩下的 n - 1 位数的排列不能够包括排在第 1 位的那个数，根据“选择排序”的思想，把剩下的 n - 1 位数依次交换到第 1 位即可。
     * 例如：[1, 2, 3, 4] 的全排列可以由下面 4 种情况得到：
     *
     * 1 + permute([2, 3, 4])
     *
     * 2 + permute([1, 3, 4])
     *
     * 3 + permute([1, 2, 4])
     *
     * 4 + permute([1, 2, 3])
     *
     * 根据这个思路，编码如下，注意：在递归方法执行以后，需要再执行一次交换操作，这一步是状态重置的操作，从这一点上说，和方法一的思路是一样的。
     * @param nums 数组
     * @return 结果
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        helper(nums, 0, len, res);
        return res;
    }

    private void helper(int[] nums, int begin, int len, List<List<Integer>> res) {
        if (begin == len - 1) {
            List<Integer> currRes = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                currRes.add(nums[i]);
            }
            res.add(currRes);
            return;
        }
        helper(nums, begin + 1, len, res);
        // 从 begin 的下一位开始一直要交换到最后一位
        for (int i = begin + 1; i < len; i++) {
            swap(nums, begin, i);
            helper(nums, begin + 1, len, res);
            // 注意：递归完成以后要交换回来
            swap(nums, begin, i);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

}
