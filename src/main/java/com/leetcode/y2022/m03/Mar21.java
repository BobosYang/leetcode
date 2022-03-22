package com.leetcode.y2022.m03;

import java.util.HashSet;

public class Mar21 {

/*
给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

示例 1：
输入: root = [5,3,6,2,4,null,7], k = 9
输出: true

示例 2：
输入: root = [5,3,6,2,4,null,7], k = 28
输出: false
 
提示:
二叉树的节点个数的范围是  [1, 104].
-10^4 <= Node.val <= 10^4
root 为二叉搜索树
-10^5 <= k <= 10^5

*/
	
    public boolean findTarget(TreeNode root, int k) {
		HashSet<Integer> hashset = new HashSet<Integer>();
		return preOrder(root, hashset, k);
    }
    
    public boolean preOrder(TreeNode root,HashSet<Integer> hashset,int k){
		if (root == null)
			return false;
		if (hashset.contains(k - root.val)) {
			return true;
		}
		hashset.add(root.val);
		return preOrder(root.left, hashset, k) || preOrder(root.right, hashset, k);
    }
    
	public static void main(String[] args) {
		Mar21 mar21 = new Mar21();

		int k = 9;
		BinarySearchTree bst = new BinarySearchTree(5, 3, 6, 2, 4, null, 7);
		System.out.println(mar21.findTarget(bst.getRoot(), k));
		
		k = 28;
		bst = new BinarySearchTree(5,3,6,2,4,null,7);
		System.out.println(mar21.findTarget(bst.getRoot(), k));
	}

}
