package com.techstuff.algods.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.techstuff.algods.util.Tuple2;

public class WeightedDirectedGraph<K, T> {

	private Map<Vertex<T>, List<Tuple2<K, Vertex<T>>>> graph;

	private Set<Vertex<T>> nodes;
	
	public WeightedDirectedGraph() {
		this.graph = new HashMap<>();
		this.nodes = new HashSet<>();
	}

	@SuppressWarnings("unchecked")
	public List<Tuple2<K, Vertex<T>>> getEdges(Vertex<T> node) {
		return graph.get(node) == null ? Collections.EMPTY_LIST : graph.get(node);
	}

	public Set<Vertex<T>> getNodes() {
		return nodes;
	}

	public void addEdge(Vertex<T> source, Vertex<T> destination, K edgeAttr) {
		if(!graph.containsKey(source)) {
			graph.put(source, new ArrayList<>());
		}
		graph.get(source).add(new Tuple2<>(edgeAttr, destination));
		nodes.add(source);
		nodes.add(destination);
	}
	
	public List<Edge<K, T>> getEdges() {
		List<Edge<K, T>> edges = new ArrayList<>();
		for(Map.Entry<Vertex<T>, List<Tuple2<K, Vertex<T>>>> entry : graph.entrySet()) {
			for(Tuple2<K, Vertex<T>> edge : entry.getValue()) {
				edges.add(new Edge<>(entry.getKey(), edge.getSecond(), edge.getFirst()));
			}
		}
		return edges;
	}
}
