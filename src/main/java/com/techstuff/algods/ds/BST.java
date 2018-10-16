package com.techstuff.algods.ds;

import java.util.ArrayList;
import java.util.List;

public class BST<T extends Comparable<T>> {
	
	private Node root;
	
	public BST(List<T> sequence) {
		for(T elem : sequence) {
			if(root == null) {
				root = new Node(elem);
			} else {
				Node current = root;
				while(true) {
					if(current.data.compareTo(elem) < 0) {
						if(current.right != null) {
							current = current.right;
						} else {
							current.setRight(new Node(elem));
							break;
						}
					} else {
						if(current.left != null) {
							current = current.left;
						} else {
							current.setLeft(new Node(elem));
							break;
						}
					}
				}
			}
		}
	}
	
	private Node search(T data) {
		Node current = root;
		while(current != null && !current.data.equals(data)) {
			if(current.data.compareTo(data) < 0) {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		return current;
	}
	
	public T getMinimum() {
		return getMinimum(root);
	}
	
	private T getMinimum(Node current) {
		while(current.left != null) {
			current = current.left;
		}
		return current.data;
	}
	
	public T getMaximum() {
		return getMaximum(root);
	}
	
	private T getMaximum(Node current) {
		while(current.right != null) {
			current = current.right;
		}
		return current.data;
	}
	
	public T getSuccessor(T data) {
		Node current = search(data);
		if(current.right != null) {
			return getMinimum(current.right);
		}
		Node parent = current.parent;
		while(parent != null && current.equals(parent.right)) {
			current = parent;
			parent = current.parent;
		}
		if(parent == null) {
			return null;
		} else {
			return parent.data;
		}
	}
	
	public T getPredecessor(T data) {
		Node current = search(data);
		if(current.left != null) {
			return getMaximum(current.left);
		}
		Node parent = current.parent;
		while(parent != null && current.equals(parent.left)) {
			current = parent;
			parent = current.parent;
		}
		if(parent == null) {
			return null;
		} else {
			return parent.data;
		}
	}
	
	public void insert(T data) {
		Node current = root;
		Node prev = null;
		while(current != null) {
			prev = current;
			if(current.data.compareTo(data) < 0) {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		if(prev == null) {
			root = new Node(data);
		}
		if(prev.data.compareTo(data) < 0) {
			prev.setRight(new Node(data));
		} else {
			prev.setLeft(new Node(data));
		}
	}
	
	public void delete(T data) {
		Node node = search(data);
		if(node.left == null) {
			transplant(node, node.right);
		} else if(node.right == null) {
			transplant(node, node.left);
		} else {
			Node successor = node.right;
			while(successor.left != null) {
				successor = successor.left;
			}
			transplant(successor, successor.right);
			transplant(node, successor);
			successor.setLeft(node.left);
			successor.setRight(node.right);
		}
	}
	
	public void transplant(Node current, Node replacement) {
		if(current.parent == null) {
			root = replacement;
		} else if(current.parent.left == current) {
			current.parent.setLeft(replacement);
		} else {
			current.parent.setRight(replacement);
		}
	}
	
	public List<T> getInorder() {
		return getInorder(new ArrayList<T>(), root);
	}
	
	public List<T> getInorder(List<T> list, Node node) {
		if(node != null) {
			getInorder(list, node.left);
			list.add(node.data);
			getInorder(list, node.right);
		}
		return list;
	}
	
	public List<T> getLevelorder() {
		Queue<Node> queue = new Queue<>(getSize());
		List<T> list = new ArrayList<>();
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			Node current = queue.dequeue();
			if(current.left != null) {
				queue.enqueue(current.left);
			}
			if(current.right != null) {
				queue.enqueue(current.right);
			}
			list.add(current.data);
		}
		return list;
	}
		
	public int getSize() {
		return getSize(root);
	}
	
	private int getSize(Node node) {
		if(node == null) {
			return 0;
		}
		int leftSize = getSize(node.left);
		int rightSize = getSize(node.right);
		return leftSize + rightSize + 1;
	}
	
	public void print() {
		printLevel(root, 0, "root");
	}
	
	private void printLevel(Node current, int i, String pos) {
		if(current == null) {
			return;
		}
		for(int j = 0 ; j < i ; j++) {
			System.out.print(" ");
		}
		System.out.println(current.data + " " + pos);
		printLevel(current.left, i + 1, "left");
		printLevel(current.right, i + 1, "right");
	}

	private class Node {
		
		private Node parent;
		
		private Node left;
		
		private Node right;
		
		private T data;
		
		public Node(T data) {
			this.data = data;
		}
		
		public void setLeft(Node left) {
			this.left = left;
			if(left != null) {
				left.parent = this;
			}
		}
		
		public void setRight(Node right) {
			this.right = right;
			if(right != null) {
				right.parent = this;
			}
		}
	}
}
