package org.datasays.util;

import jodd.util.StringUtil;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class TreeNode {
	public long id;
	public String name;
	public int type = 1;
	public Object obj;
	public String code;
	public long parentId;
	public List<TreeNode> Children;

	//	public TreeNode(long id, String name, int type, Message obj) {
	//		super();
	//		this.id = id;
	//		this.name = name;
	//		this.type = type;
	//		this.obj = obj;
	//	}

	public TreeNode(long parentId, long id, String name, int type, Object obj) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.type = type;
		this.obj = obj;
	}

	public void addChild(TreeNode node) {
		if (Children == null) {
			Children = new ArrayList<TreeNode>();
		}
		node.parentId = id;
		Children.add(node);
	}

	public static TreeNode buildTree(List<TreeNode> nodes) {
		Map<Long, TreeNode> allNodes = new Hashtable<Long, TreeNode>();
		for (TreeNode node : nodes) {
			allNodes.put(node.id, node);
		}

		TreeNode root = null;
		for (TreeNode node : nodes) {
			TreeNode parent = allNodes.get(node.parentId);
			if (parent != null) {
				parent.addChild(node);
			} else {
				if (root != null) {
					System.out.println("重复的根节点" + root.id + "--" + node.parentId);
				}
				root = node;
			}
		}
		return root;
	}

	//	public static WORKFLOW_TREENODE.Builder convert(TreeNode node) {
	//		WORKFLOW_TREENODE.Builder tree = WORKFLOW_TREENODE.newBuilder();
	//		if (node != null) {
	//			tree.setID(node.id);
	//			tree.setTYPE(node.type);
	//			tree.setNAME(node.name);
	//			tree.setPARENTID(node.parentId);
	//			if (node.obj != null) {
	//				tree.setCODE(MessageUtil.conver2String(node.obj));
	//			} else if (node.code != null) {
	//				tree.setCODE(node.code);
	//			}
	//			if (node.Children != null) {
	//				for (int i = 0; i < node.Children.size(); i++) {
	//					WORKFLOW_TREENODE.Builder child = convert(node.Children.get(i));
	//					tree.addChildren(child);
	//				}
	//			}
	//		}
	//		return tree;
	//	}

	public static void main(String[] args) {
		try {
			String text = "&#x529f;&#x80fd;&#x754c;&#x9762;&#x8bbe;&#x8ba1;";

			System.out.println(StringUtil.escapeJava(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
