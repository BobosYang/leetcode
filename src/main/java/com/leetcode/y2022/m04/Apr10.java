package com.leetcode.y2022.m04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Apr10 {
/*
804. 唯一摩尔斯密码词
国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如:
'a' 对应 ".-" ，
'b' 对应 "-..." ，
'c' 对应 "-.-." ，以此类推。
为了方便，所有 26 个英文字母的摩尔斯密码表如下：
[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。

例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。
对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。

示例 1：
输入: words = ["gin", "zen", "gig", "msg"]
输出: 2
解释: 
各单词翻译如下:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."

共有 2 种不同翻译, "--...-." 和 "--...--.".

示例 2：
输入：words = ["a"]
输出：1

提示：
1 <= words.length <= 100
1 <= words[i].length <= 12
words[i] 由小写英文字母组成

*/
	
	private static final String[] MORSE_REPS = new String[] { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
			"..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--",
			"-..-", "-.--", "--.." };

	private static final String[] ALPHABETS = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

	private static final Map<String, String> alphabetMorseRepMap = new HashMap<String, String>();

	static {
//		for (int num = 97; num <= 122; num++) {
//		System.out.print("\"" + (char) num + "\",");
//	}

		for (int idx = 0; idx < ALPHABETS.length; idx++) {
//			System.out.printf("%s(%s)", MORSE_REPS[idx], ALPHABETS[idx]);
//			System.out.println();
			alphabetMorseRepMap.put(ALPHABETS[idx], MORSE_REPS[idx]);
		}
	}

	public int uniqueMorseRepresentations(String[] words) {
		Map<String, List<String>> morseWordMap = new HashMap<String, List<String>>();

		String morseRep = null;
		for (String word : words) {
			morseRep = getMorseRepresentation(word);
			if(!morseWordMap.containsKey(morseRep)) {
				morseWordMap.put(morseRep, new ArrayList<String>());
			}
			morseWordMap.get(morseRep).add(word);
		}
		return morseWordMap.size();
	}

	private String getMorseRepresentation(String word) {
		StringBuilder morseRepresentation = new StringBuilder();
		for (int idx = 0; idx < word.length(); idx++) {
//			System.out.printf("%s(%s)", word.substring(idx, idx + 1),
//					alphabetMorseRepMap.get(word.substring(idx, idx + 1)));
			morseRepresentation.append(alphabetMorseRepMap.get(word.substring(idx, idx + 1)));
		}
//		System.out.println();
		return morseRepresentation.toString();
	}

	public static void main(String[] args) {
		Apr10 apr10 = new Apr10();

		String[] words = new String[] { "gin", "zen", "gig", "msg" };
		System.out.println(apr10.uniqueMorseRepresentations(words));

		words = new String[] { "a" };
		System.out.println(apr10.uniqueMorseRepresentations(words));
	}
}
