package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-17 17:41
 */
public class CountingBits {

    @Test
    public void test() {
        int[] result = countBits4(10);
        for (int i=0; i<result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    /**
     * 会存在大量的重复计算，效率不是很好。
     * @param num 非负整数
     * @return result
     */
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for (int i=0; i<=num; i++) {
            int temp = i;
            int count = 0;
            while (temp > 0) {
                if ((temp & 1) == 1) {
                    count++;
                }
                temp >>= 1;
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * 通过找规律：
     *          result[i] = result[i>>1] + 1, i为奇数
     *          result[i] = result[i>>1],     i为偶数
     * 其中i为当前所求的数，result[i]为i的二进制数中所含1的个数。
     * @param num 非负整数
     * @return result
     */
    public int[] countBits2(int num) {
        int[] result = new int[num+1];
        for (int i=0; i<=num; i++) {
            if ((i & 1) == 1) {
                result[i] = result[i>>1] + 1;
            } else {
                result[i] = result[i>>1];
            }
        }
        return result;
    }

    /**
     * (i&1) 省去if else
     * @param num 非负整数
     * @return result
     */
    public int[] countBits3(int num) {
        int[] result = new int[num+1];
        for (int i=0; i<=num; i++) {
            result[i] = result[i>>1] + (i&1);
        }
        return result;
    }

    /**
     * 官方题解Pop count
     * @param num 非负整数
     * @return result
     */
    public int[] countBits4(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }

}
