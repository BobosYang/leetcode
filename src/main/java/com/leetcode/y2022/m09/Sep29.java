package com.leetcode.y2022.m09;

public class Sep29 {

	/**
		面试题 01.09. 字符串轮转
		字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
		
		示例1:
		 输入：s1 = "waterbottle", s2 = "erbottlewat"
		 输出：True
		 
		示例2:
		 输入：s1 = "aa", s2 = "aba"
		 输出：False
		 
		提示：
		字符串长度在[0, 100000]范围内。
		
		说明:
		你能只调用一次检查子串的方法吗？
	 */
	
	public boolean isFlipedString1(String s1, String s2) {
		String rotateS1 = String.join("", s1, s1);
		System.out.printf("rotated s1=%s\n", rotateS1);
		if(!"".equals(s2)) {
			return (rotateS1.indexOf(s2) >= 0);
		} else {
			if("".equals(s1)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean isFlipedString(String s1, String s2) {
		if(s1.length() == s2.length()) {
			String rotateS1 = String.join("", s1, s1);
			return rotateS1.contains(s2);
		}
		return false;
	}

	public static void main(String[] args) {
		Sep29 sep29 = new Sep29();
		
		String s1 = "waterbottle";
		String s2 = "erbottlewat";
		System.out.printf("isFlipedString(\"%s\", \"%s\")=%b\n", s1, s2, sep29.isFlipedString(s1, s2));

		s1 = "aa";
		s2 = "aba";
		System.out.printf("isFlipedString(\"%s\", \"%s\")=%b\n", s1, s2, sep29.isFlipedString(s1, s2));
		
		s1 = "rxOpSEXvfIuoRJfjwgwuomevMMBOfeSMvYSPBaovrZBSgmCrSLDirNnILhARNShOYIFBHIRiFKHtfxWHjawaLRAEYPIZokUKgiqyZpvcOHdfPpRqHADKAXzEfzhxdXXb";
		s2 = "";
		System.out.printf("isFlipedString(\"%s\", \"%s\")=%b\n", s1, s2, sep29.isFlipedString(s1, s2));
		
		System.out.println("a".indexOf(""));
		System.out.println("a".indexOf("a"));
		System.out.println("a".indexOf("b"));
	}
}
