package com.leetcode.y2022.m01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestJan17 {

	private Jan17 jan17 = new Jan17();

	@ParameterizedTest
	@CsvSource({ "1, 5", "2, 10", "5, 68" })
	public void testCountVowelPermutation(int n, int count) {
		assertThat(jan17.countVowelPermutation(n), is(count));
	}
	
	@ParameterizedTest
	@CsvSource({ "1, 5", "2, 10", "5, 68" })
	public void testCountVowelPermutation1(int n, int count) {
		assertThat(jan17.countVowelPermutation1(n), is(count));
	}
}
