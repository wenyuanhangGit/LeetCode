package com.wen.leetcode.simple;

import org.junit.Test;

/**
 * 给你一个 n 行 m 列的矩阵，最开始的时候，每个单元格中的值都是 0。
 *
 * 另有一个索引数组 indices，indices[i] = [ri, ci] 中的 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 *
 * 你需要将每对 [ri, ci] 指定的行和列上的所有单元格的值加 1。
 *
 * 请你在执行完所有 indices 指定的增量操作后，返回矩阵中 「奇数值单元格」 的数目。

 * 示例 1：

 * 输入：n = 2, m = 3, indices = [[0,1],[1,1]]
 * 输出：6
 * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
 * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
 * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
 *
 *
 * 示例 2：
 *
 * 输入：n = 2, m = 2, indices = [[1,1],[0,0]]
 * 输出：0
 * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 50
 * 1 <= m <= 50
 * 1 <= indices.length <= 100
 * 0 <= indices[i][0] < n
 * 0 <= indices[i][1] < m
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cells-with-odd-values-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-24 17:45
 */
public class OddCells {

    @Test
    public void test() {
        int n = 48, m = 37;
//        int[][] indices = new int[][]{{0, 1}, {1, 1}};
        int[][] indices = new int[][]{{40, 5}};
        System.out.println(oddCells2(n, m, indices));
    }

    /**
     * 这个是最容易想到的方法，借助于一个数组将值算一遍再统计，但是感觉有些蠢。
     * 感觉可以直接通过n，m和indices算出来。
     * @param n 行数
     * @param m 列数
     * @param indices 数组
     * @return 奇数个数
     */
    public int oddCells(int n, int m, int[][] indices) {
        int result = 0;
        int[][] arr = new int[n][m];
        for (int[] row : indices) {
            for (int i=0; i<m; i++) {
                arr[row[0]][i]++;
            }
            for (int i=0; i<n; i++) {
                arr[i][row[1]]++;
            }
        }
        for (int[] row : arr) {
            for (int column : row) {
                if (column % 2 != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 别人的解法
     * @param n 行数
     * @param m 列数
     * @param indices 数组
     * @return 奇数个数
     */
    public int oddCells2(int n, int m, int[][] indices) {
        boolean[] r = new boolean[n];
        boolean[] c = new boolean[m];
        int i;
        for (i = 0; i < indices.length; i++) {
            r[indices[i][0]] = !r[indices[i][0]];
            c[indices[i][1]] = !c[indices[i][1]];
        }
        int rr = 0, cc = 0;
        for (i = 0; i < r.length; i++) {
            if(r[i])rr++;
        }
        for (i = 0; i < c.length; i++) {
            if(c[i])cc++;
        }
        return rr * m + cc * n - rr * cc * 2;
    }

}
