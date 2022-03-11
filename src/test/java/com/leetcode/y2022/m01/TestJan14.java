package com.leetcode.y2022.m01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestJan14 {

	private Jan14 jan14 = new Jan14();

	@Test
	public void testFindMinSumCombination1() {
		int[] nums1 = new int[] { 1, 7, 11 };
		int[] nums2 = new int[] { 2, 4, 6 };
		int k = 3;
		int[][] target = { { 1, 2 }, { 1, 4 }, { 1, 6 } };

		List<List<Integer>> smallestPairs = jan14.findMinSumCombination(nums1, nums2, k);
		if (smallestPairs != null && target.length == smallestPairs.size()) {
			for (int idx = 0; idx < smallestPairs.size(); idx++) {
				assertThat(Arrays.equals(smallestPairs.get(idx).stream().mapToInt(Integer::intValue).toArray(),
						target[idx]), is(true));
			}
		} else {
			assertThat(false, is(true));
		}
	}

	@Test
	public void testFindMinSumCombination2() {
		int[] nums1 = new int[] { 1, 1, 2 };
		int[] nums2 = new int[] { 1, 2, 3 };
		int k = 2;
		int[][] target = { { 1, 1 }, { 1, 1 } };
//		int[][] target = { { 1, 3 }, { 2, 4 } };

		List<List<Integer>> smallestPairs = jan14.findMinSumCombination(nums1, nums2, k);
		if (smallestPairs != null && target.length == smallestPairs.size()) {
			for (int idx = 0; idx < smallestPairs.size(); idx++) {
//				System.out.println("result array:{}" + Arrays.toString(target[idx]));
//				System.out.println("target array:{}"
//						+ Arrays.toString(smallestPairs.get(idx).stream().mapToInt(Integer::intValue).toArray()));
				assertThat(Arrays.equals(smallestPairs.get(idx).stream().mapToInt(Integer::intValue).toArray(),
						target[idx]), is(true));
			}
		} else {
			assertThat(false, is(true));
		}
	}

	@Test
	public void testFindMinSumCombination3() {
		int[] nums1 = new int[] { 1, 2 };
		int[] nums2 = new int[] { 3 };
		int k = 3;
		int[][] target = { { 1, 3 }, { 2, 3 } };

		List<List<Integer>> smallestPairs = jan14.findMinSumCombination(nums1, nums2, k);

		if (smallestPairs != null && target.length == smallestPairs.size()) {
			for (int idx = 0; idx < smallestPairs.size(); idx++) {
				assertThat(Arrays.equals(smallestPairs.get(idx).stream().mapToInt(Integer::intValue).toArray(),
						target[idx]), is(true));
			}
		} else {
			assertThat(false, is(true));
		}
	}
}
