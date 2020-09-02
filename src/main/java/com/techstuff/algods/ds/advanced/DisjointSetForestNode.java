package com.techstuff.algods.ds.advanced;

import com.techstuff.algods.ds.advanced.DisjointSetForest.Node;

public class DisjointSetForestNode<T> {

	private T data;
	
	private DisjointSetForestNode<T> parent;
	
	private int rank;
	
	public DisjointSetForestNode(T data) {
		this.data = data;
		this.parent = this;
		rank = 0;
	}
	
	public DisjointSetForestNode<T> findSet() {
		if(!this.parent.equals(this)) {
			this.parent = this.parent.findSet();
		}
		return this.parent;
	}
	
	public void union(DisjointSetForestNode<T> other) {
		DisjointSetForestNode<T> root = this.findSet();
		DisjointSetForestNode<T> otherRoot = other.findSet();
		if(root.rank > otherRoot.rank) {
			otherRoot.parent = root;
			return;
		}
		root.parent = otherRoot;
		if(root.rank == otherRoot.rank) {
			otherRoot.rank += 1;
		}
	}
}
