package com.leetcode.y2022.m03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinarySearchTree {
	private TreeNode root;

	public BinarySearchTree() {

	}

	public BinarySearchTree(Integer... val) {
		List<Integer> nodeVals = Stream.of(val).collect(Collectors.toList());
		for (Integer nodeVal : nodeVals) {
			if (nodeVal != null) {
				this.insert(nodeVal);
			}
		}
	}
	
	public void insert(int key) {
		TreeNode p = new TreeNode(); // 待插入的节点
		p.val = key;

		if (root == null) {
			root = p;
		} else {
			TreeNode parent = new TreeNode();
			TreeNode current = root;
			while (true) {
				parent = current;
				if (key > current.val) {
					current = current.right; // 右子树
					if (current == null) {
						parent.right = p;
						return;
					}
				} else // 本程序没有做key出现相等情况的处理，暂且假设用户插入的节点值都不同
				{
					current = current.left; // 左子树
					if (current == null) {
						parent.left = p;
						return;
					}
				}
			}
		}
	}

	public TreeNode getRoot() {
		return this.root;
	}

	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(5, 3, 6, 2, 4, null, 7);
		System.out.println(bst);
	}
}
