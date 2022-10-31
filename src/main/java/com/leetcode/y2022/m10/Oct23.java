package com.leetcode.y2022.m10;

public class Oct23 {

/**
1768. 交替合并字符串
给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。
如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
返回 合并后的字符串 。

示例 1：
输入：word1 = "abc", word2 = "pqr"
输出："apbqcr"
解释：字符串合并情况如下所示：
word1：  a   b   c
word2：    p   q   r
合并后：  a p b q c r

示例 2：
输入：word1 = "ab", word2 = "pqrs"
输出："apbqrs"
解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
word1：  a   b 
word2：    p   q   r   s
合并后：  a p b q   r   s

示例 3：
输入：word1 = "abcd", word2 = "pq"
输出："apbqcd"
解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
word1：  a   b   c   d
word2：    p   q 
合并后：  a p b q c   d

提示：
1 <= word1.length, word2.length <= 100
word1 和 word2 由小写英文字母组成

 */
	
	public String mergeAlternately_v1(String word1, String word2) {
		int word1Length = word1.length();
		int word2Length = word2.length();
		int minLength = Math.min(word1Length, word2Length);
		int maxLength = Math.max(word1Length, word2Length);
		StringBuilder mergeFinal = new StringBuilder();
		int turn = 1;
		int loopCount = 0;
		for (int i = 0; i < maxLength; i++) {
			if (i < minLength) {
				loopCount=0;
				while (loopCount < 2) {
					if (turn == 1) {
						mergeFinal.append(word1.charAt(i));
						turn = 2;
					} else if (turn == 2) {
						mergeFinal.append(word2.charAt(i));
						turn = 1;
					}
					loopCount++;
				}
			} else {
				if (maxLength == word1.length()) {
					mergeFinal.append(word1.charAt(i));
				}
				if (maxLength == word2.length()) {
					mergeFinal.append(word2.charAt(i));
				}
			}
		}
	
		return mergeFinal.toString();
	}
	
	public String mergeAlternately(String word1, String word2) {
		StringBuilder ans = new StringBuilder();
		int pos = 0;
		int maxLength = Math.max(word1.length(), word2.length());
		while (pos < maxLength) {
			if (pos < word1.length()) {
				ans.append(word1.charAt(pos));
			}
			if (pos < word2.length()) {
				ans.append(word2.charAt(pos));
			}
			pos++;
		}
		return ans.toString();
	}
    
	public static void main(String[] args) {
		Oct23 oct23 = new Oct23();
		
		String word1 = "abc";
		String word2 = "pqr";
		System.out.printf("mergeAlternately(%s, %s)=%s\n", word1, word2, oct23.mergeAlternately(word1, word2));

		word1 = "ab";
		word2 = "pqrs";
		System.out.printf("mergeAlternately(%s, %s)=%s\n", word1, word2, oct23.mergeAlternately(word1, word2));

		word1 = "abcd";
		word2 = "pq";
		System.out.printf("mergeAlternately(%s, %s)=%s\n", word1, word2, oct23.mergeAlternately(word1, word2));
	}

}
