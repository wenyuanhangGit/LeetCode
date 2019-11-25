package com.wen.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-11-25 11:11
 */
public class LengthOfLongestSubstring {

    @Test
    public void test() {
//        Assert.assertEquals(3, lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring4("abcabcbb"));
        System.out.println(lengthOfLongestSubstring4("bbbbb"));
        System.out.println(lengthOfLongestSubstring4("pwwkew"));
    }

    /**
     * 这题没有什么思路，所以看了看评论区，和官方的解答。
     * 暴力破解还是能够想到的，先弄暴力破解写一下，然后学习一下别人的算法。
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int ans = 0;
        for (int i=0; i<length; i++) {
            for (int j=i+1; j<=length; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j-i);
                }
            }
        }
        return ans;
    }

    /**
     * 判断字符串是否有重复字符
     * @param s 字符串
     * @param start 开始位置
     * @param end 结束位置
     * @return 是否有重复字符串
     */
    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<Character>();
        for (int i=start; i<end; i++) {
            Character c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

    /**
     * 看了一下官方的一个结题思路，滑动窗口，准备自己先写一下试试。
     * 没写出来，看一下官方解答。
     *
     * 执行用时  内存消耗
     * 20 ms	37.6 MB
     *
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<Character>();
        int n = s.length();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 根据上一个利用HashSet做的滑动窗口，可以完美的解决问题。
     * 我在才开始思考的时候一直在考虑怎么用HashSet做滑动窗口，我感觉应该已HashMap做滑动窗口。
     * 因为：
     *      当我们发现一个重复的字符串时是移除i位置的字符，然后i一直加的处理方式。
     *      我感觉应该是直接删除i到重复字符的位置，然后i从重复字符位置开始重新计算。
     * 在此，利用HashMap实现一次。
     *
     * 执行用时  内存消耗
     * 14 ms	 37.4 MB
     *
     * 这个的执行时间和内存消耗果然比官方第二个解答要好一些，一会看看官方的关于滑动窗口的优化。
     * 估计优化的思路就是这个样子的。
     *
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring3(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            char c = s.charAt(j);
            if (!map.containsKey(c)) {
                map.put(c, j++);
                ans = Math.max(ans, j - i);
            } else {
                int repeatCharPos = map.get(c);
                //清除i到重复字符之间的字符标记
                for (int k=i; k<=repeatCharPos; k++) {
                    map.remove(s.charAt(k));
                }
                i = repeatCharPos + 1;
            }
        }
        return ans;
    }

    /**
     * 准备用数据实现一次，估计效果会比HashSet和HashMap要好。
     *
     * 执行用时  内存消耗
     * 3 ms	     37.1 MB
     *
     * 这个在速度上有提升
     *
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length();
        int ans = 0, i = 0, j = 0;
        int[] arr = new int[256];
        //这个初始化为-1是因为i，j下标从0开始
        for (int k=0; k<arr.length; k++) {
            arr[k] = -1;
        }
        while (i < n && j < n) {
            int c = s.charAt(j);
            if (arr[c] == -1) {
                arr[c] = j++;
                ans = Math.max(ans, j - i);
            } else {
                int repeatCharPos = arr[c];
                //清除i到重复字符之间的字符标记
                for (int k=i; k<=repeatCharPos; k++) {
                    arr[s.charAt(k)] = -1;
                }
                i = repeatCharPos + 1;
            }
        }
        return ans;
    }

    /**
     * 官方滑动窗口优化
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring5(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 官方滑动窗口优化
     * 看了一下官方的代码，还是有很大差距，在思路和代码上都要努力。
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring6(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            //直接省去了if,确定i的位置
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            //直接在数组中记录，相当于省去了if判断，直接覆盖
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

}
