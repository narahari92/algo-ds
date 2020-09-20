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
	
	public K getEdgeAttribute(Vertex<T> source, Vertex<T> destination) {
		for(Tuple2<K, Vertex<T>> edge : getEdges(source)) {
			if(edge.getSecond().equals(destination)) {
				return edge.getFirst();
			}
		}
		return null;
	}

	public Set<Vertex<T>> getNodes() {
		return nodes;
	}

	public void addEdge(Vertex<T> source, Vertex<T> destination, K edgeAttr) {
		if(!graph.containsKey(source)) {
			graph.put(source, new ArrayList<>());
		}
		graph.get(source).add(new Tuple2<>(edgeAttr, destination));
		if(!nodes.contains(source)) {
			nodes.add(source);
		}
		if(!nodes.contains(destination)) {
			nodes.add(destination);
		}
	}
	
	public boolean edgeExists(Vertex<T> source, Vertex<T> destination) {
		return edgeIndex(source, destination) != -1;
	}
	
	public void modifyEdge(Vertex<T> source, Vertex<T> destination, K edgeAttr) {
		int nodeIndex = edgeIndex(source, destination);
		if(nodeIndex != -1) {
			getEdges(source).set(nodeIndex, new Tuple2<>(edgeAttr, destination));
		}
	}
	
	public void deleteEdge(Vertex<T> source, Vertex<T> destination) {
		int nodeIndex = edgeIndex(source, destination);
		if(nodeIndex != -1) {
			getEdges(source).remove(nodeIndex);
		}
	}
	
	public void addNode(Vertex<T> node) {
		nodes.add(node);
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
	
	private int edgeIndex(Vertex<T> source, Vertex<T> destination) {
		int edgeIndex = -1;
		boolean found = false;
		for(Tuple2<K, Vertex<T>> edge : getEdges(source)) {
			edgeIndex++;
			if(edge.getSecond().equals(destination)) {
				found = true;
				break;
			}
		}
		if(!found) {
			return -1;
		}
		return edgeIndex;
	}
}
