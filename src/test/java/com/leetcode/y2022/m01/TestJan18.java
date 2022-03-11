package com.leetcode.y2022.m01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import com.leetcode.param.converter.StringListConverter;

public class TestJan18 {

	private Jan18 jan18 = new Jan18();

	@ParameterizedTest
	@CsvSource({ "'23:59,00:00', 1", "'00:00,23:59,00:00', 0", "'01:01, 02:01', 60", "'01:01, 02:01, 03:00',59",
			"'01:39,10:26,21:43', 236" })
	public void testCountVowelPermutation(@ConvertWith(StringListConverter.class) List<String> timePoints,
			int minDiffMinutes) {
		assertThat(jan18.findMinDifference(timePoints), is(minDiffMinutes));
	}
}
