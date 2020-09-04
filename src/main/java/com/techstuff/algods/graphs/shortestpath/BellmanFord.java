package com.techstuff.algods.graphs.shortestpath;

import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;

/**
 * Finding shortest path from single source to all reachable vertices and 
 * graph might contain non-negative loops
 */
public class BellmanFord<T> {
	
	private final String distanceKey = "distance";
	
	private final String parentKey = "parent";

	private WeightedDirectedGraph<Integer, T> graph;
	
	public BellmanFord(WeightedDirectedGraph<Integer, T> graph) {
		this.graph = graph;
	}
	
	public boolean calculateShortestPaths(Vertex<T> source) {
		initialise(source);
		for(int i = 1 ; i < graph.getNodes().size() ; i++) {
			for(Edge<Integer, T> edge : graph.getEdges()) {
				Integer sourceDistance = (Integer)edge.getSource().getAttribute(distanceKey);
				Integer destinationDistance = (Integer)edge.getDestination().getAttribute(distanceKey);
				Integer edgeWeight = edge.getAttributes();
				if(sourceDistance == Integer.MAX_VALUE) {
					continue;
				}
				if(destinationDistance > sourceDistance + edgeWeight) {
					edge.getDestination().setAttribute(distanceKey, sourceDistance + edgeWeight);
					edge.getDestination().setAttribute(parentKey, edge.getSource());
				}
			}
		}
		
		for(Edge<Integer, T> edge : graph.getEdges()) {
			Integer sourceDistance = (Integer)edge.getSource().getAttribute(distanceKey);
			Integer destinationDistance = (Integer)edge.getDestination().getAttribute(distanceKey);
			Integer edgeWeight = edge.getAttributes();
			if(destinationDistance > sourceDistance + edgeWeight) {
				return false;
			}
		}
		return true;
	}
	
	private void initialise(Vertex<T> source) {
		for(Vertex<T> node : graph.getNodes()) {
			node.setAttribute(distanceKey, Integer.MAX_VALUE);
			node.setAttribute(parentKey, null);
		}
		source.setAttribute(distanceKey, 0);
	}
}
