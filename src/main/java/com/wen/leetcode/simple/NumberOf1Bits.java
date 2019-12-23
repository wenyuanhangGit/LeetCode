package com.wen.leetcode.simple;

import org.junit.Test;

/**
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *  
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-23 11:45
 */
public class NumberOf1Bits {

    @Test
    public void test() {
        System.out.println(hammingWeight3(00000000000000000000000000001011));
    }

    /**
     * 这个超时。
     * 不是太理解提示的意思。
     * @param n 无符号整数
     * @return 位数为1的个数
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n&1;
            n >>= 1;
        }
        return count;
    }

    /**
     *
     * @param n 无符号整数
     * @return 位数为1的个数
     */
    public int hammingWeight2(int n) {
        int count = 0;
        for (int i=0; i<32; i++) {
            if (((n>>i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 官方题解。
     * 我们可以把前面的算法进行优化。我们不再检查数字的每一个位，而是不断把数字最后一个 1 反转，并把答案加一。当数字变成 0 的时候偶，我们就知道它没有 1 的位了，此时返回答案。
     * @param n 无符号整数
     * @return 位数为1的个数
     */
    public int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n-1;
        }
        return count;
    }

}
