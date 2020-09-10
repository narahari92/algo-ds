package com.techstuff.algods.graphs;

public class ComparableVertex<T> extends Vertex<T> implements Comparable<ComparableVertex<T>> {
	
	private Double key;

	public ComparableVertex(T payload) {
		super(payload);
	}
	
	public Double getKey() {
		return key;
	}

	public void setKey(Double key) {
		this.key = key;
	}

	@Override
	public int compareTo(ComparableVertex<T> o) {
		return (int)(this.key - o.key);
	}
}
