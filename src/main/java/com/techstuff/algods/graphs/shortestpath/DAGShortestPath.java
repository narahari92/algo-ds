package com.techstuff.algods.graphs.shortestpath;

import java.util.LinkedList;
import java.util.List;

import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

/**
 * Finding shortest path from single source to all reachable vertices and 
 * graph is a DAG and might contain non-negative weighted edges
 */
public class DAGShortestPath<T> {
	
	private final String distanceKey = "distance";
	
	private final String parentKey = "parent";

	private WeightedDirectedGraph<Integer, T> graph;
	
	public DAGShortestPath(WeightedDirectedGraph<Integer, T> graph) {
		this.graph = graph;
	}
	
	public void calculateShortestPaths(Vertex<T> source) {
		for(Vertex<T> node : graph.getNodes()) {
			node.setAttribute(distanceKey, Integer.MAX_VALUE);
			node.setAttribute(parentKey, null);
		}
		source.setAttribute(distanceKey, 0);
		List<Vertex<T>> sortedList = topologicalSort();
		for(Vertex<T> node : sortedList) {
			Integer sourceDistance = (Integer)node.getAttribute(distanceKey);
			if(sourceDistance == Integer.MAX_VALUE) {
				continue;
			}
			for(Tuple2<Integer, Vertex<T>> edge : graph.getEdges(node)) {
				Integer destinationDistance = (Integer)edge.getSecond().getAttribute(distanceKey);
				Integer edgeWeight = edge.getFirst();
				if(destinationDistance > sourceDistance + edgeWeight) {
					edge.getSecond().setAttribute(distanceKey, destinationDistance + edgeWeight);
					edge.getSecond().setAttribute("parent", source);
				}
			}
		}
	}
	
	private List<Vertex<T>> topologicalSort() {
		LinkedList<Vertex<T>> list = new LinkedList<>();
		for(Vertex<T> node : graph.getNodes()) {
			node.setAttribute("color", "white");
		}
		
		for(Vertex<T> node : graph.getNodes()) {
			if(node.getAttribute("color").equals("white")) {
				dfsVisit(node, list);
			}
		}
		return list;
	}
	
	private void dfsVisit(Vertex<T> node, LinkedList<Vertex<T>> list) {
		node.setAttribute("color", "gray");
		for(Tuple2<Integer, Vertex<T>> edge : graph.getEdges(node)) {
			if(edge.getSecond().getAttribute("color").equals("white")) {
				dfsVisit(edge.getSecond(), list);
			}
		}
		node.setAttribute("color", "black");
		list.addFirst(node);
	}
}
