package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-16
 */
public class MinimumPathSum {

    @Test
    public void test() {
//        int[][] grid = {
//                {1, 3, 1},
//                {1, 5, 1},
//                {4, 2, 1}
//            };
//        int[][] grid = {
//                {1, 2, 3},
//                {4, 5, 6}
//        };
        int[][] grid = {
                {1, 2},
                {1, 1}
        };
        System.out.println(minPathSum3(grid));
    }

    private int minPathSum;
    private int rowCount;
    private int colCount;

    /**
     * 才开始是想通过图的遍历的方式解这道题，可是不行。
     * 这个超时，思路就是遍历所有，然后从中找出一个路径最短的。
     * 这题应该是可以通过动态规划实现的。
     * @param grid m * n网格
     * @return 最小路径总和
     */
    public int minPathSum(int[][] grid) {
        rowCount = grid.length;
        colCount = grid[0].length;
        calculate(grid, 0, 0, grid[0][0]);
        return minPathSum;
    }

    private void calculate(int[][] grid, int row, int col, int pathSum) {
        if (row >= rowCount || col >= colCount) {
            return;
        }
        if (row == rowCount - 1 && col == colCount - 1) {
            if (minPathSum == 0) {
                minPathSum = pathSum;
            }
            minPathSum = minPathSum < pathSum ? minPathSum : pathSum;
            return;
        }
        if (row + 1 <= rowCount - 1) {
            calculate(grid, row + 1, col, pathSum + grid[row + 1][col]);
        }
        if (col + 1 <= colCount - 1) {
            calculate(grid, row, col + 1, pathSum + grid[row][col+1]);
        }
    }

    /**
     * 动态规划，才开始没想到思路这么简单，是自己把这题想复杂了。
     * 不过经过提交测试，发现，时间和内存都还有优化的地步。
     * @param grid m * n网格
     * @return 最小路径总和
     */
    public int minPathSum2(int[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int[][] dp = new int[rowCount][colCount];
        dp[0][0] = grid[0][0];
        for (int i=1; i<rowCount; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i=1; i<colCount; i++) {
            dp[0][i] += dp[0][i-1] + grid[0][i];
        }
        for (int i=1; i<rowCount; i++) {
            for (int j=1; j<colCount; j++) {
                dp[i][j] = grid[i][j] + (dp[i-1][j] < dp[i][j-1] ? dp[i-1][j] : dp[i][j-1]);
            }
        }
        return dp[rowCount-1][colCount-1];
    }

    /**
     * 分析后发现，其实dp只需要一个一维数组。
     * 测试提交后，发现执行时间确实少了，没想到内存消耗竟然增加了。
     * @param grid m * n网格
     * @return 最小路径总和
     */
    public int minPathSum3(int[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int[] dp = new int[colCount];
        dp[0] = grid[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        for (int i=1; i<rowCount; i++) {
            dp[0] += grid[i][0];
            for (int j=1; j<colCount; j++) {
                dp[j] = grid[i][j] + (dp[j] < dp[j-1] ? dp[j] : dp[j-1]);
            }
        }
        return dp[colCount-1];
    }

}
