package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-06 11:22
 */
public class SubSets {

    @Test
    public void test() {
        System.out.println(subsets3(new int[]{1,2,3}));
    }

    /**
     * 先初始化一个空List，里边有个空List。
     * 然后挨个元素遍历，将新元素加入到每个
     * @param nums 数组
     * @return 子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> subResult = new ArrayList<>();
            for (List<Integer> src : result) {
                List<Integer> dest = new ArrayList<>(src);
                dest.add(num);
                subResult.add(dest);
            }
            result.addAll(subResult);
        }
        return result;
    }

    /**
     * 这个利用位运算的思路确实不错，没有想到，还是看到了评论区中别人写的。
     * @param nums 数组
     * @return 子集
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int length = nums.length;
        int subSize = 1 << length;
        int hash = 1;
        while (hash < subSize) {
            List<Integer> temp = new ArrayList<>();
            for (int i=0; i<length; i++) {
                int a = 1 << i;
                if ((a & hash) > 0){
                    temp.add(nums[i]);
                }
            }
            result.add(temp);
            hash++;
        }
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    /**
     * DFS
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        DFS(nums, new ArrayList<>(), 0);
        return result;
    }

    public void DFS(int[] nums, List<Integer> list, int index) {
        if (index == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        DFS(nums, list, index + 1);
        list.add(nums[index]);
        DFS(nums, list, index + 1);
        list.remove(list.size() - 1);
    }

}
