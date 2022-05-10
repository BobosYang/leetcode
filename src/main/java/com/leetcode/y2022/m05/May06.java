package com.leetcode.y2022.m05;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class May06 {

	/**
			933. 最近的请求次数
			写一个 RecentCounter 类来计算特定时间范围内最近的请求。
			
			请你实现 RecentCounter 类：
			RecentCounter() 初始化计数器，请求数为 0 。
			int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
			保证 每次对 ping 的调用都使用比之前更大的 t 值。
			
			示例 1：
			输入：
			["RecentCounter", "ping", "ping", "ping", "ping"]
			[[], [1], [100], [3001], [3002]]
			输出：
			[null, 1, 2, 3, 3]
			解释：
			RecentCounter recentCounter = new RecentCounter();
			recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
			recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
			recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
			recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
			 
			提示：
			1 <= t <= 10^9
			保证每次对 ping 调用所使用的 t 值都 严格递增
			至多调用 ping 方法 10^4 次
	 */
	
	/**
	 * 实现方式 1
	 */
//	private List<Integer> pingRecords = new ArrayList<Integer>();
//	
//	public May06() {
//		pingRecords = new ArrayList<Integer>();
//	}
//	
//	int ping(int t) {
//		int counter = 0;
//
//		// 追加ping时间
//		pingRecords.add(t);
//
//		int lowerBoundary = t - 3000;
//		int upperBoundary = t;
//		for (Integer pingTime : pingRecords) {
//			if (pingTime >= lowerBoundary && pingTime <= upperBoundary) {
//				counter++;
//			}
//		}
//
//		return counter;
//	}
	
	/**
	 * 实现方式 2
	 */
	Queue<Integer> pingRecords = new PriorityQueue<Integer>();
	
	public May06() {
		pingRecords = new PriorityQueue<Integer>();
	}

	int ping(int t) {
		// 追加ping时间
		pingRecords.add(t);

		while (!pingRecords.isEmpty() && pingRecords.peek() < t - 3000) {
			pingRecords.poll();
		}

		return pingRecords.size();
	}

	public static void main(String[] args) {
		May06 may06 = null;

		String[] commands = new String[] { "RecentCounter", "ping", "ping", "ping", "ping" };
		int[][] pingTime = new int[][] { {}, { 1 }, { 100 }, { 3001 }, { 3002 } };

		for (int idx = 0; idx < commands.length; idx++) {
			if ("RecentCounter".equals(commands[idx])) {
				System.out.printf("RecentCounter()\n");
				may06 = new May06();
			} else if ("ping".equals(commands[idx])) {
				System.out.printf("ping(%d)=%d\n", pingTime[idx][0], may06.ping(pingTime[idx][0]));
			}
		}
	}
}
