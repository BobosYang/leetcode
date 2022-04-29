package com.leetcode.y2022.m04;

import java.util.Arrays;

public class Apr28 {

	/**
		905. 按奇偶排序数组
		给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
		返回满足此条件的 任一数组 作为答案。
		
		示例 1：
		输入：nums = [3,1,2,4]
		输出：[2,4,3,1]
		解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
		
		示例 2：
		输入：nums = [0]
		输出：[0]
		
		提示：
		1 <= nums.length <= 5000
		0 <= nums[i] <= 5000
	 */

	/**
		排序，需要循环，判断比较，交换。本题对排序算法的排序条件的掌握，将常规排序条件从比大小变为判断奇偶。
		思路：题目要求偶数在前，奇数在后，那么我选择采取双指针的方法：
		左指针寻找奇数值，右指针寻找偶数值，当符合交换条件时，进行两数交换；
		否则指针继续左右运动，寻找符合条件的奇偶值。
		当两指针相遇时，结束循环。
	 */
	public int[] sortArrayByParity1(int[] A) {
		if (A == null || A.length == 1)
			return A;
		// 左、右指针初始化
		int left = 0;
		int right = A.length - 1;
		int tem;
		while (left < right) {
			// 左指针对应奇数值，右指针对应偶数值，进行交换
			if ((A[left] & 1) == 1 && (A[right] & 1) == 0) {
				tem = A[left];
				A[left] = A[right];
				A[right] = tem;
			}
			while ((A[left] & 1) == 0) { // 左指针对应的是偶数值，符合题意，继续向右移动
				left++;
			}
			while ((A[right] & 1) == 1) { // 右指针对应的是奇数值，符合题意，继续向左移动
				right--;
			}
		}
		return A;
	}
	
	public int[] sortArrayByParity(int[] nums) {
		int[] result = new int[nums.length];

		int evenNumStart = 0;
		int oddNumStart = result.length - 1;
		for (int idx = 0; idx < nums.length; idx++) {
			if (nums[idx] % 2 == 0) {
				result[evenNumStart++] = nums[idx];
			} else {
				result[oddNumStart--] = nums[idx];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Apr28 apr28 = new Apr28();

		int[] nums = new int[] { 3, 1, 2, 4 };
		System.out.printf("sortArrayByParity: %s", Arrays.toString(apr28.sortArrayByParity(nums)));
	}

}
