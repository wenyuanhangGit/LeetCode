package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-19
 */
public class FindAllDuplicatesInAnArray {

    @Test
    public void test() {
        int[] nums = {4, 1, 2, 8, 8, 2, 3, 1};
        System.out.println(findDuplicates3(nums));
    }

    /**
     * 时间空间都很高。
     * @param nums 整数数组
     * @return 重复元素集合
     */
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            if (set.contains(num)) {
                result.add(num);
                continue;
            }
            set.add(num);
        }
        return result;
    }

    /**
     * 才开始一直想着在nums[i]上加maxValue，然后通过减的方式去计算，写了好久都有问题。
     * 最后突然想到求模，没想到这么简单，浪费我昨天一下午。
     * 应该还可以优化。
     * @param nums 整数数组
     * @return 重复元素集合
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int maxValue = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i] % maxValue == 0 ? nums.length : nums[i] % maxValue;
            if (nums[temp - 1] > maxValue) {
                result.add(temp);
            } else {
                nums[temp - 1] += maxValue;
            }
        }
        return result;
    }

    /**
     * 别人用的取反，测试了一下，没有比上一个快。
     * @param nums 整数数组
     * @return 重复元素集合
     */
    public List<Integer> findDuplicates3(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (nums[num - 1] > 0) {
                nums[num - 1] *= -1;
            } else {
                result.add(num);
            }
        }
        return result;
    }

}
