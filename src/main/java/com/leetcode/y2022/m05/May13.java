package com.leetcode.y2022.m05;

public class May13 {

	/**
		面试题 01.05. 一次编辑
		字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
		
		示例 1:
		输入: 
		first = "pale"
		second = "ple"
		输出: True
		
		示例 2:
		输入: 
		first = "pales"
		second = "pal"
		输出: False 
	 */

	public boolean oneEditAway(String first, String second) {
		// Length difference is greater than 1
		if (Math.abs(first.length() - second.length()) > 1) {
			return false;
		}

		// Equal length
		if (first.length() == second.length()) {
			int diffCharCount = 0;
			for (int idx = 0; idx < first.length(); idx++) {
				if (first.charAt(idx) != second.charAt(idx)) {
					diffCharCount++;
				}
			}
			if (diffCharCount > 1) {
				return false;
			} else {
				return true;
			}
		}

		// Length difference is equal to 1
		if (first.length() > second.length()) {
			return compareLongShort(first, second);
		} else {
			return compareLongShort(second, first);
		}
	}
	
	private boolean compareLongShort(String longStr, String shortStr) {
		int diffCharCount = 0;
		for (int idx = 0, shortIdx=idx; idx < longStr.length() && shortIdx<shortStr.length(); idx++, shortIdx++) {
			if (longStr.charAt(idx) == shortStr.charAt(shortIdx)) {
				continue;
			}
			if (longStr.charAt(idx) != shortStr.charAt(shortIdx)) {
				shortIdx--;
				diffCharCount++;
			}
			if (diffCharCount > 1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		May13 may13 = new May13();

		String first = "pale";
		String second = "ple";
		System.out.printf("first=\"%s\", second=\"%s\"\t", first, second);
		System.out.printf("one edit away=%b\n", may13.oneEditAway(first, second));
		
		first = "pales";
		second = "pal";
		System.out.printf("first=\"%s\", second=\"%s\"\t", first, second);
		System.out.printf("one edit away=%b\n", may13.oneEditAway(first, second));
		
		first = "mart";
		second = "karma";
		System.out.printf("first=\"%s\", second=\"%s\"\t", first, second);
		System.out.printf("one edit away=%b\n", may13.oneEditAway(first, second));
	}

}
