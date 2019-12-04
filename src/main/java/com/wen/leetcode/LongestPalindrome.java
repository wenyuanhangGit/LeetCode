package com.wen.leetcode;

import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-11-27 11:52
 */
public class LongestPalindrome {

    @Test
    public void test() {
//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome2("babad"));
        System.out.println(longestPalindrome5("babad"));
        System.out.println(longestPalindrome5("cbbd"));
    }

    /**
     * 最开始想的是用两个下标模拟指针，操作栈的方式来做，最后没写出来。
     * 然后，看的官方题解。
     * 这个用的是中心扩展算法，从思路上是比较好理解的。
     * @param s 原始串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 动态规划
     * 首先定义P(i,j):
     *     P(i,j) = true,   s[i,j]是回文串
     *     P(i,j) = false,  s[i,j]不是回文串
     *  接下来
     *      P(i,j) = (P(i+1,j-1) && S[i] == S[j])
     *
     * 所以如果我们想知道P(i,j)的情况，不需要调用判断回文串的函数了，
     * 只需要知道P(i+1,j-1)的情况就可以了，所以这样复杂度就少了O(n)。
     * 因此我们可以用动态规划的方法，空间换时间，把已经求出的P(i,j)存储起来。
     *
     * 如果S[i+1,j-1]是回文串，那么只要S[i]==S[j]，就可以确定S[i,j]也是回文串了。
     *
     * 求长度为1和长度为2的P(i,j)是不能用上边的公式，因此我们带入公式后会遇到P[i][j]中i>j的情况，
     * 比如求P[1][2]的话，我们需要知道P[1+1][2-1]=P[2][1]，而P[2][1]代表着S[2][1]是不是回文串，
     * 显然是不对的，所以需要单独判断。
     *
     * 所以我们先初始化长度是1的回文串的P[i,j],这样利用上边提出的公式P(i,j) = (P(i+1,j-1) && S[i] == S[j])，
     * 然后两边向外各扩充一个字符，长度为3的，为5的，所有奇数长度的就都求出来了。
     *
     * 同理，初始化长度是2的回文串P[i,i+1]，利用公式，长度为4的，6的所有偶数长度的就都求出来了。
     * @param s 原始串
     * @return 最长回文子串
     */
    public String longestPalindrome2(String s) {
        int length = s.length();
        boolean[][] P = new boolean[length][length];
        int maxLen = 0;
        String maxPal = "";
        for (int len = 1; len <= length; len++) {//遍历所有的长度
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                if (end >= length) //下标已经越界，结束本次循环
                    break;
                P[start][end] = (len == 1 || len == 2 || P[start + 1][end - 1]) && s.charAt(start) == s.charAt(end); //长度为 1 和 2 的单独判断下
                if (P[start][end] && len > maxLen) {
                    maxPal = s.substring(start, end + 1);
                }
            }
        }
        return maxPal;
    }

    /**
     * 动态规划
     * 递推公式中我们可以看到，我们首先知道了i+1才会知道i，所以我们只需要倒这遍历就行了
     * @param s 原始串
     * @return 最长回文子串
     */
    public String longestPalindrome3(String s) {
        int length = s.length();
        String maxStr = "";
        boolean[][] P = new boolean[length][length];
        for (int i=length-1; i>=0; i--) {
            for (int j=i; j<length; j++) {
                P[i][j] = (j-i<2 || P[i+1][j-1]) && s.charAt(i) == s.charAt(j);
                if (P[i][j] && j-i+1 > maxStr.length()) {
                    maxStr = s.substring(i, j+1);
                }
            }
        }
        return maxStr;
    }

    /**
     * 动态规划
     * 当求第 i 行的时候我们只需要第 i+1 行的信息，并且 j 的话需要 j - 1 的信息，所以和之前一样 j 也需要倒叙。
     * @param s 原始串
     * @return 最长回文子串
     */
    public String longestPalindrome4(String s) {
        int length = s.length();
        String maxStr = "";
        boolean[] P = new boolean[length];
        for (int i=length-1; i>=0; i--) {
            for (int j=length-1; j>=i; j--) {
                P[j] = (j-i<3 || P[j-1]) && s.charAt(i) == s.charAt(j);
                if (P[j] && j-i+1>maxStr.length()) {
                    maxStr = s.substring(i, j+1);
                }
            }
        }
        return maxStr;
    }

    /**
     * 暴利破解
     * @param s 原始串
     * @return 最长回文子串
     */
    public String longestPalindrome5(String s) {
        int length = s.length();
        String maxStr = "";
        for (int i=0; i<length; i++) {
            for (int j=0; j<length; j++) {
                if (isPalindrome(s, i, j) && j-i+1 > maxStr.length()) {
                    maxStr = s.substring(i, j+1);
                }
            }
        }
        return maxStr;
    }

    private boolean isPalindrome(String s, int low, int high) {
        int mid = (low + high-1) / 2;
        for (int i=0; i<=mid; i++) {
            if (low + i < s.length() && high-i >= 0 && s.charAt(low+i) != s.charAt(high-i)) {
                return false;
            }
        }
        return true;
    }

}
