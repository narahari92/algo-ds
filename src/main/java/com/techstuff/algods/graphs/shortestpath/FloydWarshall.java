package com.techstuff.algods.graphs.shortestpath;

import com.techstuff.algods.graphs.DirectedAdjacencyMatrixGraph;

public class FloydWarshall {

	private DirectedAdjacencyMatrixGraph graph;
	
	public FloydWarshall(DirectedAdjacencyMatrixGraph graph) {
		this.graph = graph;
	}
	
	public DirectedAdjacencyMatrixGraph allPairShortestPath() {
		DirectedAdjacencyMatrixGraph stage = graph.clone();
		for(int k = 0 ; k < graph.getNodeCount() ; k++) {
			DirectedAdjacencyMatrixGraph nextStage = new DirectedAdjacencyMatrixGraph(graph.getNodeCount());
			for(int i = 0 ; i < graph.getNodeCount() ; i++) {
				for(int j = 0 ; j < graph.getNodeCount() ; j++) {
					if(stage.getEdgeWeight(i, j) <= stage.getEdgeWeight(i, k) + stage.getEdgeWeight(k, j)) {
						nextStage.setEdge(i, j, stage.getEdgeWeight(i, j));
					} else {
						nextStage.setEdge(i, j, stage.getEdgeWeight(i, k) + stage.getEdgeWeight(k, j));
					}
				}
			}
			stage = nextStage;
		}
		return stage;
	}
}
