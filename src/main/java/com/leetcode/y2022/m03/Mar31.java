package com.leetcode.y2022.m03;

import java.util.ArrayList;
import java.util.List;

public class Mar31 {
/*
自除数 是指可以被它包含的每一位数整除的数。
	例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
自除数 不允许包含 0 。

给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。

示例 1：
输入：left = 1, right = 22
输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]

示例 2:
输入：left = 47, right = 85
输出：[48,55,66,77]

提示：
1 <= left <= right <= 10^4

*/
	
	public List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> selfDivNums = new ArrayList<Integer>();
		List<Integer> allDigits = null;

		int current = left;
		boolean isSelfDividingNumber = true;
		while (current <= right) {
//			System.out.println("=================");
			isSelfDividingNumber = true;
			allDigits = findEachDigit(current);
			if (allDigits != null && allDigits.size() > 0) {
				for (Integer digit : allDigits) {
//					System.out.printf("digit=%d ", digit);
					if (digit == 0 || (current % digit != 0)) {
						isSelfDividingNumber = false;
						break;
					}
				}
			}
			if (isSelfDividingNumber) {
				selfDivNums.add(current);
			}
			current++;
		}
		return selfDivNums;
	}
	
	private List<Integer> findEachDigit(int targetNumber) {
		if (targetNumber == 0) {
			return null;
		}
		List<Integer> allDigits = new ArrayList<Integer>();
		int dividedByTen = targetNumber;
		do {
			allDigits.add(dividedByTen % 10);
			dividedByTen = dividedByTen / 10;
		} while (dividedByTen > 0);
		return allDigits;
	}
	
	public static void main(String[] args) {
		Mar31 mar31 = new Mar31();

		int left = 1;
		int right = 22;
		System.out.printf("------------------------left=%d, right=%d------------------------", left, right);
		System.out.println();
		System.out.println("self dividing numbers: " + mar31.selfDividingNumbers(left, right));

		left = 47;
		right = 85;
		System.out.printf("------------------------left=%d, right=%d------------------------", left, right);
		System.out.println();
		System.out.println("self dividing numbers: " + mar31.selfDividingNumbers(left, right));
	}
}
