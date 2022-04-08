package com.leetcode.y2022.m04;

public class Apr07 {
/*
旋转字符串

给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 
例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 
示例 1:
输入: s = "abcde", goal = "cdeab"
输出: true

示例 2:
输入: s = "abcde", goal = "abced"
输出: false
 

提示:
1 <= s.length, goal.length <= 100
s 和 goal 由小写英文字母组成


*/
	
	public boolean rotateString1(String s, String goal) {
		String full = s + s;
		return (full.indexOf(goal) >= 0) && (full.length() >= (full.indexOf(goal) + s.length()))
				&& (goal.equals(full.substring(full.indexOf(goal), full.indexOf(goal) + s.length())));
	}
		
	public boolean rotateString(String s, String goal) {
		for (int rotateCount = 0; rotateCount < goal.length(); rotateCount++) {
			if (goal.equals(rotate(s, rotateCount))) {
				return true;
			}
		}
		return false;
	}
	
	private String rotate(String s, int rotateStepCount) {
		if (rotateStepCount == 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		System.out.printf("s=%s, rotateStepCount=%d, tail=%s, head=%s", s, rotateStepCount, s.substring(rotateStepCount, s.length()),
				s.substring(0, rotateStepCount));
		System.out.println();
		sb.append(s.substring(rotateStepCount, s.length())).append(s.substring(0, rotateStepCount));
		System.out.println(sb.toString());
		return sb.toString();
	}
    
	public static void main(String[] args) {
		Apr07 apr07 = new Apr07();

		String s = "abcde";
		String goal = "cdeab";
		boolean result = false;
		result = apr07.rotateString1(s, goal);
		System.out.printf("rotateString(\"%s\", \"%s\")=", s, goal);
		System.out.println(result);
		System.out.println("-------------------------------------------------------");

		s = "abcde";
		goal = "abced";
		result = apr07.rotateString1(s, goal);
		System.out.printf("rotateString(\"%s\", \"%s\")=", s, goal);
		System.out.println(result);
		System.out.println("-------------------------------------------------------");

		s = "aa";
		goal = "a";
		result = apr07.rotateString1(s, goal);
		System.out.printf("rotateString(\"%s\", \"%s\")=", s, goal);
		System.out.println(result);
		System.out.println("-------------------------------------------------------");
	}
}
