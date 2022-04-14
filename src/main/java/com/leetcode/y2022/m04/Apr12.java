package com.leetcode.y2022.m04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Apr12 {
/*
806. 写字符串需要的行数
我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。
我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。
现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。

示例 1:
输入: 
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
输出: [3, 60]
解释: 
所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
我们需要2个整行和占用60个单位的一行。

示例 2:
输入: 
widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "bbbcccdddaaa"
输出: [2, 4]
解释: 
除去字母'a'所有的字符都是相同的单位10，并且字符串 "bbbcccdddaa" 将会覆盖 9 * 10 + 2 * 4 = 98 个单位.
最后一个字母 'a' 将会被写到第二行，因为第一行只剩下2个单位了。
所以，这个答案是2行，第二行有4个单位宽度。
 
注:
字符串 S 的长度在 [1, 1000] 的范围。
S 只包含小写字母。
widths 是长度为 26的数组。
widths[i] 值的范围在 [2, 10]。

*/
	
    public int[] numberOfLines1(int[] widths, String s) {
		int[] ans = new int[2];
		ans[0] = 1;
		for (char arr : s.toCharArray()) {
			ans[1] += widths[arr - 'a'];
			if (ans[1] > 100) {
				ans[1] = widths[arr - 'a'];
				ans[0]++;
			}
		}
		return ans;
    }
	
    public int[] numberOfLines(int[] widths, String s) {
		int[] totalRowNumLastRowCount = new int[2];

		Map<String, Integer> alphabetsOccurenceMap = getAlphabetsOccurenceMap(widths);

		int allowedCountPerRow = 100;
		int currentRowLeftWidth = allowedCountPerRow;
		int currentAlphabetWidth = 0;
		totalRowNumLastRowCount[0] = 0;
		totalRowNumLastRowCount[1] = 0;
		System.out.println();

		for (int idx = 0; idx < s.length(); idx++) {
			currentAlphabetWidth = alphabetsOccurenceMap.get(s.substring(idx, idx + 1));
			System.out.printf("%s-%d currentRowLeftWidth=%d ", s.substring(idx, idx + 1), currentAlphabetWidth, currentRowLeftWidth);
			if (currentAlphabetWidth > currentRowLeftWidth) {
				currentRowLeftWidth = 100;
				totalRowNumLastRowCount[0] = totalRowNumLastRowCount[0] + 1;
				totalRowNumLastRowCount[1] = currentAlphabetWidth;
			} else {
				totalRowNumLastRowCount[1] = totalRowNumLastRowCount[1] + currentAlphabetWidth;
			}
			currentRowLeftWidth = currentRowLeftWidth - currentAlphabetWidth;
			System.out.println(Arrays.toString(totalRowNumLastRowCount));
		}

		if (totalRowNumLastRowCount[1] != 0) {
			totalRowNumLastRowCount[0] = totalRowNumLastRowCount[0] + 1;
		}
		return totalRowNumLastRowCount;
    }
	
	private Map<String, Integer> getAlphabetsOccurenceMap(int[] widths) {
		Map<String, Integer> alphabetsOccurenceMap = new HashMap<String, Integer>();

		int alphabetIntNum = 97;
		System.out.printf("Alphabet width: ");
		for (int idx = 0; idx < widths.length; idx++, alphabetIntNum++) {
			System.out.printf("%s-%d ", (char) alphabetIntNum, widths[idx]);
			alphabetsOccurenceMap.put((char) alphabetIntNum + "", widths[idx]);
		}
		return alphabetsOccurenceMap;
    }
    
	public static void main(String[] args) {
		Apr12 apr12 = new Apr12();
		
		int[] widths = new int[] {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
		String s = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(Arrays.toString(apr12.numberOfLines(widths, s)));
		
		widths = new int[] {4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
		s = "bbbcccdddaaa";
		System.out.println(Arrays.toString(apr12.numberOfLines1(widths, s)));
	}
}
