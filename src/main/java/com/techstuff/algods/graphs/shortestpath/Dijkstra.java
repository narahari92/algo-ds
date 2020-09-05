package com.techstuff.algods.graphs.shortestpath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.techstuff.algods.ds.advanced.FibonacciHeap;
import com.techstuff.algods.graphs.ComparableVertex;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

/**
 * Finding shortest path from single source to all reachable vertices and 
 * graph is a directed graph and contains only non-negative edge weights.
 */
public class Dijkstra<T> {
	
	private final String parentKey = "parent";

	private WeightedDirectedGraph<Integer, T> graph;
	
	public Dijkstra(WeightedDirectedGraph<Integer, T> graph) {
		this.graph = graph;
	}
	
	public void calculateShortestPaths(Vertex<T> source) {
		FibonacciHeap<ComparableVertex<T>> minPriorityQueue = new FibonacciHeap<>();
		Map<Vertex<T>, FibonacciHeap<ComparableVertex<T>>.Node> vertexHeapMap = new HashMap<>();
		for(Vertex<T> node : graph.getNodes()) {
			((ComparableVertex<T>)node).setKey(Integer.MAX_VALUE);
			if(source.equals(node)) {
				((ComparableVertex<T>)node).setKey(0);
			}
			node.setAttribute(parentKey, null);
			vertexHeapMap.put(node, minPriorityQueue.insert((ComparableVertex<T>)node));
		}
		Set<Vertex<T>> set = new HashSet<>();
		ComparableVertex<T> min = null;
		while((min = minPriorityQueue.extractMin()) != null) {
			set.add(min);
			if(min.getKey() == Integer.MAX_VALUE) {
				continue;
			}
			for(Tuple2<Integer, Vertex<T>> edge : graph.getEdges(min)) {
				if(((ComparableVertex<T>)edge.getSecond()).getKey() > min.getKey() + edge.getFirst()) {
					((ComparableVertex<T>)edge.getSecond()).setKey(min.getKey() + edge.getFirst());
					edge.getSecond().setAttribute(parentKey, min);
				}
			}
		}
	}
}
