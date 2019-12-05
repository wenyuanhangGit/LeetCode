package com.wen.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-12-04 17:52
 */
public class ThreeSum {

    @Test
    public void test() {
//        threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        threeSum2(new int[]{-1, 0, 1, 2, -1, -4});
        threeSum3(new int[]{-1, 0, 1, 2, -1, -4});
    }

    /**
     * 暴力破解超时
     * @param nums 数组
     * @return 结果
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        for (int i=0; i<length; i++) {
            for (int j=i; j<length; j++) {
                if (i == j) {
                    continue;
                }
                for (int k=j; k<length; k++) {
                    if (j == k) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> subResult = new ArrayList<>();
                        subResult.add(nums[i]);
                        subResult.add(nums[j]);
                        subResult.add(nums[k]);
                        Collections.sort(subResult);
                        if (!result.contains(subResult)) {
                            result.add(subResult);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 这个不超时，但是执行时间300多ms，性能不行
     * @param nums 数组
     * @return 结果
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueIndexMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                Integer threeIndex = valueIndexMap.get(0 - nums[i] - nums[j]);
                if (threeIndex == null || j == threeIndex || i == threeIndex) {
                    continue;
                }
                List<Integer> subResult = new ArrayList<>();
                subResult.add(nums[i]);
                subResult.add(nums[j]);
                subResult.add(0 - nums[i] - nums[j]);
                Collections.sort(subResult);
                set.add(subResult);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 1.将数组排序 2.定义三个指针，i，j，k。
     * 遍历i，那么这个问题就可以转化为在i之后的数组中寻找nums[j]+nums[k]=-nums[i]这个问题，
     * 也就将三数之和问题转变为二数之和---（可以使用双指针）
     * @param nums 数组
     * @return 结果
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int c=nums.length-1; c>=2;) {
            for (int a=0, b=c-1; a<b;) {
                int tmp_sum = nums[a] + nums[b];
                if (tmp_sum < -nums[c]) {
                    ++a;
                } else if (tmp_sum > -nums[c]) {
                    --b;
                } else {
                    result.add(Arrays.asList(nums[a], nums[b], nums[c]));
                    do {//去重a b
                        ++a;
                    } while (a<b && nums[a-1] == nums[a]);
                    do {
                        --b;
                    } while (a<b && nums[b+1] == nums[b]);
                }
            }
            do {//去重c
                --c;
            } while (c>=2 && nums[c+1]==nums[c]);
        }
        return result;
    }

    public List<List<Integer>> threeSum4(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0;
        int posSize = 0;
        int zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) {
                minValue = v;
            }
            if (v > maxValue) {
                maxValue = v;
            }
            if (v > 0) {
                posSize++;
            } else if (v < 0) {
                negSize++;
            } else {
                zeroSize++;
            }
        }
        if (zeroSize >= 3) {
            res.add(Arrays.asList(0, 0, 0));
        }
        if (negSize == 0 || posSize == 0) {
            return res;
        }
        if (minValue * 2 + maxValue > 0) {
            maxValue = -minValue * 2;
        } else if (maxValue * 2 + minValue < 0) {
            minValue = -maxValue * 2;
        }

        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {
                if (map[v - minValue]++ == 0) {
                    if (v > 0) {
                        poses[posSize++] = v;
                    } else if (v < 0) {
                        negs[negSize++] = v;
                    }
                }
            }
        }
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {
            int nv = negs[i];
            int minp = (-nv) >>> 1;
            while (basej < posSize && poses[basej] < minp) {
                basej++;
            }
            for (int j = basej; j < posSize; j++) {
                int pv = poses[j];
                int cv = 0 - nv - pv;
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1) {
                            res.add(Arrays.asList(nv, nv, pv));
                        }
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1) {
                            res.add(Arrays.asList(nv, pv, pv));
                        }
                    } else {
                        if (map[cv - minValue] > 0) {
                            res.add(Arrays.asList(nv, cv, pv));
                        }
                    }
                } else if (cv < nv) {
                    break;
                }
            }
        }
        return res;
    }

}
