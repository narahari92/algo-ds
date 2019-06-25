package com.techstuff.algods.graphs;

import java.util.List;
import java.util.Set;

public interface Graph<T> {

	public List<Vertex<T>> getEdges(Vertex<T> node);
	
	public Set<Vertex<T>> getNodes();
	
	public void addEdge(Vertex<T> source, Vertex<T> destination);
	
	public Graph<T> transpose();
	
}
