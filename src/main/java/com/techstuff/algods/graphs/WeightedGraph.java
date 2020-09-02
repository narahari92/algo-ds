package com.techstuff.algods.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.techstuff.algods.util.Tuple2;

public class WeightedGraph<K, T> {
	
	private Map<Vertex<T>, List<Tuple2<K, Vertex<T>>>> graph;
	
	public WeightedGraph() {
		this.graph = new HashMap<>();
	}

	public List<Tuple2<K, Vertex<T>>> getEdges(Vertex<T> node) {
		return graph.get(node);
	}

	public Set<Vertex<T>> getNodes() {
		return graph.keySet();
	}

	public void addEdge(Vertex<T> source, Vertex<T> destination, K edgeAttr) {
		if(!graph.containsKey(source)) {
			graph.put(source, new ArrayList<>());
		}
		graph.get(source).add(new Tuple2<>(edgeAttr, destination));
		if(!graph.containsKey(destination)) {
			graph.put(destination, new ArrayList<>());
		}
		graph.get(destination).add(new Tuple2<>(edgeAttr, source));
	}

}
