package com.leetcode.y2022.m10;

import java.util.Arrays;

public class Oct19 {

/**
1700. 无法吃午餐的学生数量
学校的自助午餐提供圆形和方形的三明治，分别用数字 0 和 1 表示。所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。
餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个 栈 里，每一轮：
如果队列最前面的学生 喜欢 栈顶的三明治，那么会 拿走它 并离开队列。否则，这名学生会 放弃这个三明治 并回到队列的尾部。
这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。
给你两个整数数组 students 和 sandwiches ，其中 sandwiches[i] 是栈里面第 i​​​​​​ 个三明治的类型（i = 0 是栈的顶部）， 
students[j] 是初始队列里第 j​​​​​​ 名学生对三明治的喜好（j = 0 是队列的最开始位置）。
请你返回无法吃午餐的学生数量。

示例 1：
输入：students = [1,1,0,0], sandwiches = [0,1,0,1]
输出：0 
解释：
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
所以所有学生都有三明治吃。

示例 2：
输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
输出：3
 
提示：
1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] 要么是 0 ，要么是 1 。
students[i] 要么是 0 ，要么是 1 。
 */
	
    public int countStudents(int[] students, int[] sandwiches) {
    	int studentCount = students.length;
    	int sandwichMatchCount = 0;
    	int firstStudent = 0;
    	while(sandwichMatchCount!=studentCount) {
    		if(students[0]==sandwiches[0]) {
    			students = Arrays.copyOfRange(students, 1, students.length);
    			sandwiches = Arrays.copyOfRange(sandwiches, 1, sandwiches.length);
    			sandwichMatchCount = 0;
    			studentCount = studentCount - 1;
    		} else {
    			firstStudent = students[0];
    			for(int i=1;i<students.length;i++) {
    				students[i-1] = students[i];
    			}
    			students[students.length - 1] = firstStudent;
    			sandwichMatchCount = sandwichMatchCount +1;
    		}
    	}
    	return studentCount;
    }
    
    /**
     * 学生在排队领取三明治的时候，如果待领取三明治为学生不喜欢的形状，学生会从新排到队伍的末尾，等待下一轮领取，
     * 所以此题的本质是按形状统计学生人数，然后循环三明治数组，依次从对应形状的学生人数中减1，最终剩下的学生总人数即为目标结果
     * @param students
     * @param sandwiches
     * @return
     */
	public int countStudents1(int[] students, int[] sandwiches) {
		// 三明治的种类（圆形和方形2中）
		int[] cnt = new int[2];
		
		// 统计喜欢圆形三明治（0） 与 喜欢方形三明治（1）的学生数量
		for (int student : students) {
			cnt[student]++;
		}
		
		// 循环每一个三明治，从对应三明治形状的学生统计人数中减去1（前提是未领取三明治的人数是大于0的）
		for (int sandwich : sandwiches) {
			if (cnt[sandwich] > 0)
				cnt[sandwich]--;
			else
				break;
		}
		return cnt[0] + cnt[1];
	}
    
	public static void main(String[] args) {
		Oct19 oct19 = new Oct19();
		
		int[] students = new int[] {1,1,0,0};
		int[] sandwiches = new int[] {0,1,0,1};
		System.out.printf("1-countStudents=%d\n", oct19.countStudents(students, sandwiches));
		
		students = new int[] {1,1,1,0,0,1};
		sandwiches = new int[] {1,0,0,0,1,1};
		System.out.printf("2-countStudents=%d\n", oct19.countStudents(students, sandwiches));
	}

}
