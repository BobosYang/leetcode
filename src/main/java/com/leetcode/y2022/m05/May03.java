package com.leetcode.y2022.m05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.format.number.NumberStyleFormatter;

public class May03 {
	
	/**
		937. 重新排列日志文件
		给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
		
		有两种不同类型的日志：
			* 字母日志：除标识符之外，所有字均由小写字母组成
			* 数字日志：除标识符之外，所有字均由数字组成
		请按下述规则将日志重新排序：
			* 所有 字母日志 都排在 数字日志 之前。
			* 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
			* 数字日志 应该保留原来的相对顺序。
		返回日志的最终顺序。
		
		示例 1：
		输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
		输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
		解释：
		字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
		数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
		
		示例 2：
		输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
		输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
		
		提示：
		1 <= logs.length <= 100
		3 <= logs[i].length <= 100
		logs[i] 中，字与字之间都用 单个 空格分隔
		题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
	 */
	
	public String[] reorderLogFiles(String[] logs) {
		int firstSpaceIndex = 0;
		int secondSpaceIndex = 0;
		String secondElement = null;
		List<String> characterLogList = new ArrayList<String>();
		List<String> digitalLogList = new ArrayList<String>();
//		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$"); 
		for (String log : logs) {
			firstSpaceIndex = log.indexOf(" ", 0);
			secondSpaceIndex = log.indexOf(" ", firstSpaceIndex + 1);
			secondSpaceIndex = secondSpaceIndex == -1 ? log.length() : secondSpaceIndex;
			secondElement = log.substring(firstSpaceIndex + 1, secondSpaceIndex);

//			System.out.printf("-----log: %s\n", log);
//			System.out.printf("1st space index=%d, 2nd space index=%d\n",firstSpaceIndex , secondSpaceIndex);
//			System.out.printf("Second element of log=%s\n", secondElement);
//			System.out.printf("pattern.match(%s)=%b\n",  secondElement, pattern.matcher(secondElement).matches());

//			if(pattern.matcher(secondElement).matches()) {
			if (secondElement.matches("^[-\\+]?[\\d]*$")) {
				digitalLogList.add(log);
			} else {
				characterLogList.add(log);
			}
		}

		String temp = null;
		for (int idx = 0; idx < characterLogList.size() - 1; idx++) {
			for (int idy = 0; idy < characterLogList.size() - idx - 1; idy++) {
				if (characterLogList.get(idy).substring(characterLogList.get(idy).indexOf(" ")).compareTo(
						characterLogList.get(idy + 1).substring(characterLogList.get(idy + 1).indexOf(" "))) > 0) {
					temp = characterLogList.get(idy);
					characterLogList.set(idy, characterLogList.get(idy + 1));
					characterLogList.set(idy + 1, temp);
				}
			}
		}

		for (int idx = 0; idx < characterLogList.size() - 1; idx++) {
			for (int idy = 0; idy < characterLogList.size() - idx - 1; idy++) {
				if (characterLogList.get(idy).substring(characterLogList.get(idy).indexOf(" ")).compareTo(
						characterLogList.get(idy + 1).substring(characterLogList.get(idy + 1).indexOf(" "))) == 0) {
					if (characterLogList.get(idy).substring(0, characterLogList.get(idy).indexOf(" "))
							.compareTo(characterLogList.get(idy + 1).substring(0,
									characterLogList.get(idy + 1).indexOf(" "))) > 0) {
						temp = characterLogList.get(idy);
						characterLogList.set(idy, characterLogList.get(idy + 1));
						characterLogList.set(idy + 1, temp);
					}
				}
			}
		}

		characterLogList.addAll(digitalLogList);
		return characterLogList.toArray(new String[characterLogList.size()]);
	}

	public String[] reorderLogFiles1(String[] logs) {
		Arrays.sort(logs, (log1, log2) -> {// 运用到了java里的泛型，第二个参数重新定义排序规则
			String[] split1 = log1.split(" ", 2); // 将log1 按分隔符“ ” ，分成2份，即把标识符分开来
			String[] split2 = log2.split(" ", 2);
			boolean isDigit1 = Character.isDigit(split1[1].charAt(0));// 判断除标识符外的第一个字符是数字true，字母false
			boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
			if (!isDigit1 && !isDigit2) { // 如果两个日志都是字母日志
				int cmp = split1[1].compareTo(split2[1]); // 先比较内容字母split1>split2则返回1，等于返0，小于返-1
				if (cmp != 0) {
					return cmp;
				}
				return split1[0].compareTo(split2[0]);// 若内容字母相同则比较标识符
			}
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
			// 如果split1是数字字符，且split2是数字字符返回0，
			// 如果split1是数字字符，且split2是字母字符返回1，即split1>split2,从小到大排序，split2提前
			// 如果split1是字母字符，返回-1，
		});
		return logs;
	}
	
	public String[] reorderLogFiles2(String[] logs) {
		Arrays.sort(logs, new Comparator<String>() {
			@Override
			public int compare(String log1, String log2) {
				String id1, id2, c1, c2;
				boolean isNum1 = false, isNum2 = false;
				int n1 = log1.length(), n2 = log2.length(), t = 0;

				while (t < n1 && log1.charAt(t) != ' ') {
					t++;
				}
				id1 = log1.substring(0, t); // 第一个日志的标识符
				c1 = log1.substring(t, n1); // 第一个日志的内容
				isNum1 = log1.charAt(t + 1) >= '0' && log1.charAt(t + 1) <= '9'; // 第一个日志是否数字日志

				t = 0;
				while (t < n2 && log2.charAt(t) != ' ') {
					t++;
				}
				id2 = log2.substring(0, t); // 第二个日志的标识符
				c2 = log2.substring(t, n2); // 第二个日志的内容
				isNum2 = log2.charAt(t + 1) >= '0' && log2.charAt(t + 1) <= '9'; // 第二个日志是否数字日志

				if (isNum1 != isNum2) { // 如果两个日志不是同一个类型
					if (isNum1) { // 如果第一个日志是数字日志，那第二个日志一定是字符日志
						return 1; // 往后挪动
					} else { // 否则如果第一个日志是字符日志，那第二个日志一定是数字日志
						return -1; // 无需挪动
					}
				} else if (isNum1) { // 如果两个日志是同一个类型， 且 第一个日志是数字日志
					return 0; // 无需挪动
				} else { // 如果两个日志是同一个类型， 且 第一个日志是字符日志
					if (c1.equals(c2)) { // 如果日志的内容相同，就按照日志的标识符排序
						return id1.compareTo(id2);
					} else { // 如果日志的内容不相同，就按照日志的内容排序
						return c1.compareTo(c2);
					}
				}
			}
		});
		return logs;
	}
	
	public static void main(String[] args) {
		May03 may03 = new May03();

		String[] logs = new String[] { "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig",
				"let3 art zero" };
		System.out.printf("logs: %s\n", Arrays.toString(logs));
		System.out.printf("Reordered log files: %s\n\n", Arrays.toString(may03.reorderLogFiles(logs)));

		logs = new String[] { "a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo" };
		System.out.printf("logs: %s\n", Arrays.toString(logs));
		System.out.printf("Reordered log files: %s\n\n", Arrays.toString(may03.reorderLogFiles(logs)));
		
		logs = new String[] { "a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo" };
		System.out.printf("logs: %s\n", Arrays.toString(logs));
		System.out.printf("Reordered log files: %s\n\n", Arrays.toString(may03.reorderLogFiles(logs)));
	}

}
