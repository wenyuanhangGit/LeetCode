package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 给定一个数组 candidates 和一个目标数 length ，找出 candidates 中所有可以使数字和为 length 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], length = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], length = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-10 17:28
 */
public class CombinationSumII {

    @Test
    public void test() {
        System.out.println(combinationSum4(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinationSum4(new int[]{2, 5, 2, 1, 2}, 5));
    }

    /**
     * 递归回溯，剪枝会有问题。
     * 这里用Set接收是为了去重，因为有的结果需要去重，但这样效率不行。
     * 需要采取好的去重方式。
     * @param candidates 数组
     * @param target 目标值
     * @return 结果
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> result = new HashSet<>();
        findCombinationSum2(target, 0, new Stack<>(), candidates, result);
        return new ArrayList<>(result);
    }

    /**
     * 递归回溯处理
     * @param residue 剩余数值
     * @param start 起始位置
     * @param stack 用来存放组合值
     * @param candidates 数组
     * @param result 结果
     */
    private void findCombinationSum2(int residue, int start, Stack<Integer> stack, int[] candidates, Set<List<Integer>> result) {
        if (residue < 0) {
            return;
        }
        if (residue == 0) {
            result.add(new ArrayList<>(stack));
        }
        for (int i = start; i<candidates.length && residue - candidates[i]>=0; i++) {
            stack.add(candidates[i]);
            findCombinationSum2(residue-candidates[i], i+1, stack, candidates, result);
            stack.pop();
        }
    }

    /**
     * 递归回溯，在递归过程中去重连续相同元素。
     * @param candidates 数组
     * @param target 目标值
     * @return 结果
     */
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        findCombinationSum3(target, 0, new Stack<>(), candidates, result);
        return new ArrayList<>(result);
    }

    /**
     * 递归回溯处理
     * @param residue 剩余数值
     * @param start 起始位置
     * @param stack 用来存放组合值
     * @param candidates 数组
     * @param result 结果
     */
    private void findCombinationSum3(int residue, int start, Stack<Integer> stack, int[] candidates, List<List<Integer>> result) {
        if (residue < 0) {
            return;
        }
        if (residue == 0) {
            result.add(new ArrayList<>(stack));
        }
        for (int i = start; i<candidates.length && residue - candidates[i]>=0; i++) {
            //去重连续相同元素
            if (i != start && candidates[i] == candidates[i-1]) {
                continue;
            }
            stack.add(candidates[i]);
            findCombinationSum3(residue-candidates[i], i+1, stack, candidates, result);
            stack.pop();
        }
    }

    /**
     * 被人提交的代码，主要使用数组记录candidates中的元素是否被选中。
     * 省去了stack。
     * @param candidates 数组
     * @param target 目标值
     * @return 结果
     */
    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        int[] flags = new int[candidates.length];
        findCombinationSum4(target, 0, flags, candidates, result);
        return new ArrayList<>(result);
    }

    private void findCombinationSum4(int residue, int start, int[] flags, int[] candidates, List<List<Integer>> result) {
        if (residue == 0) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < flags.length; i++) {
                if (flags[i] > 0) {
                    temp.add(candidates[i]);
                }
            }
            result.add(temp);
            return;
        }
        for (int i = start; i<candidates.length && residue - candidates[i]>=0; i++) {
            //剪枝
            if (residue - candidates[i] < 0) {
                break;
            }
            //去重连续相同元素
            if (i != start && candidates[i] == candidates[i-1]) {
                continue;
            }
            flags[i] = 1;
            findCombinationSum4(residue-candidates[i], i+1, flags, candidates, result);
            flags[i] = -1;
        }
    }

}
