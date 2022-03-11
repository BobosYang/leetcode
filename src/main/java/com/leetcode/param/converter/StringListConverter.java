package com.leetcode.param.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class StringListConverter extends SimpleArgumentConverter {

	@Override
	protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
		if (List.class.isAssignableFrom(targetType)) {
			return Arrays.stream(((String) source).split("\s*,\s*")).collect(Collectors.toList());
		} else {
			throw new IllegalArgumentException(
					"Conversion from " + source.getClass() + " to " + targetType + " not supported.");
		}
	}

}
