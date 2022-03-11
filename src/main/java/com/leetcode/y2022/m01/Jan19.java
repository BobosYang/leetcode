package com.leetcode.y2022.m01;

import java.util.HashMap;
import java.util.Map;

public class Jan19 {
/*
给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
示例 1：
输入：nums = [1,2,3,1], k = 3
输出：true

示例 2：
输入：nums = [1,0,1,1], k = 1
输出：true

示例 3：
输入：nums = [1,2,3,1,2,3], k = 2
输出：false
 
提示：
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
0 <= k <= 10^5

*/
	
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
		if (nums == null || nums.length < 2) {
			return false;
		}

		for (int idx1 = 0; idx1 < nums.length; idx1++) {
			for (int idx2 = 0; idx2 < nums.length; idx2++) {
				if (idx1 == idx2) {
					continue;
				} else {
					if (nums[idx1] == nums[idx2]) {
						if (Math.abs(idx1 - idx2) <= k) {
							return true;
						}
					}
				}
			}
		}
		return false;
    }
	
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
		if (nums == null || nums.length < 2) {
			return false;
		}

		if (k >= nums.length) {
			k = nums.length;
		}

		for (int idx1=0; idx1 < nums.length; idx1++) {
			for (int idx2 = idx1 - k; idx2 <= idx1 + k && idx2 < nums.length; idx2++) {
				if (idx1 == idx2 || idx2 < 0) {
					continue;
				} else {
					System.out.println("compare nums[" + idx1 + "]=" + nums[idx1] + " with nums[" + idx2 + "]=" + nums[idx2]);
					if (nums[idx1] == nums[idx2]) {
						return true;
					}
				}
			}
		}

		return false;
    }
    
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer index = map.get(nums[i]);
			if (index != null && i - index <= k) {
				return true;
			}
			map.put(nums[i], i);
		}
		return false;
	}
    
	public static void main(String[] args) {
		Jan19 jan19 = new Jan19();
		int[] nums = { 1, 2, 3, 1 };
		int k = 4;
		System.out.println("Expect: true, Real:" + jan19.containsNearbyDuplicate(nums, k));

		nums = new int[] { 1, 0, 1, 1 };
		k = 1;
		System.out.println("Expect: true, Real:" + jan19.containsNearbyDuplicate(nums, k));

		nums = new int[] { 1, 2, 3, 1, 2, 3 };
		k = 2;
		System.out.println("Expect: false, Real:" + jan19.containsNearbyDuplicate(nums, k));

		nums = new int[] { 99, 99 };
		k = 2;
		System.out.println("Expect: true, Real:" + jan19.containsNearbyDuplicate(nums, k));
	}
}
