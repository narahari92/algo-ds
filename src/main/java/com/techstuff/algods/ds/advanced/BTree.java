package com.techstuff.algods.ds.advanced;

import java.util.ArrayList;
import java.util.List;

import com.techstuff.algods.ds.Queue;

public class BTree<T extends Comparable<T>> {
	
	private Node root;

	private int t;
	
	// BTree creation
	public BTree(int t) {
		this.t = t;
		root = allocateNode();
		diskWrite(root);
	}
	
	public Node getRoot() {
		return root;
	}
	
	// For testing
	void setRoot(Node root) {
		this.root = root;
	}
	
	public void insert(T key) {
		if(root.getNumKeys() == 2 * t - 1) {
			Node newRoot = allocateNode();
			newRoot.children.add(root);
			this.root = newRoot;
			splitChild(this.root, 0);
		}
		insertNonFull(root, key);
	}
	
	public BTreeSearchResult<T> search(T key) {
		return search(root, key);
	}
	
	public void delete(T key) {
		delete(root, key);
	}
	
	public void levelOrder() {
		Queue<Node> queue = new Queue<>(getNodeCount(root));
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			Node node = queue.dequeue();
			for(int i = 0 ; i < node.getChildren().size() ; i++) {
				queue.enqueue(node.getChildren().get(i));
			}
			System.out.println(node.getKeys());
		}
	}
	
	private void insertNonFull(Node node, T key) {
		int i = 0;
		if(node.isLeaf()) {
			while(i < node.getNumKeys() && key.compareTo(node.keys.get(i)) > 0) {
				i++;
			}
			node.keys.add(i, key);
			diskWrite(node);
			return;
		}
		while(i < node.getNumKeys() && key.compareTo(node.keys.get(i)) > 0) {
			i++;
		}
		diskRead(node.children.get(i));
		if(node.children.get(i).getNumKeys() == 2 * t - 1) {
			splitChild(node, i);
			if(key.compareTo(node.keys.get(i)) > 0) {
				i++;
			}
		}
		insertNonFull(node.children.get(i), key);
	}
	
	private void splitChild(Node node, int index) {
		Node newChild = allocateNode();
		Node existingChild = node.children.get(index);
		for(int i = 0 ; i < t - 1 ; i++) {
			newChild.keys.add(existingChild.keys.get(i + t));
		}
		if(!existingChild.isLeaf()) {
			for(int i = 0 ; i < t ; i++) {
				newChild.children.add(existingChild.children.get(i + t));
			}
			existingChild.children.subList(t, existingChild.children.size()).clear();
		}
		node.children.add(index + 1, newChild); // Shift all children after index one step right and index new child
		node.keys.add(index, existingChild.keys.get(this.t - 1)); // Shift all keys after index one step right and insert key
		existingChild.keys.subList(t - 1, existingChild.keys.size()).clear();
		diskWrite(newChild);
		diskWrite(existingChild);
		diskWrite(node);
	}
	
	private BTreeSearchResult<T> search(Node node, T key) {
		int i = 0;
		while(i < node.getNumKeys() && key.compareTo(node.getKeys().get(i)) > 0) {
			i++;
		}
		if(i < node.getNumKeys() && key.equals(node.getKeys().get(i))) {
			return new BTreeSearchResult<>(node, i);
		}
		if(node.isLeaf()) {
			return null;
		}
		diskRead(node.getChildren().get(i));
		return search(node.getChildren().get(i), key);
	}
	
	private void delete(Node node, T key) {
		if(node.getKeys().indexOf(key) >= 0) {
			deleteKey(node, key);
			return;
		}
		int index = 0;
		for(T nodeKey : node.getKeys()) {
			if(key.compareTo(nodeKey) < 0) {
				break;
			}
			index++;
		}
		int newIndex = ensureTNodes(node, index);
		delete(node.getChildren().get(newIndex), key);
	}
	
	private int ensureTNodes(Node node, int index) {
		Node targetNode = node.getChildren().get(index);
		if(targetNode.getKeys().size() >= t) {
			return index;
		}
		int newIndex = index;
		if(index > 0 && node.getChildren().get(index - 1).getKeys().size() >= t) {
			Node siblingNode = node.getChildren().get(index - 1);
			targetNode.getKeys().add(0, node.getKeys().get(index - 1));
			node.getKeys().set(index - 1, siblingNode.getKeys().get(siblingNode.getNumKeys() - 1));
			siblingNode.getKeys().remove(siblingNode.getKeys().size() - 1);
			if(!siblingNode.isLeaf()) {
				targetNode.getChildren().add(0, siblingNode.getChildren().get(siblingNode.getChildren().size() - 1));
				siblingNode.getChildren().remove(siblingNode.getChildren().size() - 1);
			}
		} else if(index < node.getChildren().size() - 1 && node.getChildren().get(index + 1).getKeys().size() >= t) {
			Node siblingNode = node.getChildren().get(index + 1);
			targetNode.getKeys().add(targetNode.getKeys().size(), node.getKeys().get(index));
			node.getKeys().set(index, siblingNode.getKeys().get(0));
			siblingNode.getKeys().remove(0);
			if(!siblingNode.isLeaf()) {
				targetNode.getChildren().add(targetNode.getChildren().size(), siblingNode.getChildren().get(0));
				siblingNode.getChildren().remove(0);
			}
		} else {
			int siblingIndex = index - 1;
			if(index == 0) {
				siblingIndex = index + 1;
			}
			if(siblingIndex < index) {
				merge(node, siblingIndex);
				newIndex = siblingIndex;
			} else {
				merge(node, index);
			}
			if(node.getNumKeys() == 0) {
				root = node.getChildren().get(0);
			}
		}
		
		return newIndex;
	}
	
	private void merge(Node parent, int index) {
		Node firstChild = parent.getChildren().get(index);
		Node secondChild = parent.getChildren().get(index + 1);
		firstChild.getKeys().add(parent.getKeys().get(index));
		firstChild.getKeys().addAll(secondChild.getKeys());
		parent.getKeys().remove(index);
		parent.getChildren().remove(index + 1);
		firstChild.getChildren().addAll(secondChild.getChildren());
	}
	
	private void deleteKey(Node node, T key) {
		int index = node.getKeys().indexOf(key);
		if(index == -1) {
			throw new RuntimeException("key " + key + " not found");
		}
		
		if(node.isLeaf()) {
			node.getKeys().remove(key);
			return;
		}
		
		if(node.getChildren().get(index).getNumKeys() >= t) {
			Node leftChild = node.getChildren().get(index);
			T newKey = leftChild.getKeys().get(leftChild.getKeys().size() - 1);
			if(leftChild.isLeaf()) {
				leftChild.getKeys().remove(newKey);
			} else {
				deleteKey(leftChild, newKey);
			}
			node.getKeys().set(index, newKey);
			return;
		}
		
		if(node.getChildren().get(index + 1).getNumKeys() >= t) {
			Node rightChild = node.getChildren().get(index + 1);
			T newKey = rightChild.getKeys().get(0);
			if(rightChild.isLeaf()) {
				rightChild.getKeys().remove(newKey);
			} else {
				deleteKey(rightChild, newKey);
			}
			node.getKeys().set(index, newKey);
			return;
		}
		
		Node leftChild = node.getChildren().get(index);
		Node rightChild = node.getChildren().get(index + 1);
		leftChild.getKeys().add(key);
		leftChild.getKeys().addAll(rightChild.getKeys());
		leftChild.getChildren().addAll(rightChild.getChildren());
		node.getChildren().remove(rightChild);
		node.getKeys().remove(key);
		
		if(node.getNumKeys() == 0) {
			root = leftChild;
		}
		
		deleteKey(leftChild, key);
	}
	
	private int getNodeCount(Node node) {
		if(node.isLeaf()) {
			return 1;
		}
		int count = 0;
		for(int i = 0 ; i < node.getChildren().size() ; i++) {
			count += getNodeCount(node.getChildren().get(i));
		}
		return count;
	}
	
	private void diskRead(Node node) {
		// Disk read logic should go here
	}
	
	private void diskWrite(Node node) {
		// Disk write logic should go here
	}
	
	private Node allocateNode() {
		// Allocates one page to a node
		Node newNode = new Node();
		return newNode;
	}
	
	class Node {
		
		private List<T> keys;
		
		private List<Node> children;
		
		public Node() {
			this.keys = new ArrayList<>();
			this.children = new ArrayList<>();
		}
			
		public int getNumKeys() {
			return keys.size();
		}
	
		public List<T> getKeys() {
			return keys;
		}
	
		public List<Node> getChildren() {
			return children;
		}
	
		public boolean isLeaf() {
			return children.isEmpty();
		}
	}
}
