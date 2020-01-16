package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-16
 */
public class CountSquareSubmatricesWithAllOnes {

    @Test
    public void test() {
//        int[][] matrix = {
//                {1, 0, 1},
//                {1, 1, 0},
//                {1, 1, 0}
//            };
        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
            };
        System.out.println(countSquares(matrix));
    }

    /**
     * 自己在看到这题时的第一想法是图的搜索算法。
     * 然后自己思考了思考，发现不行，解不了。
     * 之后看了评论区中的第一个，原来是用动态规划。
     * 看了别人的解题思路及代码，真的是太巧妙了，动态规划解这个题。
     * 自己只明白一些动态规划的思想，然而在实际的应用中确实，不好想到，也不好完整写出来。
     * 本来是想着先从数组类的做起的，可在做一些数组类的题时，都是用动态规划的思想解的。
     * 自己还需更加努力。
     * @param matrix 矩阵
     * @return 全为1写自矩阵数
     */
    public int countSquares(int[][] matrix) {
        int result = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row+1][col+1];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (matrix[i][j] == 1) {
                    dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i][j+1], dp[i+1][j])) + 1;
                    result += dp[i+1][j+1];
                }
            }
        }
        return result;
    }

}
