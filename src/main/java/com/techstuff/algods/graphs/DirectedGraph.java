package com.techstuff.algods.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<T> implements Graph<T> {
	
	private Map<Vertex<T>, List<Vertex<T>>> graph;
	
	private Set<Vertex<T>> nodes;
	
	public DirectedGraph(Map<Vertex<T>, List<Vertex<T>>> graph) {
		this.graph = graph;
		this.nodes = new HashSet<>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Vertex<T>> getEdges(Vertex<T> node) {
		return graph.get(node) == null ? Collections.EMPTY_LIST : graph.get(node);
	}
	
	public Set<Vertex<T>> getNodes() {
		return nodes;
	}
	
	@Override
	public void addEdge(Vertex<T> source, Vertex<T> destination) {
		if(!graph.containsKey(source)) {
			graph.put(source, new ArrayList<Vertex<T>>());
		}
		graph.get(source).add(destination);
		nodes.add(source);
		nodes.add(destination);
	}

	@Override
	public Graph<T> transpose() {
		Map<Vertex<T>, List<Vertex<T>>> transposeMap = new HashMap<>();
		for(Map.Entry<Vertex<T>, List<Vertex<T>>> entry : graph.entrySet()) {
			for(Vertex<T> destNode : entry.getValue()) {
				if(!transposeMap.containsKey(destNode)) {
					transposeMap.put(destNode, new ArrayList<Vertex<T>>());
				}
				transposeMap.get(destNode).add(entry.getKey());
			}
		}
		return new AdjacencyListGraph<>(transposeMap);
	}

}
