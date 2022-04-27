package com.leetcode.y2022.m04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Apr25 {
	/**
		398. 随机数索引
		给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。你可以假设给定的数字一定存在于数组中。
		
		实现 Solution 类：
		Solution(int[] nums) 用数组 nums 初始化对象。
		int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。
		
		示例：
		输入
		["Solution", "pick", "pick", "pick"]
		[[[1, 2, 3, 3, 3]], [3], [1], [3]]
		输出
		[null, 4, 0, 2]
		解释
		Solution solution = new Solution([1, 2, 3, 3, 3]);
		solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
		solution.pick(1); // 返回 0 。因为只有 nums[0] 等于 1 。
		solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
		 
		提示：
		1 <= nums.length <= 2 * 10^4
		-2^31 <= nums[i] <= 2^31 - 1
		target 是 nums 中的一个整数
		最多调用 pick 函数 10^4 次
	 */
	
	private Map<Integer, List<Integer>> dataIndexesMap;

	public Apr25(int[] nums) {
		dataIndexesMap = new HashMap<Integer, List<Integer>>();
		for (int idx = 0; idx < nums.length; idx++) {
			if (dataIndexesMap.containsKey(nums[idx])) {
				dataIndexesMap.get(nums[idx]).add(idx);
			} else {
				dataIndexesMap.put(nums[idx], Stream.of(idx).collect(Collectors.toList()));
			}
		}
	}
    
	public int pick(int target) {
		return dataIndexesMap.get(target).get(new Random().nextInt(dataIndexesMap.get(target).size()));
	}

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */
    
	public static void main(String[] args) {
//		Random random = new Random();
//		for(int i=0;i<100;i++) {
//			System.out.printf("random.nextInt(1)=%d\n", random.nextInt(3));
//		}
		
		int[] nums = IntStream.of(1, 2, 3, 3, 3).toArray();
		
		Apr25 apr25 = new Apr25(nums);
		System.out.printf("nums=%s\n", Arrays.toString(nums));
		
		System.out.printf("pick(%d)=%d\n", 3, apr25.pick(3));
		
		System.out.printf("pick(%d)=%d\n", 1, apr25.pick(1));
		
		System.out.printf("pick(%d)=%d\n", 3, apr25.pick(3));
		
		System.out.printf("pick(%d)=%d\n", 2, apr25.pick(2));
		
		System.out.printf("pick(%d)=%d\n", 1, apr25.pick(1));
	}

}
