package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-10 11:44
 */
public class CombinationSum {

    @Test
    public void test() {
        System.out.println(combinationSum3(new int[]{2, 3, 6, 7}, 7));
    }

    /**
     * 利用递归
     * @param candidates 数组
     * @param target 目标数值
     * @return 结果
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> listAll = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //排序
        Arrays.sort(candidates);
        find(listAll, list, candidates, target, 0);
        return listAll;
    }

    public void find(List<List<Integer>> listAll, List<Integer> tmp, int[] candidates, int target, int num) {
        //递归的终点
        if( target == 0) {
            listAll.add(tmp);
            return;
        }
        if (target<candidates[num]) {
            return;
        }
        for (int i=num; i<candidates.length && candidates[i]<=target; i++) {
            //拷贝一份，不影响下次递归
            List<Integer> list = new ArrayList<>(tmp);
            list.add(candidates[i]);
            //递归运算，将i传递至下一次运算是为了避免结果重复。
            find(listAll, list, candidates, target-candidates[i], i);
        }
    }

    /**
     * 回溯算法+剪枝
     * @param candidates 数组
     * @param target 目标数值
     * @return 结果
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum2(target, 0, new Stack<>());
        return res;
    }

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    private void findCombinationSum2(int residue, int start, Stack<Integer> pre) {
        //剪枝
        if (residue < 0) {
            return;
        }
        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findCombinationSum2(residue - candidates[i], i, pre);
            pre.pop();
        }
    }

    /**
     * 回溯算法+剪枝。利用加法做。
     * @param candidates 数组
     * @param target 目标数值
     * @return 结果
     */
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        this.target = target;
        findCombinationSum3(0, 0, new Stack<>());
        return res;
    }

    private int target;

    private void findCombinationSum3(int residue, int start, Stack<Integer> pre) {
        //剪枝
        if (residue > this.target) {
            return;
        }
        if (residue == this.target) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue + candidates[i] <= this.target; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findCombinationSum3(residue + candidates[i], i, pre);
            pre.pop();
        }
    }

}