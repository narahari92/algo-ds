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

	private WeightedDirectedGraph<Double, T> graph;
	
	public BellmanFord(WeightedDirectedGraph<Double, T> graph) {
		this.graph = graph;
	}
	
	public boolean calculateShortestPaths(Vertex<T> source) {
		initialise(source);
		for(int i = 1 ; i < graph.getNodes().size() ; i++) {
			for(Edge<Double, T> edge : graph.getEdges()) {
				Double sourceDistance = (Double)edge.getSource().getAttribute(distanceKey);
				Double destinationDistance = (Double)edge.getDestination().getAttribute(distanceKey);
				Double edgeWeight = edge.getAttributes();

				if(destinationDistance > sourceDistance + edgeWeight) {
					edge.getDestination().setAttribute(distanceKey, sourceDistance + edgeWeight);
					edge.getDestination().setAttribute(parentKey, edge.getSource());
				}
			}
		}
		
		for(Edge<Double, T> edge : graph.getEdges()) {
			Double sourceDistance = (Double)edge.getSource().getAttribute(distanceKey);
			Double destinationDistance = (Double)edge.getDestination().getAttribute(distanceKey);
			Double edgeWeight = edge.getAttributes();
			if(destinationDistance > sourceDistance + edgeWeight) {
				return false;
			}
		}
		return true;
	}
	
	private void initialise(Vertex<T> source) {
		for(Vertex<T> node : graph.getNodes()) {
			node.setAttribute(distanceKey, Double.POSITIVE_INFINITY);
			node.setAttribute(parentKey, null);
		}
		source.setAttribute(distanceKey, 0.0);
	}
}
