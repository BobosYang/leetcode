package com.leetcode.y2022.m03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mar18 {
/*
假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。

示例 1:
输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
输出: ["Shogun"]
解释: 他们唯一共同喜爱的餐厅是“Shogun”。

示例 2:
输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
输出: ["Shogun"]
解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 
提示:
1 <= list1.length, list2.length <= 1000
1 <= list1[i].length, list2[i].length <= 30 
list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。
list1 的所有字符串都是 唯一 的。
list2 中的所有字符串都是 唯一 的。

 */

    public String[] findRestaurant(String[] list1, String[] list2) {
        return IntStream.range(0, list1.length + list2.length) // 产生0-(n1 + n2 - 1)的int流
                .boxed() // mapToObj(Integer::valueOf) 转为Integer
                // 对相同的String进行groupby，生成map<String, List<Integer>>
                .collect(Collectors.groupingBy(i -> i < list1.length ? list1[i] : list2[i - list1.length]))
                // List<Map.Entry>
                .entrySet()
                // 重新转为流Map.Entry<String, List<Integer>>
                .stream()
                // filter去掉单个情况
                .filter(i -> i.getValue().size() == 2)
                // 再进行groupby，生成TreeMap<Integer, List<String>>
                .collect(Collectors.groupingBy(i -> i.getValue().get(0) + i.getValue().get(1), TreeMap::new, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                // 输出第一个
                .firstEntry()
                .getValue()
                .toArray(new String[0]);
    }
    
	public String[] findRestaurant1(String[] list1, String[] list2) {
		List<String> list = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		int min = Integer.MAX_VALUE, len = list1.length;
		for (int i = 0; i < len; ++i) {
			map.put(list1[i], i);
		}
		len = list2.length;
		for (int i = 0; i < len; ++i) {
			if (map.containsKey(list2[i])) {
				int sum = map.get(list2[i]) + i;
				if (sum < min) {
					list.clear();
					min = sum;
					list.add(list2[i]);
				} else if (sum == min) {
					list.add(list2[i]);
				}
			}
		}
		return list.toArray(new String[list.size()]);
	}
    
	public static void main(String[] args) {
		Mar18 mar18 = new Mar18();
		String[] list1 = null;
		String[] list2 = null;
		String[] sameFavoriteRestaurant = null;

		list1 = new String[] { "Shogun", "Tapioca Express", "Burger King", "KFC" };
		list2 = new String[] { "Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun" };
		sameFavoriteRestaurant = mar18.findRestaurant(list1, list2);
		System.out.println(Arrays.toString(sameFavoriteRestaurant));

		list1 = new String[] { "Shogun", "Tapioca Express", "Burger King", "KFC" };
		list2 = new String[] { "KFC", "Shogun", "Burger King" };
		sameFavoriteRestaurant = mar18.findRestaurant(list1, list2);
		System.out.println(Arrays.toString(sameFavoriteRestaurant));
		
		sameFavoriteRestaurant = mar18.findRestaurant1(list1, list2);
		System.out.println(Arrays.toString(sameFavoriteRestaurant));
		
	}
}
