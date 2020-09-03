package com.techstuff.algods.ds.advanced;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.techstuff.algods.ds.CircularDoubleLinkedList;

public class FibonacciHeap<T extends Comparable<T>> {
	
	private CircularDoubleLinkedList<Node> rootList;

	private Node min;
	
	private int numKeys;
	
	public Node insert(T key) {
		Node node = new Node(key);
		node.mark = false;
		node.parent = null;
		if(min == null) {
			rootList = new CircularDoubleLinkedList<>();
			rootList.insert(node);
			min = node;
			numKeys++;
			return node;
		}
		rootList.insert(node);
		if(node.key.compareTo(min.key) < 0) {
			min = node;
		}
		numKeys++;
		return node;
	}
	
	public T minimum() {
		return min.key;
	}
	
	public T extractMin() {
		if(min == null) {
			return null;
		}
		Node minimum = min;
		for(Node child : minimum.getChild().getSiblings().getAsList()) {
			child.parent = null;
			rootList.insert(child);
		}
		if(rootList.size() == 1) {
			min = null;
			rootList = null;
			numKeys--;
			return minimum.key;
		}
		Node right = rootList.right(minimum);
		rootList.remove(min);
		min = right;
		consolidate();
		numKeys--;
		return minimum.key;
	}
	
	public FibonacciHeap<T> union(FibonacciHeap<T> second) {
		FibonacciHeap<T> unionHeap = new FibonacciHeap<>();
		unionHeap.rootList = this.rootList;
		unionHeap.min = this.min;
		unionHeap.rootList.insert(second.rootList);
		if(unionHeap.min == null || 
				(second.min != null && 
				(second.minimum().compareTo(unionHeap.minimum()) < 0)
				)) {
			unionHeap.min = second.min;
		}
		unionHeap.numKeys = this.numKeys + second.numKeys;
		return unionHeap;
	}
	
	public void decreaseKey(Node node, T newKey) {
		if(newKey.compareTo(node.key) > 0) {
			throw new RuntimeException("new key greater than old key");
		}
		node.key = newKey;
		Node parent = node.parent;
		if(parent != null) {
			cut(node, parent);
			cascadingCut(parent);
		}
		if(min.key.compareTo(node.key) > 0) {
			min = node;
		}
	}
	
	public void deleteNode(Node node) {
		// decreaseKey(node, -infinity); // call this in calling side as we can't do it in generic code
		extractMin();
	}
	
	private void cut(Node node, Node parent) {
		CircularDoubleLinkedList<Node> children = parent.getChild().getSiblings();
		children.remove(node);
		if(parent.getChild().equals(node)) {
			parent.child = children.right(node);
		}
		rootList.insert(node);
		node.parent = null;
		node.mark = false;
	}
	
	private void cascadingCut(Node parent) {
		Node grandParent = parent.getParent();
		if(grandParent == null) {
			return;
		}
		if(!parent.mark) {
			parent.mark = true;
		} else {
			cut(parent, grandParent);
			cascadingCut(grandParent);
		}
	}
	
	private void consolidate() {
		Map<Integer, Node> degMap = new HashMap<>();
		Node node = min;
		while(!(degMap.containsKey(node.getDegree()) && degMap.get(node.getDegree()).equals(node))) {
			int degree = node.getDegree();
			while(degMap.containsKey(degree)) {
				Node other = degMap.get(degree);
				if(node.key.compareTo(other.key) > 0) {
					Node temp = node;
					node = other;
					other = temp;
				}
				degMap.remove(degree);
				fibHeapLink(node, other);
				degree = node.getDegree();
			}
			degMap.put(degree, node);
			node = rootList.right(node);
		}
		rootList = new CircularDoubleLinkedList<>();
		min = null;
		for(Entry<Integer, Node> entry : degMap.entrySet()) {
			rootList.insert(entry.getValue());
			if(min == null || (min.getKey().compareTo(entry.getValue().key) > 0)) {
				min = entry.getValue();
			}
		}
	}
	
	private void fibHeapLink(Node x, Node y) {
		rootList.remove(y);
		x.getChild().getSiblings().insert(y);
		y.parent = x;
		y.mark = false;
	}
	
	// For testing
	List<T> getRootListData() {
		List<Node> rootListData = rootList.getAsList();
		return rootListData.stream().map(node -> node.key).collect(Collectors.toList());
	}
	
	public class Node {

		private T key;
		
		private Node parent;
		
		private Node child;
		
		private CircularDoubleLinkedList<Node> siblings;
		
		private boolean mark;
		
		Node() {
			siblings = new CircularDoubleLinkedList<>();
		}
		
		Node(T key) {
			this.key = key;
			child = new Node();
			siblings = new CircularDoubleLinkedList<>();
		}
		
		public T getKey() {
			return key;
		}

		public Node getParent() {
			return parent;
		}

		public Node getChild() {
			return child;
		}

		public CircularDoubleLinkedList<Node> getSiblings() {
			return siblings;
		}

		public boolean isMark() {
			return mark;
		}

		public int getDegree() {
			if(child == null) {
				return 0;
			}
			return child.getSiblings().size();
		}
		
		@Override
		public String toString() {
			return key.toString();
		}
	}
}
