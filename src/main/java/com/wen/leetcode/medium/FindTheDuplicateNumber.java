package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-20
 */
public class FindTheDuplicateNumber {

    @Test
    public void test() {
//        int[] nums = {1, 3, 4, 2, 2};
        int[] nums = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
//        int[] nums = {3, 1, 3, 4, 2};
        System.out.println(findDuplicate5(nums));
    }

    /**
     * 极慢也不符合要求
     * @param nums 数组
     * @return 重复的数组
     */
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }

    /**
     * 效果好一些，仍然不符合要求
     * @param nums 数组
     * @return 重复的数组
     */
    public int findDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return 0;
    }

    /**
     * 用数组比set快一些
     * @param nums 数组
     * @return 重复的数组
     */
    public int findDuplicate3(int[] nums) {
        int[] set = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            if (set[nums[i]] == 1) {
                return nums[i];
            }
            set[nums[i]] = 1;
        }
        return 0;
    }

    /**
     * 看了评论区，有人说快慢指针，让人想到了判断链表是否有环的思想。
     * @param nums 数组
     * @return 重复的数组
     */
    public int findDuplicate4(int[] nums) {
        int fast = 0, slow = 0;
        while(true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(slow == fast) {
                fast = 0;
                while(nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }

    /**
     * 看了评论区，有人说快慢指针，让人想到了判断链表是否有环的思想。
     * @param nums 数组
     * @return 重复的数组
     */
    public int findDuplicate5(int[] nums) {
        int res = 0;
        for (int fast = 0; res != fast || fast == 0;){
            res = nums[res];
            fast = nums[nums[fast]];
        }
        for (int i = 0; res != i; i = nums[i]){
            res = nums[res];
        }
        return res;
    }

}
