package com.leetcode.y2022.m04;

public class Apr26 {

	/**
		883. 三维形体投影面积
		
		在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
		每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
		现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
		投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
		返回 所有三个投影的总面积 。
		
		示例 1：
		输入：[[1,2],[3,4]]
		输出：17
		解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
		
		[[1,2],[3,4]]
		
		[1,2]
		[3,4]
		
		a[0][0] = 1
		a[0][1] = 2
		a[1][0] = 3
		a[1][1] = 4
		
		示例 2:
		输入：grid = [[2]]
		输出：5
		
		示例 3：
		输入：[[1,0],[0,2]]
		输出：8
		
		提示：
		n == grid.length == grid[i].length
		1 <= n <= 50
		0 <= grid[i][j] <= 50
	 */
	
	public int projectionArea(int[][] grid) {
		return getXyArea(grid) + getXzArea(grid) + getYzArea(grid);
	}

	private int getXyArea(int[][] grid) {
		int area = 0;
		for (int idx1 = 0; idx1 < grid.length; idx1++) {
			for (int idx2 = 0; idx2 < grid[idx1].length; idx2++) {
//				System.out.printf("%d ", grid[idx1][idx2]);
				if (grid[idx1][idx2] > 0) {
					area++;
				}
			}
//			System.out.println();
		}
		System.out.printf("XyArea=%d\n", area);
		return area;
	}

	private int getXzArea(int[][] grid) {
		int area = 0;
		int maxAreaByX = 0;
		for (int idxX = 0; idxX < grid.length; idxX++) {
			maxAreaByX = 0;
			for (int idxY = 0; idxY < grid[idxX].length; idxY++) {
//				System.out.printf("%d ", grid[idxX][idxY]);
				maxAreaByX = Math.max(maxAreaByX, grid[idxX][idxY]);
//				System.out.printf("maxAreaByX=%d ", maxAreaByX);
			}
			area += maxAreaByX;
//			System.out.println();
		}
//		System.out.printf("XzArea=%d\n", area);
		return area;
	}

	private int getYzArea(int[][] grid) {
		int area = 0;
		int maxAreaByY = 0;
		for (int idxY = 0; idxY < grid.length; idxY++) {
			maxAreaByY = 0;
			for (int idxX = 0; idxX < grid[idxY].length; idxX++) {
//				System.out.printf("%d ", grid[idxX][idxY]);
				maxAreaByY = Math.max(maxAreaByY, grid[idxX][idxY]);
			}
			area += maxAreaByY;
//			System.out.println();
		}
//		System.out.printf("YzArea=%d\n", area);
		return area;
	}

	public static void main(String[] args) {
		Apr26 apr26 = new Apr26();

		int[][] grid = new int[][] { { 1, 2 }, { 3, 4 } };
		System.out.printf("projectionArea=%d\n", apr26.projectionArea(grid));

		grid = new int[][] { { 2 } };
		System.out.printf("projectionArea=%d\n", apr26.projectionArea(grid));

		grid = new int[][] { { 1, 0 }, { 0, 2 } };
		System.out.printf("projectionArea=%d\n", apr26.projectionArea(grid));
		
		grid = new int[][] { { 1,4 }, { 1,1 } };
		System.out.printf("projectionArea=%d\n", apr26.projectionArea(grid));
		
		
	}

}
