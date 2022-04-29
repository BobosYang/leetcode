package com.leetcode.y2022.m03;

public class Mar03 {

	/**
		258. 各位相加
		给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
		
		示例 1:
		输入: num = 38
		输出: 2 
		解释: 各位相加的过程为：
		38 --> 3 + 8 --> 11
		11 --> 1 + 1 --> 2
		由于 2 是一位数，所以返回 2。
		
		示例 2:
		输入: num = 0
		输出: 0
		 
		提示：
		0 <= num <= 2^31 - 1
	 */
	
	/**
	 * X = 100*a + 10*b + c = 99*a + 9*b + (a+b+c)
	 * X = 1000*a + 100*b + 10*c + d = 999*a + 99*b + 9*c + (a + b + c + d)
	 *  所以对9取余即可。
	 * X%9 = Y
	 * (X-1)%9 + 1 = Y
	 */
	public int addDigits(int num) {
		return (num - 1) % 9 + 1;
	}
	
	public int addDigits1(int num) {
		if (num > 9) {
            int result = num % 9;
			return result==0?9:result;
		}
		return num;
	}
	
	public int addDigits0(int num) {
		int sum = num;
		while (sum >= 10) {
			sum = 0;
			do  {
				sum += num % 10;
				num = num / 10;
			} while(num > 0);
			num = sum;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Mar03 mar03 = new Mar03();
		
		int num = 38;
		System.out.printf("addDigits(%d)=%d\n", num, mar03.addDigits(num));

		num = 0;
		System.out.printf("addDigits(%d)=%d\n", num, mar03.addDigits(num));
	}

}
