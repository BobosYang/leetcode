package com.leetcode.y2022.m02;

public class Feb14 {
/*
	给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
	请你找出并返回只出现一次的那个数。
	你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
	
	示例 1:
	输入: nums = [1,1,2,3,3,4,4,8,8]
	输出: 2

	示例 2:
	输入: nums =  [3,3,7,7,10,11,11]
	输出: 10
	 
	提示:
	1 <= nums.length <= 10^5
	0 <= nums[i] <= 10^5

*/

	public int singleNonDuplicate(int[] nums) {
		int target = 0;

		if (nums.length == 1) {
			return nums[0];
		}

		for (int i = 0; i < nums.length - 1; i = i + 2) {
			if (nums[i] != nums[i + 1]) {
				return nums[i];
			}
		}
		return nums[nums.length - 1];

	}

	public int singleNonDuplicate1(int[] nums) {
		int num = 0;
		for (int i : nums)
			num ^= i;

		return num;
	}

	public static void main(String args[]) {
		Feb14 feb14 = new Feb14();

		// singleNonDuplicate
		int[] nums = new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 };
		System.out.println(feb14.singleNonDuplicate(nums));

		nums = new int[] { 1, 1, 2 };
		System.out.println(feb14.singleNonDuplicate(nums));

		nums = new int[] { 3, 3, 7, 7, 10, 11, 11 };
		System.out.println(feb14.singleNonDuplicate(nums));

		// singleNonDuplicate1
		nums = new int[] { 3, 3, 7, 7, 10, 11, 11 };
		System.out.println(feb14.singleNonDuplicate1(nums));
	}
}
