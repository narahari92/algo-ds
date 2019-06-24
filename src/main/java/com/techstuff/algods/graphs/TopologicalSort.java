package com.techstuff.algods.graphs;

import java.util.LinkedList;
import java.util.List;

// Graph passed should be DAG(Directed Acyclic Graph)
public class TopologicalSort<T> extends DFS<T> {
	
	LinkedList<Vertex<T>> list = new LinkedList<>();

	public TopologicalSort(Graph<T> graph) {
		super(graph);
	}
	
	@Override
	public void afterFinish(Vertex<T> node) {
		list.addFirst(node);
	}
	
	public List<Vertex<T>> getTopologicalOrder() {
		return list;
	}
}
