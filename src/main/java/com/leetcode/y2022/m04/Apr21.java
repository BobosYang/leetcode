package com.leetcode.y2022.m04;

public class Apr21 {
	/**
		824. 山羊拉丁文
		给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
		
		请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
		
		如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
		例如，单词 "apple" 变为 "applema" 。
		如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
		例如，单词 "goat" 变为 "oatgma" 。
		根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
		例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
		返回将 sentence 转换为山羊拉丁文后的句子。
		
		示例 1：
		输入：sentence = "I speak Goat Latin"
		输出："Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
		
		示例 2：
		输入：sentence = "The quick brown fox jumped over the lazy dog"
		输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
		 
		提示：
		1 <= sentence.length <= 150
		sentence 由英文字母和空格组成
		sentence 不含前导或尾随空格
		sentence 中的所有单词由单个空格分隔
	 */
	
	
	public String toGoatLatin(String sentence) {
		String[] words = sentence.split(" ");
		StringBuilder goatLatinSentence = new StringBuilder();
		for (int idx = 0; idx < words.length; idx++) {
			goatLatinSentence.append(getWordGoatLatin(words[idx], idx)).append(" ");
//			System.out.printf("%s \n", goatLatinSentence);
		}
		return goatLatinSentence.substring(0, goatLatinSentence.length() - 1);
	}
	
	/* 效率 和 内存占用 都比上面的方法都低*/
//	public String toGoatLatin(String sentence) {
//		String[] words = sentence.split(" ");
//		String[] goatLatinSentenceWords = new String[words.length];
//		for (int idx = 0; idx < words.length; idx++) {
//			goatLatinSentenceWords[idx] = getWordGoatLatin(words[idx], idx);
//		}
//		return String.join(" ", goatLatinSentenceWords);
//	}

	private String getWordGoatLatin(String word, int idx) {
		StringBuilder goatLatinWord = new StringBuilder();
		if (isStartedWithVowel(word)) {
			goatLatinWord.append(word).append("ma");
		} else {
			goatLatinWord.append(word.substring(1, word.length())).append(word.substring(0, 1)).append("ma").toString();
		}
		for (int idy = 0; idy <= idx; idy++) {
			goatLatinWord.append("a");
		}
		return goatLatinWord.toString();
	}

//	private boolean isStartedWithVowel(String word) {
//		return Pattern.matches("^[aeiouAEIOU]{1}\\w*", word);
//	}

	private boolean isStartedWithVowel(String word) {
		return word.startsWith("a") || word.startsWith("A") || word.startsWith("e") || word.startsWith("E")
				|| word.startsWith("i") || word.startsWith("I") || word.startsWith("o") || word.startsWith("O")
				|| word.startsWith("u") || word.startsWith("U");
	}

	public static void main(String[] args) {
		Apr21 apr21 = new Apr21();

		String sentence = "I speak Goat Latin";
		System.out.printf("Origin=%s\n", sentence);
		System.out.printf("GoatLatin=%s\n", apr21.toGoatLatin(sentence));
		System.out.println("----------------------------------------------------------------------------------");

		sentence = "The quick brown fox jumped over the lazy dog";
		System.out.printf("Origin=%s\n", sentence);
		System.out.printf("GoatLatin=%s\n", apr21.toGoatLatin(sentence));
		System.out.println("----------------------------------------------------------------------------------");

//		System.out.println(apr21.isStartedWithVowel("apple"));
//		System.out.println(apr21.isStartedWithVowel("egg"));
//		System.out.println(apr21.isStartedWithVowel("icecream"));
//		System.out.println(apr21.isStartedWithVowel("over"));
//		System.out.println(apr21.isStartedWithVowel("undefined"));
//		System.out.println(apr21.isStartedWithVowel("banana"));
	}

}
