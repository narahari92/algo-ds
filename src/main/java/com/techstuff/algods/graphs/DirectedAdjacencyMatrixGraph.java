package com.techstuff.algods.graphs;

import java.util.ArrayList;
import java.util.List;

public class DirectedAdjacencyMatrixGraph {

	private List<List<Double>> graph;
	
	public DirectedAdjacencyMatrixGraph(int vertexCount) {
		this.graph = new ArrayList<>(vertexCount);
		for(int i = 0 ; i < vertexCount ; i++) {
			List<Double> row = new ArrayList<>(vertexCount);
			this.graph.add(row);
			for(int j = 0 ; j < vertexCount ; j++) {
				if(i == j) {
					row.add(0.0);
				} else {
					row.add(Double.POSITIVE_INFINITY);
				}
			}
		}
	}
	
	public void addEdge(Integer source, Integer destination, Double weight) {
		graph.get(source).set(destination, weight);
	}
	
	public void setEdge(Integer source, Integer destination, Double weight) {
		graph.get(source).set(destination, weight);
	}
	
	public int getNodeCount() {
		return graph.size();
	}
	
	public Double getEdgeWeight(Integer source, Integer destination) {
		return graph.get(source).get(destination);
	}
	
	public DirectedAdjacencyMatrixGraph clone() {
		DirectedAdjacencyMatrixGraph clone = new DirectedAdjacencyMatrixGraph(this.getNodeCount());
		for(int i = 0 ; i < getNodeCount() ; i++) {
			for(int j = 0 ; j < getNodeCount() ; j++) {
				clone.setEdge(i, j, getEdgeWeight(i, j));
			}
		}
		return clone;
	}
	
	public List<List<Double>> getGraph() {
		DirectedAdjacencyMatrixGraph clone = clone();
		return clone.graph;
	}
}
