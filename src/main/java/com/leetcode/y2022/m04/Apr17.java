package com.leetcode.y2022.m04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Apr17 {
/*
819. 最常见的单词
给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
题目保证至少有一个词不在禁用列表中，而且答案唯一。
禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。

示例：
输入: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
输出: "ball"
解释: 
"hit" 出现了3次，但它是一个禁用的单词。
"ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。 
注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"）， 
"hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。

提示：
1 <= 段落长度 <= 1000
0 <= 禁用单词个数 <= 100
1 <= 禁用单词长度 <= 10
答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
paragraph 只包含字母、空格和下列标点符号!?',;.
不存在没有连字符或者带有连字符的单词。
单词里只包含字母，不会出现省略号或者其他标点符号。

*/
	
//	private static final String punctuation;
//	
//	static {
//		punctuation = findAllPunctuation();
//	}

	public String mostCommonWord(String paragraph, String[] banned) {
		System.out.println("----------paragraph(origin)----------");
		System.out.printf("paragraph=%s", paragraph);
		System.out.println();
	
		String paragraphReplaced = paragraph.toLowerCase();
		System.out.println("----------paragraph(to lower case)----------");
		System.out.printf("paragraph=%s", paragraphReplaced);
		System.out.println();
	
		String punctuation = "[!?',;.]";
		paragraphReplaced = paragraphReplaced.replaceAll(punctuation, " ");
		System.out.println("----------paragraph(removed punctuation)----------");
		System.out.printf("paragraph=%s", paragraphReplaced);
		System.out.println();
	
		paragraphReplaced = paragraphReplaced.replace("  ", " ");
		System.out.println("----------paragraph(removed space)----------");
		System.out.printf("paragraph=%s", paragraphReplaced);
		System.out.println();
	
		String[] words = paragraphReplaced.split(" ");
		System.out.println(Arrays.deepToString(words));
	
		Map<String, Integer> wordOccurCountMap = new HashMap<String, Integer>();
		String mostCommonWord = null;
		int maxOccurCount = 0;
		for (String word : words) {
			if (word == null || word.length() == 0) {
				continue;
			}
	
			if (isBanned(banned, word)) {
				continue;
			}
	
			if (wordOccurCountMap.containsKey(word)) {
				wordOccurCountMap.put(word, wordOccurCountMap.get(word) + 1);
			} else {
				wordOccurCountMap.put(word, 1);
			}
			if (wordOccurCountMap.get(word) > maxOccurCount) {
				maxOccurCount = wordOccurCountMap.get(word);
				mostCommonWord = word;
			}
		}
	
		return mostCommonWord;
	}
	
	private boolean isBanned(String[] banned, String word) {
		for (String ban : banned) {
			if (ban.equalsIgnoreCase(word)) {
				return true;
			}
		}
		return false;
	}
    
    public static String findAllPunctuation() {
//    	for(int i=0;i<=255;i++) {
//    		if(i%5==0) {
//    			System.out.println();
//    		}
//    		System.out.printf("%d:%s ", i, (char)i);
//    	}
    	
		StringBuilder punctuation = new StringBuilder();
		for (int i = 33; i <= 47; i++) {
			punctuation.append((char) i);
		}
		for (int i = 58; i <= 64; i++) {
			punctuation.append((char) i);
		}
		for (int i = 91; i <= 96; i++) {
			punctuation.append((char) i);
		}
		for (int i = 123; i <= 126; i++) {
			punctuation.append((char) i);
		}
		return punctuation.toString();
    }
    
	public static void main(String[] args) {
		Apr17 apr17 = new Apr17();

		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = new String[] { "hit" };
		System.out.printf("most common word=%s", apr17.mostCommonWord(paragraph, banned));
		System.out.println();
		System.out.println();
		
		paragraph = "Bob. hIt, baLl";
		banned = new String[] { "bob", "hit" };
		System.out.printf("most common word=%s", apr17.mostCommonWord(paragraph, banned));
		System.out.println();
		System.out.println();
		
		paragraph = "a, a, a, a, b,b,b,c, c";
		banned = new String[] { "a"};
		System.out.printf("most common word=%s", apr17.mostCommonWord(paragraph, banned));
		System.out.println();
		System.out.println();
		
		paragraph = "abc abc? abcd the jeff!";
		banned = new String[] { "abc","abcd","jeff"};
		System.out.printf("most common word=%s", apr17.mostCommonWord(paragraph, banned));
		System.out.println();
		System.out.println();
	}
}
