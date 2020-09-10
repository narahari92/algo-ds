package com.techstuff.algods.graphs.shortestpath;

import com.techstuff.algods.graphs.ComparableVertex;
import com.techstuff.algods.graphs.DirectedAdjacencyMatrixGraph;
import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

public class Johnson {
	
	private final String hValue = "hValue";

	private WeightedDirectedGraph<Double, Integer> graph;
	
	public Johnson(WeightedDirectedGraph<Double, Integer> graph) {
		this.graph = graph;
	}
	
	public DirectedAdjacencyMatrixGraph allPairShortestPath() {
		Vertex<Integer> source = new ComparableVertex<>(-1);   // dummy vertex
		graph.addNode(source);
		for(Vertex<Integer> node : graph.getNodes()) {
			if(source.equals(node)) {
				continue;
			}
			graph.addEdge(source, node, 0.0);
		}
		
		BellmanFord<Integer> bellmanFord = new BellmanFord<>(graph);
		boolean hasNoNegativeCycle = bellmanFord.calculateShortestPaths(source);
		if(!hasNoNegativeCycle) {
			throw new RuntimeException("Graph has negative weight cycle");
		}
		
		
		
		source.setAttribute(hValue, 0.0);
		for(Tuple2<Double, Vertex<Integer>> edge : graph.getEdges(source)) {
			edge.getSecond().setAttribute(hValue, edge.getSecond().getAttribute("distance"));
		}
		
		WeightedDirectedGraph<Double, Integer> reweightedGraph = new WeightedDirectedGraph<>();
		for(Edge<Double, Integer> edge : graph.getEdges()) {
			/*if(edge.getSource().equals(source)) {
				continue;
			}*/
			Double newEdgeWeight = edge.getAttributes() + 
					(Double)edge.getSource().getAttribute(hValue) - 
					(Double)edge.getDestination().getAttribute(hValue);
			reweightedGraph.addEdge(edge.getSource(), edge.getDestination(), newEdgeWeight);
		}
		
		DirectedAdjacencyMatrixGraph allPairShortestPaths = new DirectedAdjacencyMatrixGraph(graph.getNodes().size() - 1);
		for(Vertex<Integer> node : reweightedGraph.getNodes()) {
			if(node.equals(source)) {
				continue;
			}
			Dijkstra<Integer> dijkstra = new Dijkstra<>(reweightedGraph);
			dijkstra.calculateShortestPaths(node);
			
			for(Vertex<Integer> neighbour : reweightedGraph.getNodes()) {
				if(neighbour.equals(source)) {
					continue;
				}
				Double shortestPath = ((ComparableVertex<Integer>)neighbour).getKey() + 
						(Double)neighbour.getAttribute(hValue) - 
						(Double)node.getAttribute(hValue);
				allPairShortestPaths.addEdge(node.getPayload(), neighbour.getPayload(), (double)shortestPath);
			}
		}
		return allPairShortestPaths;
	}
}
