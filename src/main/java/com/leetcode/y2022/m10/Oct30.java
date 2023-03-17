package com.leetcode.y2022.m10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Oct30 {
/*
	784. 字母大小写全排列
	给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
	返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
	
	示例 1：
	输入：s = "a1b2"
	输出：["a1b2", "a1B2", "A1b2", "A1B2"]
	
	示例 2:
	输入: s = "3z4"
	输出: ["3z4","3Z4"]
	 
	提示:
	1 <= s.length <= 12
	s 由小写英文字母、大写英文字母和数字组成
 */
	
	/**
	 * 
	 * @param s
	 */
	public List<String> letterCasePermutation(String s) {
		List<String> permutationlist = new ArrayList<>();
		char[] arr = s.toLowerCase().toCharArray();
		dfs(arr, 0, permutationlist);
		return permutationlist;
	}
	
	private void dfs(char[] arr, int u, List<String> permutationlist) {
		for(int i=0;i<u;i++){
			System.out.print("\t");
		}
		System.out.printf("dfs(%s, %d, %s)\n", Arrays.toString(arr), u, Arrays.toString(permutationlist.toArray()));
		if (u == arr.length) {
			permutationlist.add(String.valueOf(arr));
			return;
		}
	
		// 数字或小写字母递归
		dfs(arr, u + 1, permutationlist);
	
		// 大写递归，然后恢复小写
		if (Character.isLetter(arr[u])) {
			arr[u] = Character.toUpperCase(arr[u]);
			dfs(arr, u + 1, permutationlist);
			arr[u] = Character.toLowerCase(arr[u]);
		}
	}
	
	public static void main(String[] args) {
		Oct30 oct30 = new Oct30();
	
		String s = "A1b2";
		System.out.printf("oct30.letterCasePermutation(\"%s\")=%s\n", s,
				Arrays.toString(oct30.letterCasePermutation(s).toArray()));
	
		s = "3z4";
		System.out.printf("oct30.letterCasePermutation(\"%s\")=%s\n", s,
				Arrays.toString(oct30.letterCasePermutation(s).toArray()));
	}

}
