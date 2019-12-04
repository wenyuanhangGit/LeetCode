package com.wen.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-11-22 11:29
 */
public class TwoSum {

    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 4};
        int[] actual = twoSum4(nums, 6);
        int[] expect = new int[]{1, 2};
        Assert.assertArrayEquals(expect, actual);
    }

    /**
     * 最开始的想法是暴利破解，可是一想时间复杂度要O(n*n)，就在想有没有时间复杂度低的方法。
     * 然后想到可以先遍历一遍nums，然后放到Map中，key为nums的元素，value为nums的下标，这个遍历的时间复杂度也就O(n)，之后Map中取的时间复杂度是O(1)。
     * 经过提交代码，发现不通过，有弊端。
     * 主要是因为nums中可能有重复的元素，而Map的key不能重复，导致不通过。
     * @param nums 整数数组
     * @param target 目标值
     * @return 数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!numMap.containsKey(nums[i])) {
                numMap.put(nums[i], i);
            }
        }
        for (int num : nums) {
            Integer first = numMap.get(num);
            Integer second = numMap.get(target - num);
            if (second != null && !first.equals(second)) {
                result[0] = first;
                result[1] = second;
                break;
            }
        }
        return result;
    }

    /**
     * 随后就用暴利破解先写了一个，可以通过，效果不是很理想。
     * @param nums 整数数组
     * @param target 目标值
     * @return 数组下标
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 最开始的想法是先用Map都缓存起来，从前往后匹配。
     * 最后转变一下，先缓存起来前边的数据，遍历后边数据时，再去前边的缓存找，这样也可以避免重复元素的问题。
     * 这个可以通过，执行时间还可以。
     * @param nums 整数数组
     * @param target 目标值
     * @return 数组下标
     */
    public int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer second = numMap.get(target - nums[i]);
            if (second != null) {
                result[0] = second;
                result[1] = i;
            }
            numMap.put(nums[i], i);
        }
        return result;
    }

    /**
     * 这个是查看别人的，执行时间只有1ms，粘过来学习一下。
     * 这个相当于用数组模拟Map，更剩空间和时间。集合类用来存放基本类型的包装类型是很浪费的。
     * 那个max的定义主要是为了防止数组越界使用。
     * 这个程序唯一的弊端可能是当nums中数大于2048时会出问题。
     * @param nums 整数数组
     * @param target 目标值
     * @return 数组下标
     */
    public int[] twoSum4(int[] nums, int target) {
        int max = 2047;
        int[] map = new int[2048];
        for(int i = 0; i < nums.length; i++ ){
            int dif = map[(target - nums[i]) & max];
            if (dif != 0){
                return new int[]{dif - 1, i};
            }
            map[(nums[i]) & max] = i + 1;
        }
        return null;
    }

}
