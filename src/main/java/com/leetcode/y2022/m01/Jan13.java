package com.leetcode.y2022.m01;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jan13 {
	/**
		给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
		请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
		
		示例 1：
		输入：nums = [3,6,1,0]
		输出：1
		解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
		
		示例 2：
		输入：nums = [1,2,3,4]
		输出：-1
		解释：4 没有超过 3 的两倍大，所以返回 -1 。
		
		示例 3：
		输入：nums = [1]
		输出：0
		解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
		 
		
		提示：
		1 <= nums.length <= 50
		0 <= nums[i] <= 100
		nums 中的最大元素是唯一的
	 */
	public static int findMaxMoreThanDouble(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		if (nums.length == 1) {
			return 0;
		}

		int current;
		int maxNum = nums[0];
		int maxSecondNum = nums[0];
		int smallerNum = nums[0];
		int targetIndex = 0;
		for (int idx = 1; idx < nums.length; idx++) {
			current = nums[idx];
			log.info("maxNum={}, current={}", maxNum, current);
			if (current > maxNum) {
				smallerNum = maxNum;
				maxNum = current;
				targetIndex = idx;
			} else if (current <= maxNum) {
				smallerNum = current;
			}
			
			if (smallerNum > maxSecondNum) {
				maxSecondNum = smallerNum;
			}
			if(idx==1) {
				maxSecondNum =  smallerNum;
			}else {
				if (smallerNum > maxSecondNum) {
					maxSecondNum = smallerNum;
				}
			}
			log.info("==> max={}", maxNum);
		}
		log.info("maxNum={}", maxNum);
		log.info("maxNumIndex={}", targetIndex);
		log.info("maxSecondNum={}", maxSecondNum);
		
		if(maxNum >= (maxSecondNum*2)) {
			return targetIndex;
		}

		return -1;
	}
	
	public static int findMaxMoreThanDouble1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		if (nums.length == 1) {
			return 0;
		}
		int maxNum = Arrays.stream(nums).max().getAsInt();
		int secondMaxNum;
		if (Arrays.stream(nums).filter(e -> e != maxNum).max().isPresent()) {
			secondMaxNum = Arrays.stream(nums).filter(e -> e != maxNum).max().getAsInt();
		} else {
			secondMaxNum = maxNum;
		}
		log.info("maxNum={}", maxNum);
		log.info("secondMaxNum={}", secondMaxNum);
		if (maxNum >= (secondMaxNum * 2)) {
			for(int idx=0;idx<nums.length;idx++) {
				if(nums[idx]==maxNum) {
					return idx;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] { 3, 6, 1, 0 };
//		nums = new int[] {1, 1, 1, 1  };
//		nums = new int[] {1, 0, 0, 0  };

		System.out.println("result="+ findMaxMoreThanDouble(nums));
	}
}
