package com.leetcode.y2022.m03;

public class Mar19 {
/*
606. 根据二叉树创建字符串
你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。

示例 1:
输入: 二叉树: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

输出: "1(2(4))(3)"
解释: 原本将是“1(2(4)())(3())”，
在你省略所有不必要的空括号对之后，
它将是“1(2(4))(3)”。

示例 2:
输入: 二叉树: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

输出: "1(2()(4))(3)"
解释: 和第一个示例相似，
除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。

 */
	public String tree2str(TreeNode root) {
		if (root == null) {
			return null;
		} else {
			if (root.left == null && root.right == null) {
				return "" + root.val;
			}
			if (root.left != null && root.right == null) {
//				return root.val + "(" + tree2str(root.left) + ")" + "(null)";
				return root.val + "(" + tree2str(root.left) + ")";
			}
			if (root.left == null && root.right != null) {
				return root.val + "()" + "(" + tree2str(root.right) + ")";
			} else {
				return root.val + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";
			}
		}
	}
    
    public static void main(String[] args) {
		Mar19 mar19 = new Mar19();

		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode2 = new TreeNode(2, treeNode4, null);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
		System.out.println(mar19.tree2str(treeNode1));
		
		treeNode4 = new TreeNode(4);
		treeNode2 = new TreeNode(2, null, treeNode4);
		treeNode3 = new TreeNode(3);
		treeNode1 = new TreeNode(1, treeNode2, treeNode3);
		System.out.println(mar19.tree2str(treeNode1));
		
    }
}
