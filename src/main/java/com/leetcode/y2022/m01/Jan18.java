package com.leetcode.y2022.m01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jan18 {
/*
给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。

示例 1：
输入：timePoints = ["23:59","00:00"]
输出：1

示例 2：
输入：timePoints = ["00:00","23:59","00:00"]
输出：0
 
提示：
2 <= timePoints.length <= 2 * 104
timePoints[i] 格式为 "HH:MM"
*/
	
	public int findMinDifference1(List<String> timePoints) {
		if (timePoints == null || timePoints.size() < 2 || timePoints.size()>1440) {
			return 0;
		}
		
		System.out.println(timePoints.toString());
		List<Integer> intTimePoints = new ArrayList<Integer>(timePoints.size());
		String[] timePointSplit = null;
		for (String timePoint : timePoints) {
			timePointSplit = timePoint.split(":");
			intTimePoints.add(Integer.parseInt(timePointSplit[0]) * 60 + Integer.parseInt(timePointSplit[1]));
			intTimePoints.add(24 * 60 + Integer.parseInt(timePointSplit[0]) * 60 + Integer.parseInt(timePointSplit[1]));
		}
		System.out.println(intTimePoints.toString());
		Collections.sort(intTimePoints);
		int minDiffMinutes = intTimePoints.get(1) - intTimePoints.get(0);
		int currentDiffMinutes = 0;
		for (int idx = 1; idx < intTimePoints.size() - 1; idx++) {
			currentDiffMinutes = intTimePoints.get(idx + 1) - intTimePoints.get(idx);
			if (minDiffMinutes <= currentDiffMinutes) {
				continue;
			} else {
				minDiffMinutes = currentDiffMinutes;
			}
		}

//		return (minDiffMinutes / 100) * 60 + minDiffMinutes % 100;
		return minDiffMinutes;
    }
	
	public int findMinDifference(List<String> timePoints) {
		if (timePoints == null || timePoints.size() < 2 || timePoints.size()>1440) {
			return 0;
		}
		
//		System.out.println(timePoints.toString());
		int[] intTimePoints = new int[timePoints.size()];
		
		for (int idx=0;idx<timePoints.size();idx++) {
			intTimePoints[idx] = Integer.parseInt(timePoints.get(idx).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(idx).substring(3,5));
		}
		
//		System.out.println(Arrays.toString(intTimePoints));
		Arrays.sort(intTimePoints);
		
		int minDiffMinutes = intTimePoints[0] + 1440 - intTimePoints[intTimePoints.length-1];
		for (int idx = 1; idx < intTimePoints.length; idx++) {
			minDiffMinutes = Math.min(minDiffMinutes, intTimePoints[idx] - intTimePoints[idx-1]);
		}

		return minDiffMinutes;
    }

	public static void main(String[] args) {
		Jan18 jan18 = new Jan18();
		List<String> timePoints = Stream.of("23:59", "00:00").collect(Collectors.toList());
		System.out.println(jan18.findMinDifference(timePoints));
		
		timePoints = Stream.of("00:00","23:59","00:00").collect(Collectors.toList());
		System.out.println(jan18.findMinDifference(timePoints));
		
		timePoints = Stream.of("01:01","02:01").collect(Collectors.toList());
		System.out.println(jan18.findMinDifference(timePoints));
		
		timePoints = Stream.of("01:01","02:01", "03:00").collect(Collectors.toList());
		System.out.println(jan18.findMinDifference(timePoints));
	}

}
