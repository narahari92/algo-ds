package com.techstuff.algods.graphs.mst;

import com.techstuff.algods.graphs.Vertex;

public class PrimVertex<T> extends Vertex<T> implements Comparable<PrimVertex<T>> {
	
	private Integer key;

	public PrimVertex(T payload) {
		super(payload);
	}
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	@Override
	public int compareTo(PrimVertex<T> o) {
		return this.key - o.key;
	}
}
