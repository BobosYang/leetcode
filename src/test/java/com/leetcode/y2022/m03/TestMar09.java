package com.leetcode.y2022.m03;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import com.leetcode.param.converter.IntArrayConverter;

public class TestMar09 {

	private Mar09 mar09 = new Mar09();

	@ParameterizedTest
	@CsvSource({ "'2, 3, 1, 4, 0', 3", "'1, 3, 0, 2, 4', 0" })
	public void testCountVowelPermutation(@ConvertWith(IntArrayConverter.class) int[] nums, int bestRotation) {
		assertThat(mar09.bestRotation(nums), is(bestRotation));
	}
}
