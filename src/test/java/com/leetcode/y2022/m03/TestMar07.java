package com.leetcode.y2022.m03;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestMar07 {
	private Mar07 mar07 = new Mar07();

	@ParameterizedTest
	@CsvSource({ "100, '202'", "65, 122", "-7, -10" })
	public void testCountVowelPermutation(int num, String base7) {
		assertThat(mar07.convertToBase7(num), is(base7));
	}
}
