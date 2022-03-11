package com.leetcode.y2022.m03;

public class Mar07 {
/*
给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
示例 1:
输入: num = 100
输出: "202"

示例 2:
输入: num = -7
输出: "-10"
 
提示：
-10^7 <= num <= 10^7

 * */
    public String convertToBase7(int num) {
//    	int pos = 0;
//
//    	while()
//    	currentNum = currentNum / 7;
//    	System.out.println(currentNum);
//    	currentNum = currentNum / 7;
//    	System.out.println(currentNum);
//    	currentNum = currentNum / 7;
//    	System.out.println(currentNum);
    	
//    	int power = 0;
//		while(Math.pow(7, power) <= num) {
//			power++;
//		}
//		power--;
		if (num == 0) {
			return "0";
		}
		StringBuilder result = new StringBuilder();

		int currentNum = Math.abs(num);
		int divided = 0;
		while (currentNum > 0) {
			divided = currentNum % 7;
			currentNum = currentNum / 7;
			result.append(divided);
		}

		if (num < 0) {
			result.append("-");
		}
		return result.reverse().toString();
    }
    
    private String convertToBase7_1(int num) {
    	return Integer.toString(num, 7);
    }
    
    public static void main(String[] args) {
    	int num = 100;
//    	System.out.println(Integer.toBinaryString(num));
//    	System.out.println(Integer.toHexString(num));
//    	System.out.println(Integer.toOctalString(num));
    	
//    	System.out.println(Math.pow(7, 3));
    	Mar07 mar07 = new Mar07();
    	System.out.print("100的7进制=");
    	System.out.println(mar07.convertToBase7(num));
    	
    	System.out.println(mar07.convertToBase7_1(num));
    } 
}
