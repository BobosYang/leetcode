package com.leetcode.y2022.m01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jan14 {

	/*
		给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
		定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
		请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
		
		示例 1
		输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
		输出: [1,2],[1,4],[1,6]
		解释: 返回序列中的前 3 对数：
		     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
		     
		示例 2:
		输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
		输出: [1,1],[1,1]
		解释: 返回序列中的前 2 对数：
		     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
		
		示例 3:
		输入: nums1 = [1,2], nums2 = [3], k = 3 
		输出: [1,3],[2,3]
		解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
		 
		
		提示:
		1 <= nums1.length, nums2.length <= 10^4
		-10^9 <= nums1[i], nums2[i] <= 10^9
		nums1, nums2 均为升序排列
		1 <= k <= 1000
	 */
	public List<List<Integer>> findMinSumCombination1(int[] nums1, int[] nums2, int k) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
			return null;
		}

//		log.info("Before sorted, nums1:{}", nums1);
//		nums1 = Arrays.stream(nums1).sorted().toArray();
//		log.info("AFter sorted, nums1:{}", nums1);

		List<List<Integer>> smallestPairs = new ArrayList<List<Integer>>();
		Map<Integer, List<Integer>> sumIndexPairsMap = new HashMap<Integer, List<Integer>>();
		List<PairSum> sumList = new ArrayList<PairSum>(nums1.length * nums2.length);
		Integer sum = null;
		Integer sumIndex = 1;
		for (int idx1 = 0; idx1 < nums1.length; idx1++) {
			for (int idx2 = 0; idx2 < nums2.length; idx2++) {
				sum = Integer.valueOf(Math.addExact(nums1[idx1], nums2[idx2]));
//				sumIndex = Integer.valueOf(Math.addExact(idx1, idx2));
				sumList.add(new PairSum(sum, sumIndex));

				sumIndexPairsMap.put(sumIndex, Stream.of(Integer.valueOf(nums1[idx1]), Integer.valueOf(nums2[idx2]))
						.collect(Collectors.toList()));
//				log.info("{} + {} = {} (sumIndex={})", nums1[idx1], nums2[idx2], sum, sumIndex);
				sumIndex++;
			}
		}

//		sumList = sumList.stream().sorted().collect(Collectors.toList());
		if(k>=sumList.size()) {
			sumList = sumList.stream().sorted().collect(Collectors.toList());
		}else {
			sumList = sumList.stream().sorted().collect(Collectors.toList()).subList(0, k);
		}
//		log.info("==================================================");
//		for (PairSum ps : sumList) {
//			log.info(ps.toString() + "   " + sumIndexPairsMap.get(ps.getSumIndex()).toString());
//		}
		for (PairSum ps : sumList) {
			smallestPairs.add(sumIndexPairsMap.get(ps.getSumIndex()));
		}
		return smallestPairs;
	}

	class PairSum implements Comparable<PairSum> {
		private Integer sum;
		private Integer sumIndex;

		PairSum(Integer sum, Integer sumIndex) {
			this.sum = sum;
			this.sumIndex = sumIndex;
		}

		public Integer getSum() {
			return sum;
		}

		public void setSum(Integer sum) {
			this.sum = sum;
		}

		public Integer getSumIndex() {
			return sumIndex;
		}

		public void setSumIndex(Integer sumIndex) {
			this.sumIndex = sumIndex;
		}

		@Override
		public int compareTo(PairSum o) {
			if (o == null || o.getSum() == null) {
				return 1;
			}
			if (this.getSum() == null) {
				return -1;
			}
			return this.getSum().compareTo(o.getSum());
		}

		@Override
		public String toString() {
			return "sum=" + sum + ", sumIndex=" + sumIndex;
		}
	}

	public List<List<Integer>> findMinSumCombination(int[] nums1, int[] nums2, int k) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
			return null;
		}

//		log.info("Before sorted, nums1:{}", nums1);
//		nums1 = Arrays.stream(nums1).sorted().toArray();
//		log.info("AFter sorted, nums1:{}", nums1);

		int len = Math.min(k, nums1.length * nums2.length);
		List<List<Integer>> smallestPairs = new ArrayList<List<Integer>>(len);

		Queue<Integer> sumQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		Integer sum;
		Integer sumIndex = 0;
		Integer count = 0;
		Integer removeSum;
		Map<Integer, Integer> sumSumIndexMap = new HashMap<Integer, Integer>(len);
		Map<Integer, List<Integer>> sumIndexPairMap = new HashMap<Integer, List<Integer>>(len);
		for (int idx1 = 0; idx1 < nums1.length; idx1++) {
			for (int idx2 = 0; idx2 < nums2.length; idx2++) {
				sum = Math.addExact(nums1[idx1], nums2[idx2]);
				sumQueue.add(sum);
				sumIndex++;
				count++;
				sumSumIndexMap.put(sum, sumIndex);
				sumIndexPairMap.put(sumIndex, Arrays.asList(nums1[idx1], nums2[idx2]));
				if (count > len) {
					removeSum = sumQueue.poll();
					sumIndexPairMap.remove(sumSumIndexMap.get(removeSum)) ;
					sumSumIndexMap.remove(removeSum);
					count--;
				}
			}
		}
		
//		sumQueue.stream().toArray()
		log.info("=============================================");
		while(!sumQueue.isEmpty()) {
//			log.info(sumQueue.poll().toString());
//			log.info(sumIndexPairMap.get(sumSumIndexMap.get(sumQueue.poll())).toString());
			smallestPairs.add(sumIndexPairMap.get(sumSumIndexMap.get(sumQueue.poll())));
		}
		Collections.reverse(smallestPairs);
		return smallestPairs;
	}
	
	public static void main(String[] args) {
//		int[] nums1 = new int[] { 1, 7, 11 };
//		int[] nums2 = new int[] { 2, 4, 6 };
//		int k = 3;
//		int[][] target = { { 1, 2 }, { 1, 4 }, { 1, 6 } };

//		int[] nums1 = new int[] { 1, 1, 2 };
//		int[] nums2 = new int[] { 1, 2, 3 };
//		int k = 2;
//		int[][] target = { { 1, 1 }, { 1, 1 } };
		
//		int[] nums1 = new int[] { 1,2 };
//		int[] nums2 = new int[] {  3 };
//		int k = 3;
//		int[][] target = { { 1, 3 }, { 2, 3 } };

		int[] nums1 = new int[10000];
		int[] nums2 = new int[10000];
		for (int i = 0; i < 10000; i++) {
			nums1[i] = i + 1;
			nums2[i] = i + 1;
		}
		int k = 1000;
		int[][] target = { { 1, 3 }, { 2, 3 } };
		
//		for (int i = 0; i < nums1.length; i++) {
//			System.out.println(nums2[i]); 
//		}
		
		Jan14 jan14 = new Jan14();
		List<List<Integer>> smallestPairs = jan14.findMinSumCombination(nums1, nums2, k);
		log.info("========================print result:==========================");
		for (List<Integer> pair : smallestPairs) {
			log.info(pair.toString());
		}

//		log.info("========================validate result:==========================");
//		if (smallestPairs != null && target.length == smallestPairs.size()) {
//			for (int idx = 0; idx < smallestPairs.size(); idx++) {
//				log.info("result array:{}" + Arrays.toString(target[idx]));
//				log.info("target array:{}" + Arrays.toString(smallestPairs.get(idx).stream().mapToInt(Integer::intValue).toArray()));
//				System.out.println(Arrays.equals( smallestPairs.get(idx).stream().mapToInt(Integer::intValue).toArray(), target[idx]));
//			}
//		}
	}
}
