package com.wen.leetcode.medium;

import org.junit.Test;

public class EightQueen {

    private int N = 8;

    private int count = 0;

    private int[][] board = new int[8][8];

    @Test
    public void test() {
        queen(0);
        System.out.println(count);
    }

    public void queen(int curRow) {
        if (curRow >= N) {
            count++;
            return;
        }
        for (int i=0; i<N; i++) {
            if(isOk(curRow, i)) {
                board[curRow][i] = 1;
                queen(curRow+1);
                board[curRow][i] = 0;
            }
        }
    }

    private boolean isOk(int curRow, int curCol) {
        for (int i=0; i<curRow; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] == 1 && (Math.abs(curRow-i) == Math.abs(curCol-j) || j == curCol)) {
                    return false;
                }
            }
        }
        return true;
    }



    @Test
    public void test2() {
        dfs(0);
        System.out.println(ans);
    }

    private int ans = 0;

    private boolean[] row = new boolean[8];
    private boolean[] x1 = new boolean[16];
    private boolean[] x2 = new boolean[16];

    private boolean check(int c,int i) {
        return !row[i] && !x1[c+i] && !x2[c-i+8];
    }

    public void dfs(int c) {
        if (c == 8) {
            ans++;
            return;
        }
        for (int i=0; i<8; i++) {
            if (check(c, i)) {
                row[i] = x1[c+i] = x2[c-i+8] = true;
                dfs(c+1);
                row[i] = x1[c+i] = x2[c-i+8] = false;
            }
        }
    }



    @Test
    public void test3() {
        queenSettle(0, 0, 0, 0);
        System.out.println(count);
        System.out.println(5 & -5);
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
    }

    /**
     * 位运算
     * @param row 当前处理的行
     * @param column 所有上方放置的皇后导致当前行格子不可用的集合
     * @param pie 左斜线方向导致当前行格子不可用的集合
     * @param na 右斜线方向导致当前行格子不可用的集合
     */
    private void queenSettle(int row, int column, int pie, int na) {
        if (row >= N) { // 遍历到最后一行说明已经找到符合的条件了
            count++;
            return;
        }
        // 取出当前行可放置皇后的格子
        int bits = (~(column | pie | na) & ((1 << N) - 1));
        while (bits > 0) {
            // 每次从当前行可用的格子中取出最右边位为 1 的格子放置皇后
            int p = bits & -bits;
            // 紧接着在下一行继续放皇后
            queenSettle(row+1, column|p, (pie|p)<<1, (na|p)>>1);
            // 当前行最右边格子已经选完了，将其置成 0，代表这个格子已遍历过
            bits = bits & (bits-1);
        }
    }

}
