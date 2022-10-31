package com.leetcode.y2022.m10;

import java.util.Arrays;

public class Oct27 {

/**
1822. 数组元素积的符号
已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
如果 x 是正数，返回 1 。
如果 x 是负数，返回 -1 。
如果 x 是等于 0 ，返回 0 。
给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
返回 signFunc(product) 。

示例 1：
输入：nums = [-1,-2,-3,-4,3,2,1]
输出：1
解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1

示例 2：
输入：nums = [1,5,0,2,-3]
输出：0
解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0

示例 3：
输入：nums = [-1,1,-1,1,-1]
输出：-1
解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1

提示：
1 <= nums.length <= 1000
-100 <= nums[i] <= 100
 */
	
	public int arraySign_v1(int[] nums) {
		int negativeCount = 0;
		for (int idx = 0; idx < nums.length; idx++) {
			if (nums[idx] == 0) {
				return 0;
			} else if (nums[idx] < 0) {
				negativeCount++;
			} else if (nums[idx] > 0) {
				continue;
			}
		}
	
		if (negativeCount % 2 == 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public int arraySign(int[] nums) {
		int arraySign = 1;
		for (int idx = 0; idx < nums.length; idx++) {
			if (nums[idx] == 0) {
				return 0;
			} else if (nums[idx] < 0) {
				arraySign = arraySign * -1;
			}
		}
		return arraySign;
	}

	public static void main(String[] args) {
		Oct27 oct27 = new Oct27();

		int[] nums = new int[] { -1, -2, -3, -4, 3, 2, 1 };
		System.out.printf("oct27.arraySign(%s)=%d\n", Arrays.toString(nums), oct27.arraySign(nums));

		nums = new int[] { 1, 5, 0, 2, -3 };
		System.out.printf("oct27.arraySign(%s)=%d\n", Arrays.toString(nums), oct27.arraySign(nums));

		nums = new int[] { -1, 1, -1, 1, -1 };
		System.out.printf("oct27.arraySign(%s)=%d\n", Arrays.toString(nums), oct27.arraySign(nums));
	}

}
