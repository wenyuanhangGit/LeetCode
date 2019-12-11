package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-11 15:19
 */
public class PermutationsII {

    @Test
    public void test() {
//        System.out.println(permuteUnique(new int[]{1, 1, 2}));
        System.out.println(permuteUnique(new int[]{3, 3, 0, 3}));
    }

    /**
     * 递归回溯，主要是先需要排序，然后剪枝。
     * @param nums 数组
     * @return 结果
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int[] visited = new int[nums.length];
        findPermuteUnique(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private void findPermuteUnique(int[] nums, int[] visited, List<Integer> tmp, List<List<Integer>> result) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            //剪枝
            if (i > 0 && nums[i]==nums[i-1] && visited[i-1] == 1) {
                continue;
            }
            visited[i] = 1;
            tmp.add(nums[i]);
            findPermuteUnique(nums, visited, tmp, result);
            visited[i] = -1;
            tmp.remove(tmp.size() - 1);
        }
    }

}
