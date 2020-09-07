package com.techstuff.algods.graphs.shortestpath;

import com.techstuff.algods.graphs.DirectedAdjacencyMatrixGraph;

public class AllPairShortestPath {

	private DirectedAdjacencyMatrixGraph graph;
	
	public AllPairShortestPath(DirectedAdjacencyMatrixGraph graph) {
		this.graph = graph;
	}
	
	public DirectedAdjacencyMatrixGraph allPairShortestPath() {
		int nodeCount = graph.getNodeCount();
		int m = 1;
		DirectedAdjacencyMatrixGraph stage = graph.clone();
		while(m < nodeCount - 1) {
			DirectedAdjacencyMatrixGraph newStage = shortestPathStage(stage, stage);
			stage = newStage;
			m = 2 * m;
		}
		return stage;
	}
	
	public DirectedAdjacencyMatrixGraph shortestPathStage(DirectedAdjacencyMatrixGraph graph, 
			DirectedAdjacencyMatrixGraph stage) {
		DirectedAdjacencyMatrixGraph mthGraph = new DirectedAdjacencyMatrixGraph(stage.getNodeCount());
		for(int i = 0 ; i < stage.getNodeCount() ; i++) {
			for(int j = 0 ; j < stage.getNodeCount() ; j++) {
				mthGraph.addEdge(i, j, Double.POSITIVE_INFINITY);
				for(int k = 0 ; k < stage.getNodeCount() ; k++) {
					if(mthGraph.getEdgeWeight(i, j) > stage.getEdgeWeight(i, k) + graph.getEdgeWeight(k, j)) {
						mthGraph.setEdge(i, j, stage.getEdgeWeight(i, k) + graph.getEdgeWeight(k, j));
					}
				}
			}
		}
		return mthGraph;
	}
}
