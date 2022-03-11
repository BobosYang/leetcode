package com.leetcode.y2022.m01;

public class Jan22 {
/*
删除回文子序列

给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
返回删除给定字符串中所有字符（字符串为空）的最小删除次数。

「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。

示例 1：
输入：s = "ababa"
输出：1
解释：字符串本身就是回文序列，只需要删除一次。

示例 2：
输入：s = "abb"
输出：2
解释："abb" -> "bb" -> "". 
先删除回文子序列 "a"，然后再删除 "bb"。

示例 3：
输入：s = "baabb"
输出：2
解释："baabb" -> "b" -> "". 
先删除回文子序列 "baab"，然后再删除 "b"。

提示：
1 <= s.length <= 1000
s 仅包含字母 'a'  和 'b'

 */

//		public int removePalindromeSub(String s) {
//				int minRemoveCount = 0 ;
//				
//				String leftString =  s;
//				int start = 0;
//				int end = leftString.length();
//				String subString;
//				while(leftString.length()>0) {
//					subString = leftString.substring(start, end);
//					System.out.println("subString:" + subString);
//					if(isPalindrome(subString)) {
//						System.out.println("Palindrome:" + subString);
//						System.out.println("====================");
//						leftString = leftString.substring(end, leftString.length());
//						start = 0;
//						end = leftString.length();
//						minRemoveCount++;
//					} else {
//						end--;
//					}
//				}
//				
//				return minRemoveCount;
//		}
//		
//		public boolean isPalindrome(String s) {
//			if(s!=null) {
//				if(s.length()==1 || s.equals(new StringBuffer(s).reverse().toString())) {
//					return  true;
//				}
//			}
//			return false;
//		}
		
	
    boolean isPalindrome(String s){
		int i = 0, j = s.length() - 1;
		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
    }
    
    int removePalindromeSub(String s) {
        if(isPalindrome(s)) {
        	return 1;
        } else {
        	return 2;
        }
    }
    
		public static void main(String[] args) {
			Jan22 jan22 = new Jan22();
//			jan22.removePalindromeSub("ababa");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			jan22.removePalindromeSub("abb");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			jan22.removePalindromeSub("baabb");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			jan22.removePalindromeSub("bbaabaaa");
		}
}
