package com.wen.leetcode.simple;

import org.junit.Test;

/**
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 *
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *  
 *
 * 提示：
 *
 * 1 <= N <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divisor-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-24 11:32
 */
public class DivisorGame {

    @Test
    public void test() {
        System.out.println(divisorGame3(6));
    }

    /**
     * 这题的分类是动态规划，随意就想着按动态规范的方式去做。
     * 首先想的就是算N时根据前边已算出的结果计算后边的结果。
     * 所以想到用一个数组模拟先手下时的输赢情况，之后再计算后边结果时，寻找满足n%x==0 && solutionArr[n-x]==false的情况，此时N就为true。
     * 因为有了前边的计算结果，所以在前边的结果中一直寻找，就是里边的while循环。
     * 这样做就是时间复杂度比较高。
     * @param N 1<=N<=1000
     * @return 结果
     */
    public boolean divisorGame(int N) {
        boolean[] solutionArr = new boolean[N+1];
        for (int n=1; n<=N; n++) {
            int x = 1;
            while (x <= n/2) {
                if (n%x == 0 && !solutionArr[n-x]) {
                    solutionArr[n] = true;
                    break;
                }
                x++;
            }
        }
        return solutionArr[N];
    }

    /**
     * 通过打印divisorGame中生成的solutionArr中的结果，发现是false true false true来回交替的。
     * 所以想到了这个解。
     * @param N 1<=N<=1000
     * @return 结果
     */
    public boolean divisorGame2(int N) {
//        return N%2 == 0;
        return (N&1) != 1;
    }

    /**
     * 这个是看别人提交的，确实很巧妙
     * @param N 1<=N<=1000
     * @return 结果
     */
    public boolean divisorGame3(int N) {
        boolean flag = false;
        while (N > 0) {
//            int y = find(N);
            int y = N != 1 ? 1 : 0;
            if (y != 0) {
                N -= y;
                flag = !flag;
            } else {
                return flag;
            }
        }
        return flag;
    }

    private int find(int N) {
        for (int i = 1; i < N; ++i) {
            if (N % i == 0) {
                return i;
            }
        }
        return 0;
    }

}
