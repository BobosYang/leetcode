package com.leetcode.y2022.m04;

public class Apr05 {
/*
给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
计算置位位数 就是二进制表示中 1 的个数。
例如， 21 的二进制表示 10101 有 3 个计算置位。
 
示例 1：
输入：left = 6, right = 10
输出：4
解释：
6 -> 110 (2 个计算置位，2 是质数)
7 -> 111 (3 个计算置位，3 是质数)
9 -> 1001 (2 个计算置位，2 是质数)
10-> 1010 (2 个计算置位，2 是质数)
共计 4 个计算置位为质数的数字。

示例 2：
输入：left = 10, right = 15
输出：5
解释：
10 -> 1010 (2 个计算置位, 2 是质数)
11 -> 1011 (3 个计算置位, 3 是质数)
12 -> 1100 (2 个计算置位, 2 是质数)
13 -> 1101 (3 个计算置位, 3 是质数)
14 -> 1110 (3 个计算置位, 3 是质数)
15 -> 1111 (4 个计算置位, 4 不是质数)
共计 5 个计算置位为质数的数字。

提示：
1 <= left <= right <= 10^6
0 <= right - left <= 10^4

*/
	
	public int countPrimeSetBits(int left, int right) {
		int count = 0;
		for (int start = left; start <= right; start++) {
//			System.out.printf("=====%d=====%s===", start, Integer.toBinaryString(start));
//			if (isPrime(countNumOne(Integer.toBinaryString(start)))) {
			if(isPrime(Integer.bitCount(start))) {
				count++;
			}
		}
		return count;
	}
    
	private boolean isPrime(int num) {
	    /*
	     * 质数定义：只有1和它本身两个因数的自然数
	     *
	     * 1. 小于等于1或者是大于2的偶数，直接返回false
	     * 2. 2直接返回true
	     * 3. 从3开始算起(每次加2，截止为输入值的平方根)，每次输入值除以前者，若出现一个能除尽则直接返回false
	     * 4. 全都除不尽，则为质数，返回true
	     * */
		if (num <= 1 || (num > 2 && num % 2 == 0)) {
			return false;
		} else if (num == 2) {
			return true;
		}
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
    
	private int countNumOne(String binaryString) {
		if (binaryString == null || binaryString.length() == 0) {
			return 0;
		}
//		System.out.println("====================");
//		System.out.println(binaryString);
//		System.out.println(binaryString.replace("0", ""));
		return binaryString.replace("0", "").length();
	}
	
	public static void main(String[] args) {
//		System.out.println(Math.sqrt(5));
		
		Apr05 apr05 = new Apr05();
		
		int left=6;
		int right=10;
		System.out.printf("PrimeSetBitsCount=%d", apr05.countPrimeSetBits(left, right));
		System.out.println();
		
		left=10;
		right=15;
		System.out.printf("PrimeSetBitsCount=%d", apr05.countPrimeSetBits(left, right));
		System.out.println();
	}

}
