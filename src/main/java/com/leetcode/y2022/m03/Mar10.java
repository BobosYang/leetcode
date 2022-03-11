package com.leetcode.y2022.m03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mar10 {
/*
给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。

示例 1：
输入：root = [1,null,3,2,4,null,5,6]
输出：[1,3,5,6,2,4]
          1
     3     2     4
   5 6
   
示例1的图解分析（将数组拆分成树结构）
1,   null,
3,   2,   4,   null,
5,6

示例 2：
输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
          1
  2     3     4      5
       6  7   8     9  10
          11  12  13
          14

示例2的图解分析（将数组拆分成树结构）
1,   null,
(1)
       (1)
2,   3,   4,   5,   null,
(1) (2)  (3)  (4)
(1)              (2)               (3)               (4)
      null,   6,   7,   null,   8,   null,   9,   10,   null,
                (1)  (2)            (3)           (4)   (5)

(1)            (2)             (3)              (4)
      null,   11,   null,   12,   null,   13, null,
                 (1)             (2)              (3)
(1)            (2)
      null,   14



提示：
节点总数在范围 [0, 104]内
0 <= Node.val <= 104
n 叉树的高度小于或等于 1000
 

进阶：递归法很简单，你可以使用迭代法完成此题吗?

 */

    public List<Integer> preorder(Node root) {
        List<Integer> preOrderList = null;
        
        return preOrderList;
    }
	
	public Node getRootNode(List<Integer> nums) {
		if (nums == null) {
			return null;
		}
		Node root = null;
		int loopCount = 0;
		if (nums.size() == 1 || nums.size() == 2) {
			// only 1 node
			root = new Node(nums.get(0));
		} else {
			// more than 1 node
			int lastLevelNodeCount = 1;
			int currentLevelNodeCount = 0;
			int lineBreakIndex = 0;
			int lineStart = 0;
			List<Integer> remainingNodes = nums;
			List<Node> parentNodes = null;
			List<Node> childrenNodes = null;
			List<List<Node>> parentNodeGroup = new ArrayList<List<Node>>();
			List<List<Node>> childrenNodeGroup = null;
			List<List<List<Node>>> childrenNodeGroupLine = new ArrayList<List<List<Node>>>();
			System.out.println("-----------------------------------------------------------");
			System.out.println(remainingNodes);
			while (remainingNodes.size() > 0) {
				childrenNodeGroup = new ArrayList<List<Node>>();
				currentLevelNodeCount = 0;
				lineStart = 0;
				if (remainingNodes.get(0) == null) {
					// remaining nodes starts with null (first node of remaining is null)
					lastLevelNodeCount--;
					loopCount++;
					lineStart = 1;

					childrenNodes = new ArrayList<Node>();
					childrenNodes.add(null);
					childrenNodeGroup.add(childrenNodes);
				}
				childrenNodes = new ArrayList<Node>();
				parentNodes = new ArrayList<Node>();
				Node tempNode = null;
				for (int idx = lineStart; idx < remainingNodes.size(); idx++) {
					loopCount++;
					if (remainingNodes.get(idx) != null) {
						currentLevelNodeCount++;
						tempNode = new Node(remainingNodes.get(idx));
						parentNodes.add(tempNode);
						childrenNodes.add(tempNode);
					} else {
						lastLevelNodeCount--;
						childrenNodeGroup.add(childrenNodes);
						if (lastLevelNodeCount == 0) {
							lineBreakIndex = idx;
//							System.out.println("lineBreakIndex=" + lineBreakIndex);
							if (lineBreakIndex + 1 <= remainingNodes.size()) {
								lastLevelNodeCount = currentLevelNodeCount;
//								System.out.println("currentLevelNodeCount="+ currentLevelNodeCount);
								remainingNodes = remainingNodes.subList(lineBreakIndex + 1, remainingNodes.size());
//								System.out.println(remainingNodes);
							}
							parentNodeGroup.add(parentNodes);
							childrenNodeGroupLine.add(childrenNodeGroup);
							break;
						} else {
							childrenNodes = new ArrayList<Node>();
						}
					}
				}

				if (loopCount == nums.size()) {
					childrenNodeGroup.add(childrenNodes);
					parentNodeGroup.add(parentNodes);
					childrenNodeGroupLine.add(childrenNodeGroup);
					break;
				}
//				System.out.print("parentNodeGroup:");
//				System.out.println(parentNodeGroup);
//				System.out.print("childrenNodeGroup:");
//				System.out.println(childrenNodeGroup);
//				System.out.println("-----------------------------------------------------------");
			}

//			System.out.print("parentNodeGroup:");
//			System.out.println(parentNodeGroup);
//			System.out.print("childrenNodeGroupLine:");
//			System.out.println(childrenNodeGroupLine);
//			System.out.println("-----------------------------------------------------------");

			for (int parentGroupIdx = 0; parentGroupIdx < parentNodeGroup.size(); parentGroupIdx++) {
				System.out.println("=================== Level " + parentGroupIdx + " ===================");

				for (int parentNodeIdx = 0; parentNodeIdx < parentNodeGroup.get(parentGroupIdx)
						.size(); parentNodeIdx++) {
					System.out.print("parent node: ");
					System.out.println(parentNodeGroup.get(parentGroupIdx).get(parentNodeIdx).val);
					if ((parentGroupIdx < parentNodeGroup.size() - 1)
							&& (parentNodeIdx < childrenNodeGroupLine.get(parentGroupIdx + 1).size())) {
						System.out.print("+ children nodes: ");
						System.out.println(childrenNodeGroupLine.get(parentGroupIdx + 1).get(parentNodeIdx));
						parentNodeGroup.get(parentGroupIdx).get(parentNodeIdx).children = childrenNodeGroupLine
								.get(parentGroupIdx + 1).get(parentNodeIdx);
					}
				}
			}
			root = parentNodeGroup.get(0).get(0);
		}

		return root;
	}
    
	public static void main(String[] args) {
		List<Integer> nums = null;
		Node root = null;
		Mar10 mar10 = new Mar10();

		nums = Stream.of(1, null, 3, 2, 4, null, 5, 6).collect(Collectors.toList());
		root = mar10.getRootNode(nums);

		nums = Stream.of(1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null,
				13, null, null, 14).collect(Collectors.toList());
		root = mar10.getRootNode(nums);

		mar10.preorder(root);
	}
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
    
    public String toString() {
    	return "" + val;
    }
};