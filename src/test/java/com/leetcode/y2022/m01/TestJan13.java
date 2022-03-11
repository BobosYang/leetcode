package com.leetcode.y2022.m01;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestJan13 {

	@Test
	public void testFindMaxMoreThanDouble1() {
		int[] nums = new int[] { 3, 6, 1, 0 };
		assertThat(Jan13.findMaxMoreThanDouble(nums), is(1));

		nums = new int[] { 3, 6, 1, 0, 68, 24 };
		assertThat(Jan13.findMaxMoreThanDouble(nums), is(4));
	}

	@Test
	public void testFindMaxMoreThanDouble2() {
		int[] nums = new int[] { 1, 2, 3, 4 };
		assertThat(Jan13.findMaxMoreThanDouble(nums), is(-1));
	}

	@Test
	public void testFindMaxMoreThanDouble3() {
		int[] nums = new int[] { 1 };
		assertThat(Jan13.findMaxMoreThanDouble(nums), is(0));

		nums = new int[] { 1, 1, 1, 1 };
		assertThat(Jan13.findMaxMoreThanDouble(nums), is(-1));
	}

	@Test
	public void testFindMaxMoreThanDouble4() {
		int[] nums = new int[] { 1, 0 };
		assertThat(Jan13.findMaxMoreThanDouble(nums), is(0));
	}
}
