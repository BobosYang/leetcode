package com.leetcode.y2023.m03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mar17 {
/*
2389. 和有限的最长子序列

给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。

示例 1：
输入：nums = [4,5,2,1], queries = [3,10,21]
输出：[2,3,4]
解释：queries 对应的 answer 如下：
- 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
- 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
- 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。

示例 2：
输入：nums = [2,3,4,5], queries = [1]
输出：[0]
解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。

提示：
n == nums.length
m == queries.length
1 <= n, m <= 1000
1 <= nums[i], queries[i] <= 106
 */
	
	/**
	 * 
	 * @param nums
	 * @return
	 */
	public int[] answerQueries(int[] nums, int[] queries) {
		int[] answer = new int[queries.length];

		int[] sortedNums = bubbleSort(nums);
//		System.out.printf("Sorted nums: %s\n", Arrays.toString(sortedNums));

		int subSeqMaxLength = 0;
		int query = 0;
		boolean answerExisted = false;
		for (int i = 0; i < queries.length; i++) {
//			System.out.printf("queries[%d]=%d\n", i, queries[i]);

			subSeqMaxLength = 0;
			query = queries[i];
			for (int j = 0; j < sortedNums.length; j++) {
				query = query - sortedNums[j];
				if (query < 0) {
					break;
				} else {
					subSeqMaxLength++;
				}
			}
			if(subSeqMaxLength>0) {
				answerExisted = true;
				answer[i]=subSeqMaxLength;
			} else {
				answer[i]=0;
			}
		}

		if(answerExisted) {
			return answer;
		} else {
			return new int[] {0};
		}
	}
	
	public int[] bubbleSort(int[] nums) {
		int[] sortedNums = Arrays.copyOf(nums, nums.length);
		
		int swap = 0;
		for (int i = 0; i < sortedNums.length - 1; i++) {
			for (int j = 0; j < sortedNums.length - i - 1; j++) {
				if (sortedNums[j] > sortedNums[j + 1]) {
					swap = sortedNums[j];
					sortedNums[j] = sortedNums[j + 1];
					sortedNums[j + 1] = swap;
				}
			}
		}

		return sortedNums;
	}

	public static void main(String[] args) {
		Mar17 mar17 = new Mar17();

		int[] nums = new int[] { 4, 5, 2, 1 };
		int[] queries = new int[] { 3, 10, 21 };
		int[] answers = mar17.answerQueries(nums, queries);
		System.out.printf("answerQueries(%s, %s)=%s", Arrays.toString(nums), Arrays.toString(queries),
				Arrays.toString(answers));
		
		nums = new int[] { 2,3,4,5 };
		queries = new int[] { 1 };
		answers = mar17.answerQueries(nums, queries);
		System.out.printf("answerQueries(%s, %s)=%s", Arrays.toString(nums), Arrays.toString(queries),
				Arrays.toString(answers));
	}
}
