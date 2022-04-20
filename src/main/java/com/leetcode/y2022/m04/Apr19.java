package com.leetcode.y2022.m04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Apr19 {
	/**
		821. 字符的最短距离
		给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
		返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
		两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
		
		示例 1：
		输入：s = "loveleetcode", c = "e"
		输出：[3,2,1,0,1,0,0,1,2,2,1,0]
		解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
		距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
		距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
		对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
		距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
		
		示例 2：
		输入：s = "aaab", c = "b"
		输出：[3,2,1,0]
		 
		提示：
		1 <= s.length <= 10^4
		s[i] 和 c 均为小写英文字母
		题目数据保证 c 在 s 中至少出现一次
	
	*/

	/**
	 * 参考1
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	public int[] shortestToChar1(String s, char c) {
		char ch[] = s.toCharArray();
		int ans[] = new int[ch.length];
		Arrays.fill(ans, ch.length + 5);
		for (int i = 0; i < ch.length; i++) {
			int j = i;
			while (j >= 0 && ch[j] != c) {
				j--;
			}
			if (j >= 0) {
				ans[i] = i - j;
			}
			j = i;
			while (j < ch.length && ch[j] != c) {
				j++;
			}
			if (j < ch.length) {
				ans[i] = Math.min(ans[i], j - i);
			}
		}
		return ans;
	}

	/**
	 * 参考2
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	public int[] shortestToChar2(String s, char c) {
		int idx[] = new int[10005];
		idx[0] = -10005;
		int p = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				idx[p] = i;
				p++;
			}
		}
		idx[p] = 20010;
		int[] ans = new int[s.length()];
		for (int i = 0; i < ans.length; i++) {
			int l = 1, r = p;
			while (l < r) {
				int mid = (l + r) >> 1;
				if (idx[mid] < i) {
					l = mid + 1;
				} else {
					r = mid;
				}
				if (l == r - 1) {
					if (idx[l] >= i) {
						r = l;
					}
					break;
				}
			}
			ans[i] = Math.min(idx[r] - i, i - idx[r - 1]);
		}
		return ans;
	}

	/**
	 * 参考3
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	public int[] shortestToChar3(String s, char c) {
		char ch[] = s.toCharArray();
		int ans[] = new int[ch.length];
		int idx = -10005;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == c) {
				idx = i;
			}
			ans[i] = i - idx;
		}
		idx = 20010;
		for (int i = ch.length - 1; i >= 0; i--) {
			if (ch[i] == c) {
				idx = i;
			}
			ans[i] = Math.min(ans[i], idx - i);
		}
		return ans;
	}

	public int[] shortestToChar(String s, char c) {
		int[] shortestToTarget = new int[s.length()];
		List<Integer> targetIndexes = new ArrayList<Integer>();
		for (int idx = 0; idx < s.length(); idx++) {
			if (c == s.charAt(idx)) {
				targetIndexes.add(idx);
			}
		}

		for (int idx = 0; idx < s.length(); idx++) {
			shortestToTarget[idx] = findShortest(idx, targetIndexes);
		}
		return shortestToTarget;
	}

	private int findShortest(int idx, List<Integer> targetIndexes) {
		int shortest = Math.abs(idx - targetIndexes.get(0));
		for (int targetIndexesIdx = 1; targetIndexesIdx < targetIndexes.size(); targetIndexesIdx++) {
			shortest = Math.min(shortest, Math.abs(idx - targetIndexes.get(targetIndexesIdx)));
		}
		return shortest;
	}

	public static void main(String[] args) {
		Apr19 apr19 = new Apr19();

		String s = "loveleetcode";
		char c = 'e';
		System.out.printf("s=\"%s\", c='%c'\n", s, c);
		System.out.println(Arrays.toString(apr19.shortestToChar(s, c)));

		s = "aaab";
		c = 'b';
		System.out.printf("s=\"%s\", c='%c'\n", s, c);
		System.out.println(Arrays.toString(apr19.shortestToChar(s, c)));
	}
}
