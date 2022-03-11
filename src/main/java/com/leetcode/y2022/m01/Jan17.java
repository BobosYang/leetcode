package com.leetcode.y2022.m01;

public class Jan17 {
/*
给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：

字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
每个元音 'a' 后面都只能跟着 'e'
每个元音 'e' 后面只能跟着 'a' 或者是 'i'
每个元音 'i' 后面 不能 再跟着另一个 'i'
每个元音 'o' 后面只能跟着 'i' 或者是 'u'
每个元音 'u' 后面只能跟着 'a'
由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。

示例 1：
输入：n = 1
输出：5
解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。

示例 2：
输入：n = 2
输出：10
解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。

示例 3：
输入：n = 5
输出：68
 

提示：
1 <= n <= 2 * 10^4

 */
	
	private int vowelPermutationCount;
	
	public int countVowelPermutation1(int n) {
		int currentPos = 1;
		String[] vowels = new String[] { "a", "e", "i", "o", "u" };
		String compostion;
		for (int i = 0; i < vowels.length; i++) {
			compostion = "";
			currentVowelCombination(compostion, vowels[i], currentPos, n);
		}
		return vowelPermutationCount;
	}
	
	private void currentVowelCombination(String compostion, String currentVowel, int currentPos, int totalCount) {
		if (currentPos == totalCount) {
//			System.out.println(compostion + currentVowel);
			if((compostion + currentVowel).endsWith("a")) {
				System.out.println(compostion + currentVowel);
			}
			vowelPermutationCount++;
		} else {
			compostion = compostion + currentVowel;
			String[] nextVowels = nextVowel(currentVowel);
			currentPos = currentPos + 1;
			for (int j = 0; j < nextVowels.length; j++) {
				currentVowelCombination(compostion, nextVowels[j], currentPos, totalCount);
			}
		}
	}
	
	private String[] nextVowel(String currentVowel) {
		if ("a".equals(currentVowel)) { // 每个元音 'a' 后面都只能跟着 'e'
			return new String[] { "e" };
		} else if ("e".equals(currentVowel)) { // 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
			return new String[] { "a", "i" };
		} else if ("i".equals(currentVowel)) { // 每个元音 'i' 后面 不能 再跟着另一个 'i'
			return new String[] { "a", "e", "o", "u" };
		} else if ("o".equals(currentVowel)) { // 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
			return new String[] { "i", "u" };
		} else if ("u".equals(currentVowel)) { // 每个元音 'u' 后面只能跟着 'a'
			return new String[] { "a" };
		}
		return null;
	}
	
	/**
	 * 定义a,e,i,o,u分别为以该字母结尾的序列的个数，然后遍历n-1次，每次都更新这五个变量的值
	 * 更新的规则在于，目前长度为n的以a结尾的序列，是由长度为n-1的由e,u和i结尾的序列增加一个a得到的，所以a = e + u + i
	 * 以此类推可得另外五个的
	 * 在每轮更新的时候都取模，比更新完再取模速度快很多
	 */
	public int countVowelPermutation(int n) {
		double modPart = Math.pow(10, 9);
		int MOD = (int) modPart + 7;
		long a = 1, e = 1, i = 1, o = 1, u = 1; // 初始化为长度为1的5种元音结尾序列数均为1
		for (int k = 1; k < n; k++) {
			long a_ = (e + i + u) % MOD; // a前可以为e、i、u
			long e_ = (a + i) % MOD; // e前可以为a、i
			long i_ = (e + o) % MOD; // i前可以为e、o
			long o_ = (i) % MOD; // o前可以为i
			long u_ = (i + o) % MOD; // u前可以为i、o
			a = a_;
			e = e_;
			i = i_;
			o = o_;
			u = u_; // 迭代更新(滚动更新)
		}
		return (int) ((a + e + i + o + u) % MOD);
	}
	
	public static void main(String[] args) {
		Jan17 jan17 = new Jan17();
		System.out.println(jan17.countVowelPermutation(144));
		
		System.out.println(jan17.countVowelPermutation1(2));
		System.out.println("==================================");
		System.out.println(jan17.countVowelPermutation1(3));
		System.out.println("==================================");
		System.out.println(jan17.countVowelPermutation1(4));
		
	}
}
