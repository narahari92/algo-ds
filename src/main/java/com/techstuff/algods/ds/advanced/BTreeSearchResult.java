package com.techstuff.algods.ds.advanced;

public class BTreeSearchResult<T extends Comparable<T>> {

	private BTree<T>.Node node;
	
	private int index;

	public BTreeSearchResult(BTree<T>.Node node, int index) {
		this.node = node;
		this.index = index;
	}

	public BTree<T>.Node getNode() {
		return node;
	}

	public int getIndex() {
		return index;
	}
}
