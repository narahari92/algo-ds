package com.techstuff.algods.ds.advanced;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDisjointSet<T> {
	
	Node<T> head;
	
	Node<T> tail;
	
	// Same as makeSet method
	public LinkedListDisjointSet(T object) {
		Node<T> node = new Node<>(this);
		node.payload = object;
		this.head = node;
		this.tail = node;
	}
	
	public void union(LinkedListDisjointSet<T> other) {
		tail.next = other.head;
		Node<T> node = other.head;
		while(node != null) {
			node.set = this;
			node = node.next;
		}
		tail = other.tail;
	}
	
	// For testing
	List<T> getAsList() {
		List<T> returnList = new ArrayList<>();
		Node<T> node = head;
		while(node != null) {
			returnList.add(node.payload);
			node = node.next;
		}
		return returnList;
	}
	
	// For testing
	Node<T> getNode(T object) {
		Node node = head;
		while(node != null) {
			if(node.payload.equals(object)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	public static class Node<T> {
		
		private T payload;
		
		private Node<T> next;
		
		private LinkedListDisjointSet<T> set;
		
		public Node(LinkedListDisjointSet<T> set) {
			this.set = set;
		}
		
		public Node<T> findSet() {
			return set.head;
		}
	}
}
