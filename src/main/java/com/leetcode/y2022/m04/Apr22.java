package com.leetcode.y2022.m04;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Apr22 {
	/**
	396. 旋转函数
	给定一个长度为 n 的整数数组 nums 。
	假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
	F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
	返回 F(0), F(1), ..., F(n-1)中的最大值 。
	生成的测试用例让答案符合 32 位 整数。
	
	示例 1:
	输入: nums = [4,3,2,6]
	输出: 26
	解释:
	F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
	F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
	F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
	F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
	所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
	
	示例 2:
	输入: nums = [100]
	输出: 0
	
	提示:
	n == nums.length
	1 <= n <= 10^5
	-100 <= nums[i] <= 100
	 */
	/* 此方法在提交leetcode时会报超时，效率不行*/
    public int maxRotateFunctionTimeOut(int[] nums) {
    	
    	int temp = 0;
    	int[] doubleNums = new int[nums.length*2];
    	System.arraycopy(nums, 0, doubleNums, 0, nums.length);
    	System.arraycopy(nums, 0, doubleNums, nums.length, nums.length);
//    	System.out.println(Arrays.toString(doubleNums));
    	int maxRotateSum = f(nums);
    	for(int start=1; start<nums.length;start++) {
    		temp = f(Arrays.copyOfRange(doubleNums, start, start+nums.length));
    		if(temp > maxRotateSum) {
    			maxRotateSum = temp;
    		}
    	}
    	
    	return maxRotateSum;
    }
    
	public int f(int[] nums) {
		int result = 0;
		for (int idx = 0; idx < nums.length; idx++) {
			result = result + idx * nums[idx];
		}
//		System.out.printf("%s=%d\n", Arrays.toString(nums), result);
		return result;
	}
	
	
	/**
	 	按如下方法宋姐规律：
	 	
		F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
		
		F(0) = 0 * arrk[0] + 1 * arrk[1] + 2 * arrk[2] + 3 * arrk[3] 
		F(1) = 0 * arrk[3] + 1 * arrk[0] + 2 * arrk[1] + 3 * arrk[2]
		F(2) = 0 * arrk[2] + 1 * arrk[3] + 2 * arrk[0] + 3 * arrk[1]
		F(3) = 0 * arrk[1] + 1 * arrk[2] + 2 * arrk[3] + 3 * arrk[0]
		
		
		f(1) - f(0) = (0 * arrk[3] + 1 * arrk[0] + 2 * arrk[1] + 3 * arrk[2]) - (0 * arrk[0] + 1 * arrk[1] + 2 * arrk[2] + 3 * arrk[3] )
		0 * arrk[3] + 1 * arrk[0] + 2 * arrk[1] + 3 * arrk[2]
		                     0 * arrk[0] + 1 * arrk[1] + 2 * arrk[2] + 3 * arrk[3]
		                     1 * arrk[0] + 1 * arrk[1] + 1 * arrk[2] - 3 * arrk[3] = sum - 4 * arrk[3]
		
		f(2) - f(1) = 
		0 * arrk[2] + 1 * arrk[3] + 2 * arrk[0] + 3 * arrk[1]
		                     0 * arrk[3] + 1 * arrk[0] + 2 * arrk[1] + 3 * arrk[2]
		                     1 * arrk[3] + 1 * arrk[0] + 1 * arrk[1] - 3 * arrk[2] = sum - 4 * arrk[2]
		
		f(3) - f(2) = 
		0 * arrk[1] + 1 * arrk[2] + 2 * arrk[3] + 3 * arrk[0]
		                     0 * arrk[2] + 1 * arrk[3] + 2 * arrk[0] + 3 * arrk[1]
		                     1 * arrk[2] + 1 * arrk[3] + 1 * arrk[0] - 3 * arrk[1] = sum - 4 * arrk[1]
		
		总结规律后发现：
		f(n) - f(n-1) = sum - len * arrk[len - n]
		
		最终得出公式：
		f(n) = f(n-1) + sum - len * arrk[len - n]
	 */
	
    public int maxRotateFunction(int[] nums) {
    	int rotateFunctionValue = f(nums);
    	int maxRotateSum = rotateFunctionValue;
    	int sum = IntStream.of(nums).sum();
    	for(int start=1; start<nums.length;start++) {
    		rotateFunctionValue =  rotateFunctionValue + sum - nums.length * nums[nums.length-start];
    		maxRotateSum = Math.max(maxRotateSum, rotateFunctionValue);
    	}
    	return maxRotateSum;
    }
	
	public static void main(String[] args) {
		Apr22 apr22 = new Apr22();
		
		int[] nums = IntStream.of(4,3,2,6).toArray();
		System.out.printf("maxRotateFunction=%d\n", apr22.maxRotateFunction(nums));
		
		nums = IntStream.of(100).toArray();
		System.out.printf("maxRotateFunction=%d\n", apr22.maxRotateFunction(nums));
	}

}
