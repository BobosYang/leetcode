package com.leetcode.y2022.m02;

import java.util.ArrayList;
import java.util.List;

public class Feb15 {
	/*
	给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
	幸运数是指矩阵中满足同时下列两个条件的元素：
		* 在同一行的所有元素中最小
		* 在同一列的所有元素中最大
	 


	示例 1：
	输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
	输出：[15]
	解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。

	示例 2：
	输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
	输出：[12]
	解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。

	示例 3：
	输入：matrix = [[7,8],[1,2]]
	输出：[7]

	提示：
	m == mat.length
	n == mat[i].length
	1 <= n, m <= 50
	1 <= matrix[i][j] <= 10^5
	矩阵中的所有元素都是不同的
	通过次数48,186提交次数62,459

	*/
		
	public List<Integer> luckyNumbers(int[][] matrix) {
		List<Integer> luckyNumbers = new ArrayList<Integer>();

		int row = 0;
		int column = 0;
		for (int i = 0; i < matrix.length; i++) {
			// find min num & column index
			column = findMinNumColumnIndex(matrix[i]);

			// find row index of max num in specified column
			row = findMaxNumRowIndex(matrix, column);
			if (i == row) {
				luckyNumbers.add(matrix[i][column]);
			}
		}

		return luckyNumbers;
	}

	private int findMaxNumRowIndex(int[][] matrix, int column) {
		int row = 0;
		if (matrix.length == 1) {
			row = 0;
		} else {
			for (int i = 1; i < matrix.length; i++) {
				if (matrix[row][column] < matrix[i][column]) {
					row = i;
				}
			}
		}
		return row;
	}

	private int findMinNumColumnIndex(int[] matrix) {
		int column = 0;
		if (matrix.length == 1) {
			column = 0;
		} else {
			for (int j = 1; j < matrix.length; j++) {
				if (matrix[column] > matrix[j]) {
					column = j;
				}
			}
		}
		return column;
	}

	public static void main(String[] args) {
		Feb15 feb15 = new Feb15();

		int[][] matrix = new int[][] { { 3, 7, 8 }, { 9, 11, 13 }, { 15, 16, 17 } };
		System.out.println(feb15.luckyNumbers(matrix));

		matrix = new int[][] { { 1, 10, 4, 2 }, { 9, 3, 8, 7 }, { 15, 16, 17, 12 } };
		System.out.println(feb15.luckyNumbers(matrix));

		matrix = new int[][] { { 7, 8 }, { 1, 2 } };
		System.out.println(feb15.luckyNumbers(matrix));
	}
}
