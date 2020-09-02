package com.techstuff.algods.graphs;

public class Edge<K, T> {

	private Vertex<T> source;
	
	private Vertex<T> destination;
	
	private K attributes;
	
	public Edge(Vertex<T> source, Vertex<T> destination, K attributes) {
		this.source = source;
		this.destination = destination;
		this.attributes = attributes;
	}

	public Vertex<T> getSource() {
		return source;
	}

	public Vertex<T> getDestination() {
		return destination;
	}
	
	public K getAttributes() {
		return attributes;
	}
}
