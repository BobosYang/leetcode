package com.leetcode.param.converter;

import java.util.Arrays;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class IntArrayConverter extends SimpleArgumentConverter {

	@Override
	protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
		if (int[].class.isAssignableFrom(targetType)) {
			return Arrays.stream(((String) source).split("\s*,\s*")).mapToInt(Integer::parseInt).toArray();
		} else {
			throw new IllegalArgumentException(
					"Conversion from " + source.getClass() + " to " + targetType + " not supported.");
		}
	}

}
