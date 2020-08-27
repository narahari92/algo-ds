package com.techstuff.algods.ds.advanced;

import com.techstuff.algods.ds.advanced.LinkedListDisjointSet.Node;

public class DisjointSetForest<T> {
	
	private Node<T> root;
	
	// Same as makeSet method
	public DisjointSetForest(T data) {
		root = new Node<>(data);
	}
	
	public DisjointSetForest(Node<T> node) {
		root = node;
	}
	
	public void union(DisjointSetForest<T> other) {
		if(root.rank > other.root.rank) {
			other.root.parent = root;
			return;
		}
		root.parent = other.root;
		if(root.rank == other.root.rank) {
			other.root.rank += 1;
		}
		root = other.root;
	}

	public static class Node<T> {
		
		private T data;
		
		private Node<T> parent;
		
		private int rank;
		
		public Node(T data) {
			this.data = data;
			this.parent = this;
			rank = 0;
		}
		
		public Node<T> findSet() {
			if(!this.parent.equals(this)) {
				this.parent = this.parent.findSet();
			}
			return this.parent;
		}
	}
}
