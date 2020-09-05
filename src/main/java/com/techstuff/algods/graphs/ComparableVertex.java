package com.techstuff.algods.graphs;

public class ComparableVertex<T> extends Vertex<T> implements Comparable<ComparableVertex<T>> {
	
	private Integer key;

	public ComparableVertex(T payload) {
		super(payload);
	}
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	@Override
	public int compareTo(ComparableVertex<T> o) {
		return this.key - o.key;
	}
}
