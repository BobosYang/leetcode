package com.leetcode.y2022.m04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Apr27 {

	/**
		417. 太平洋大西洋水流问题
		
		有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
		这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
		岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
		返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
		
		示例 1：
		输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
		输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
		
		示例 2：
		输入: heights = [[2,1],[1,2]]
		输出: [[0,0],[0,1],[1,0],[1,1]]
		 
		提示：
		m == heights.length
		n == heights[r].length
		1 <= m, n <= 200
		0 <= heights[r][c] <= 10^5
	 */

	
	private Map<String, Integer> coordinateAlreadyCheckedMap = new HashMap<String, Integer>();
	
//	public List<List<Integer>> pacificAtlantic(int[][] heights) {
//		for (int idx1 = 0; idx1 < heights.length; idx1++) {
//		for (int idx2 = 0; idx2 < heights[idx1].length; idx2++) {
//			if (canFlowIntoPacific(idx1, idx2) && canFlowIntoAtlantic(idx1, idx2, heights.length, heights[idx1].length)) {
//				// 方式1
//				List<Integer> coordinate = new ArrayList<Integer>();
//				coordinate.add(idx1);
//				coordinate.add(idx2);
//				result.add(coordinate);
//
//				// 方式2
//				result.add(Arrays.asList(idx1, idx2));
//
//				// 方式3
//				result.add(Stream.of(idx1, idx2).collect(Collectors.toList()));
//			}
//		}
//	}
//	}
//	
//	private boolean canFlowIntoPacific(int idx1, int idx2) {
//		// 紧邻Pacific的网格 可流入Pacific
//		if (idx1 == 0 || idx2 == 0) {
//			return true;
//		}
//
//		return false;
//	}
//
//	private boolean canFlowIntoAtlantic(int idx1, int idx2, int horizontalLength, int verticalWidth) {
//		// 紧邻Atlantic的网格 可流入Atlantic
//		if (idx1 == horizontalLength || idx2 == verticalWidth) {
//			return true;
//		}
//
//		return false;
//	}
	
	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		coordinateAlreadyCheckedMap.clear();
		Map<String, List<Integer>> pacificCoordinateMap = getAllGridCanFlowIntoPacific(heights);
//		System.out.printf("All grid can flow into Pacific: %s\n", pacificCoordinateMap);

		coordinateAlreadyCheckedMap.clear();
		Map<String, List<Integer>> atlanticCoordinateMap = getAllGridCanFlowIntoAtlantic(heights);
//		System.out.printf("All grid can flow into Atlantic: %s\n", atlanticCoordinateMap);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (String pacificCoordinateKey : pacificCoordinateMap.keySet()) {
			if (atlanticCoordinateMap.containsKey(pacificCoordinateKey)) {
				result.add(atlanticCoordinateMap.get(pacificCoordinateKey));
			}
		}
		return result;
	}

	private Map<String, List<Integer>> getAllGridCanFlowIntoPacific(int[][] heights) {
		Map<String, List<Integer>> coordinateMap = new HashMap<String, List<Integer>>();

		for (int idx1 = 0; idx1 < heights.length; idx1++) {
//			System.out.printf("%s", "====getAllGridCanFlowIntoPacific===");
			getGridCanFlowIntoCurrentGrid(heights, idx1, 0, coordinateMap);
			coordinateMap.put(idx1 + "-" + 0, Arrays.asList(idx1, 0));
		}
		
		for (int idx2 = 0; idx2 < heights[0].length; idx2++) {
//			System.out.printf("%s", "====getAllGridCanFlowIntoPacific===");
			getGridCanFlowIntoCurrentGrid(heights, 0, idx2, coordinateMap);
			coordinateMap.put(0 + "-" + idx2, Arrays.asList(0, idx2));
		}

		return coordinateMap;
	}
	
	private void getGridCanFlowIntoCurrentGrid(int[][] heights, int idx1, int idx2,
			Map<String, List<Integer>> coordinateMap) {
//		System.out.printf("=====Get all grid can flow into ([%d, %d])=====\n", idx1, idx2);
//		System.out.printf("=====Get all grid can flow into %d([%d, %d])=====\n", heights[idx1][idx2], idx1, idx2);
		if (coordinateAlreadyCheckedMap.containsKey(idx1 + "-" + idx2)) {
//			System.out.printf("Already checked coordinate[%d,%d], will be ignored\n", idx1, idx2);
			return;
		} else {
			coordinateAlreadyCheckedMap.put(idx1 + "-" + idx2, idx1 + idx2);
		}

		// left
//		System.out.printf("\tLeft");
		getAdjacentGridFlowIntoCurrentGrid(heights, idx1, idx2, idx1, idx2 - 1, coordinateMap);

		// up
//		System.out.printf("\tUp");
		getAdjacentGridFlowIntoCurrentGrid(heights, idx1, idx2, idx1 - 1, idx2, coordinateMap);

		// right
//		System.out.printf("\tRight");
		getAdjacentGridFlowIntoCurrentGrid(heights, idx1, idx2, idx1, idx2 + 1, coordinateMap);

		// down
//		System.out.printf("\tDown");
		getAdjacentGridFlowIntoCurrentGrid(heights, idx1, idx2, idx1 + 1, idx2, coordinateMap);
	}

	private void getAdjacentGridFlowIntoCurrentGrid(int[][] heights, int idx1, int idx2, int targetIdx1, int targetIdx2,
			Map<String, List<Integer>> coordinateMap) {
		StringBuilder coordinateXY = new StringBuilder();
//		System.out.printf("[%d, %d] can flow into Current[%d, %d]? ", targetIdx1, targetIdx2, idx1, idx2);
		if (canFlowIntoCurrentGrid(heights, idx1, idx2, targetIdx1, targetIdx2)) {
			coordinateXY.setLength(0);
			coordinateXY.append(targetIdx1).append("-").append(targetIdx2);
			if (!coordinateMap.containsKey(coordinateXY.toString())) {
				coordinateMap.put(coordinateXY.toString(), Arrays.asList(targetIdx1, targetIdx2));
			}
//			System.out.printf("%s", "====getAdjacentGridFlowIntoCurrentGrid===");
			getGridCanFlowIntoCurrentGrid(heights, targetIdx1, targetIdx2, coordinateMap);
		}
	}

	private boolean canFlowIntoCurrentGrid(int[][] heights, int currentIdx1, int currentIdx2, int targetIdx1,
			int targetIdx2) {
		if (targetIdx1 < 0 || targetIdx1 > heights.length - 1 || targetIdx2 < 0 || targetIdx2 > heights[0].length - 1) {
//			System.out.printf("%b ([%d, %d] not existed)\n", false, targetIdx1, targetIdx2);
			return false;
		}

		if (heights[targetIdx1][targetIdx2] >= heights[currentIdx1][currentIdx2]) {
//			System.out.printf("%b (%d >= %d)\n", true, heights[targetIdx1][targetIdx2],
//					heights[currentIdx1][currentIdx2]);
			return true;
		} else {
//			System.out.printf("%b (%d< %d)\n", true, heights[targetIdx1][targetIdx2],
//					heights[currentIdx1][currentIdx2]);
			return false;
		}
	}

	private Map<String, List<Integer>> getAllGridCanFlowIntoAtlantic(int[][] heights) {
		Map<String, List<Integer>> coordinateMap = new HashMap<String, List<Integer>>();

		int verticalLength = heights.length - 1;
		for (int idx1 = 0; idx1 < heights[0].length; idx1++) {
//			System.out.printf("[%d, %d]=%d ", verticalLength, idx1,  heights[verticalLength][idx1]);
			getGridCanFlowIntoCurrentGrid(heights, verticalLength, idx1, coordinateMap);
			coordinateMap.put(verticalLength + "-" + idx1, Arrays.asList(verticalLength,idx1));
		}
//		System.out.println();

		int horizontalLength = heights[0].length - 1;
		for (int idx2 = 0; idx2 < heights.length; idx2++) {
//			System.out.printf("[%d, %d]=%d ", idx2, horizontalLength, heights[idx2][horizontalLength]);
			getGridCanFlowIntoCurrentGrid(heights, idx2, horizontalLength, coordinateMap);
			coordinateMap.put( idx2+ "-" + horizontalLength, Arrays.asList(idx2, horizontalLength));
		}
//		System.out.println();

		return coordinateMap;
	}

	private static void printHeights(int[][] heights) {
//		for (int idx1 = 0; idx1 < heights.length; idx1++) {
//			System.out.printf("%s\n", Arrays.toString(heights[idx1]));
//		}
		for (int idx1 = 0; idx1 < heights.length; idx1++) {
			for (int idx2 = 0; idx2 < heights[idx1].length; idx2++) {
				System.out.printf("[%d][%d]=%d\t", idx1, idx2, heights[idx1][idx2]);
			}
			System.out.printf("\n");
		}
	}
	
	private static void printResultDetail(int[][] heights, List<List<Integer>> result) {
		System.out.printf("Result detail: ");
		for (int idx1 = 0; idx1 < result.size(); idx1++) {
			System.out.printf("%s= %d\t", result.get(idx1).toString(), heights[result.get(idx1).get(0)][result.get(idx1).get(1)]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Apr27 apr27 = new Apr27();
		List<List<Integer>> result = null;
		int[][] heights = null;
		
		heights = new int[][] { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 },
				{ 5, 1, 1, 2, 4 } };
		printHeights(heights);
		result = apr27.pacificAtlantic(heights);
		System.out.printf("Atlantic & Pacific: %s\n", result.toString());
		printResultDetail(heights, result);
		
		System.out.println("----------------------------------------------------------------------------");
		
		heights = new int[][] { { 2, 1 }, { 1, 2 } };
		printHeights(heights);
		result = apr27.pacificAtlantic(heights);
		System.out.printf("Atlantic & Pacific: %s\n", result.toString());
		printResultDetail(heights, result);
		
		System.out.println("----------------------------------------------------------------------------");
		
		heights = new int[][] { { 1, 1 }, { 1, 1 }, { 1, 1 } };
		printHeights(heights);
		result = apr27.pacificAtlantic(heights);
		System.out.printf("Atlantic & Pacific: %s\n", result.toString());
		printResultDetail(heights, result);

	}
}
