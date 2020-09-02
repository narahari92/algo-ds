package com.techstuff.algods.graphs.mst;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.techstuff.algods.ds.advanced.DisjointSetForestNode;
import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedGraph;
import com.techstuff.algods.util.Tuple2;

public class Kruskal<T> {

	private WeightedGraph<Integer, T> graph;
	
	public Kruskal(WeightedGraph<Integer, T> graph) {
		this.graph = graph;
	}
	
	public List<Edge<Integer, T>> mst() {
		Map<Vertex<T>, DisjointSetForestNode<Vertex<T>>> disjointSetMap = new HashMap<>();
		for(Vertex<T> node : graph.getNodes()) {
			disjointSetMap.put(node, new DisjointSetForestNode<Vertex<T>>(node));
		}
		List<Edge<Integer, T>> mst = new ArrayList<>();
		for(Edge<Integer, T> edge : getSortedEdges()) {
			DisjointSetForestNode<Vertex<T>> source = disjointSetMap.get(edge.getSource());
			DisjointSetForestNode<Vertex<T>> destination = disjointSetMap.get(edge.getDestination());
			if(source.findSet().equals(destination.findSet())) {
				continue;
			}
			source.union(destination);
			mst.add(edge);
		}
		return mst;
	}
	
	private List<Edge<Integer, T>> getSortedEdges() {
		Stack<Vertex<T>> stack = new Stack<>();
		for(Vertex<T> node : graph.getNodes()) {
			node.setAttribute("color", "white");
		}
		
		List<Edge<Integer, T>> edgeList = new ArrayList<>();
		for(Vertex<T> node : graph.getNodes()) {
			if(!node.getAttribute("color").equals("white")) {
				continue;
			}
			for(Tuple2<Integer, Vertex<T>> edge : graph.getEdges(node)) {
				if(!edge.getSecond().getAttribute("color").equals("white")) {
					continue;
				}
				edgeList.add(new Edge<>(node, edge.getSecond(), edge.getFirst()));
			}
			node.setAttribute("color", "black");
		}
		
		Collections.sort(edgeList, new Comparator<Edge<Integer, T>>() {

			@Override
			public int compare(Edge<Integer, T> first, Edge<Integer, T> second) {
				return first.getAttributes() - second.getAttributes();
			}
		});
		
		return edgeList;
	}
}
