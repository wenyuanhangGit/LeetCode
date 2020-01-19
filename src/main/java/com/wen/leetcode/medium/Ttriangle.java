package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-19
 */
public class Ttriangle {

    @Test
    public void test() {
        List<Integer> one = new ArrayList<>();
        one.add(2);

        List<Integer> two = new ArrayList<>();
        two.add(3);
        two.add(4);

        List<Integer> three = new ArrayList<>();
        three.add(6);
        three.add(5);
        three.add(7);

        List<Integer> four = new ArrayList<>();
        four.add(4);
        four.add(1);
        four.add(8);
        four.add(3);

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(one);
        triangle.add(two);
        triangle.add(three);
        triangle.add(four);

        System.out.println(minimumTotal3(triangle));
    }

    /**
     * 主要思想是：
     *      每一个值有两条路径，从上到下，选择上层小的那项加到下边，最后选择最下边一层中最小的值就是答案。
     * 主要弊端可能是用list比较耗时。
     * @param triangle 数字三角形
     * @return 最小路径和
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> beforeList = triangle.get(i - 1);
            List<Integer> curList = triangle.get(i);
            for (int j = 0; j < curList.size(); j++) {
                if (j == 0) {
                    curList.add(j, curList.remove(j) + beforeList.get(j));
                    continue;
                }
                if (j == curList.size() - 1) {
                    curList.add(j, curList.remove(j) + beforeList.get(j-1));
                    continue;
                }
                curList.add(j, curList.remove(j) + Math.min(beforeList.get(j-1), beforeList.get(j)));
            }
        }
        return Collections.min(triangle.get(triangle.size()-1));
    }

    /**
     * 优化一下。竟然还需要5ms。
     * 猜测可能是因为List的可能用的是链表实现的，或者是Integer的装箱和拆箱问题。
     * @param triangle 数字三角形
     * @return 最小路径和
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            //才开始这里用的直接是List，执行用时是5ms。
            //换成数组执行用时是2ms。
            Integer[] curList = triangle.get(i).toArray(new Integer[]{});
            //引入这个值，主要是因为当在获取dp[j-1]的值时，其实已经在处理curList[j-1]是替换了。
            int beforeValue = dp[0];
            dp[0] += curList[0];
            for (int j = 1; j < curList.length; j++) {
                if (j == curList.length - 1) {
                    dp[j] += curList[j] + beforeValue;
                    continue;
                }
                int temp = dp[j];
                dp[j] = curList[j] + Math.min(dp[j], beforeValue);
                beforeValue = temp;
            }
        }
        int minValue = dp[0];
        for (int value : dp) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    /**
     * 看了评论区别人的代码，一眼看到了triangle[0][0]。
     * 瞬间明白了，可以从下向上构造，也省的从最后一个list中选取最下的数。
     * 这样也别避免了前一个数被替换而引入beforeValue的问题。
     * 自己的思维还是有局限性。
     * @param triangle 数字三角形
     * @return 最小路径和
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()+1];
        for (int i=triangle.size()-1; i>=0; i--) {
            Integer[] curList = triangle.get(i).toArray(new Integer[]{});
            for (int j=0; j<curList.length; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + curList[j];
            }
        }
        return dp[0];
    }


    /**
     * 别人提交的，用bfs做的
     * @param triangle 数字三角形
     * @return 最小路径和
     */
    public int minimumTotal4(List<List<Integer>> triangle) {
        int row = triangle.size();
        Integer[][] memo = new Integer[row][row];
        return dfsMinimumTotal(0,0,triangle, memo);
    }

    public int dfsMinimumTotal(int level, int len, List<List<Integer>> triangle, Integer[][] memo) {
        // terminator
        if (memo[level][len] != null)
            return memo[level][len];
        if (level == triangle.size() - 1)
            return memo[level][len] = triangle.get(level).get(len);

        // drill down
        int left = dfsMinimumTotal(level + 1, len, triangle, memo);
        int right = dfsMinimumTotal(level + 1, len + 1, triangle, memo);

        // process current logic
        memo[level][len] = Math.min(left, right) + triangle.get(level).get(len);
        return memo[level][len];
    }

}
