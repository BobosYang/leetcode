package com.leetcode.y2022.m04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Apr17 {
/*


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
