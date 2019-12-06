package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-06 10:27
 */
public class SpiralMatrix {

    @Test
    public void test() {
        int n = 4;
        int[][] ints = generateMatrix(n);
        for (int a=0; a<n; a++) {
            for (int b=0; b<n; b++) {
                System.out.print(ints[a][b] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 依次遍历：
     *
     * 从左到右
     * 从上到下
     * 从右到左
     * 从下到上
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int num = 1;
        int j = 0;
        int total = n*n;
        while (num <= total) {
            for (int i=j; i<n-j; i++) {
                arr[j][i] = num++;
            }
            for (int i=j+1; i<n-j; i++) {
                arr[i][n-j-1] = num++;
            }
            for (int i=n-j-2; i>=j; i--) {
                arr[n-j-1][i] = num++;
            }
            for (int i=n-j-2; i>=j+1; i--) {
                arr[i][j] = num++;
            }
            j++;
        }
        return arr;
    }

}
