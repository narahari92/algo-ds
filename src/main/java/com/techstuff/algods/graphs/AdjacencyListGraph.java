package com.techstuff.algods.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//This is undirected graph
public class AdjacencyListGraph<T> implements Graph<T> {

	private Map<Vertex<T>, List<Vertex<T>>> graph;
	
	public AdjacencyListGraph(Map<Vertex<T>, List<Vertex<T>>> graph) {
		this.graph = graph;
	}
	
	public List<Vertex<T>> getEdges(Vertex<T> node) {
		return graph.get(node);
	}
	
	public Set<Vertex<T>> getNodes() {
		return graph.keySet();
	}
	
	@Override
	public void addEdge(Vertex<T> source, Vertex<T> destination) {
		if(!graph.containsKey(source)) {
			graph.put(source, new ArrayList<Vertex<T>>());
		}
		graph.get(source).add(destination);
		if(!graph.containsKey(destination)) {
			graph.put(destination, new ArrayList<Vertex<T>>());
		}
		graph.get(destination).add(source);
	}

}
