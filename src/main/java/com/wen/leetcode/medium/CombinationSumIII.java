package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-11 11:28
 */
public class CombinationSumIII {

    @Test
    public void test () {
        System.out.println(combinationSum3(3, 9));
    }

    /**
     * 有了前两题的思路，这题写起来比较简单。
     * @param k k个数
     * @param n 何为n
     * @return 结果
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] flags = new boolean[10];
        findCombination(flags, 1, k, n, result);
        return result;
    }

    /**
     * 有了前两天的思路，这题写起来比较简单。
     * @param flags 标识数字是否备选
     * @param start 其实位置
     * @param k k个数
     * @param n 和为n
     * @param result 结果
     */
    private void findCombination(boolean[] flags, int start, int k, int n, List<List<Integer>> result) {
        if (k == 0 && n == 0) {
            List<Integer> temp = new ArrayList<>(9);
            for (int i = 1; i < flags.length; i++) {
                if (flags[i]) {
                    temp.add(i);
                }
            }
            result.add(temp);
        }
        for (int i=start; i<10; i++) {
            //剪枝
            if (n - i < 0) {
                break;
            }
            flags[i] = true;
            findCombination(flags, i+1, k-1, n-i, result);
            flags[i] = false;
        }
    }

}
