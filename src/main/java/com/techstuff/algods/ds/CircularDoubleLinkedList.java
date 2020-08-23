package com.techstuff.algods.ds;

import java.util.ArrayList;
import java.util.List;

public class CircularDoubleLinkedList<T> {
	
	private Node start;
	
	public void insert(T data) {
		Node newNode = new Node(data);
		if(start == null) {
			newNode.next = newNode;
			newNode.prev = newNode;
			start = newNode;
			return;
		}
		Node last = start.prev;
		start.prev = newNode;
		newNode.next = start;
		last.next = newNode;
		newNode.prev = last;
	}
	
	public Node getStart() {
		return start;
	}
	
	public void insert(CircularDoubleLinkedList<T> other) {
		insert(other.start.data);
		Node newNode = other.start.next;
		while(newNode != other.start) {
			insert(newNode.data);
			newNode = newNode.next;
		}
	}
	
	public void remove(T data) {
		if(start.data.equals(data)) {
			if(start.next == start) {
				start = null;
				return;
			}
			start.next.prev = start.prev;
			start.prev.next = start.next;
			start = start.next;
			return;
		}
		Node node = start.next;
		while(!node.data.equals(data)) {
			if(node == start) {
				return;
			}
			node = node.next;
		}
		node.next.prev = node.prev;
		node.prev.next = node.next;
	}
	
	public boolean isEmpty() {
		return start == null;
	}
	
	public int size() {
		if(start == null) {
			return 0;
		}
		Node node = start;
		int size = 1;
		
		while(node.next != start) {
			size++;
			node = node.next;
		}
		return size;
	}
	
	public T right(T data) {
		Node node = findNode(data);
		if(node == null) {
			throw new RuntimeException(data + " not found");
		}
		return node.next.data;
	}
	
	private Node findNode(T data) {
		if(start.data.equals(data)) {
			return start;
		}
		Node node = start.next;
		while(!node.data.equals(data)) {
			if(node == start) {
				return null;
			}
			node = node.next;
		}
		return node;
	}
	
	// For Testing
	public List<T> getAsList() {
		List<T> retList = new ArrayList<>();
		if(start == null) {
			return retList;
		}
		retList.add(start.data);
		Node node = start.next;
		while(node != start) {
			retList.add(node.data);
			node = node.next;
		}
		return retList;
	}
	
	public void print() {
		if(start == null) {
			System.out.println("Empty List");
			return;
		}
		System.out.print(start.data);
		Node node = start.next;
		while(!node.equals(start)) {
			System.out.print(" - " + node.data);
			node = node.next;
		}
		System.out.println();
	}

	public class Node {
		
		private T data;
		
		private Node next;
		
		private Node prev;
		
		public Node(T data) {
			this.data = data;
		}
	}
}
