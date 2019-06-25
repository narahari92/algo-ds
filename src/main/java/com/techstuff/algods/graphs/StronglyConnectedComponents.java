package com.techstuff.algods.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StronglyConnectedComponents<T> {
	
	private Graph<T> graph;
	
	private List<List<Vertex<T>>> sccs = new ArrayList<>();
	
	public StronglyConnectedComponents(Graph<T> graph) {
		this.graph = graph;
	}
	
	public void calculate() {
		DFS<T> dfs1 = new DFS<>(graph);
		dfs1.calculate();
		List<Vertex<T>> nodes = new ArrayList<>(graph.getNodes());
		Collections.sort(nodes, new Comparator<Vertex<T>>() {
			@Override
			public int compare(Vertex<T> o1, Vertex<T> o2) {
				int firstEndTime = Integer.parseInt(o1.getAttribute("end").toString());
				int secondEndTime = Integer.parseInt(o2.getAttribute("end").toString());
				return secondEndTime - firstEndTime;
			}
		});
		Graph<T> transposeGraph = graph.transpose();
		Map<Vertex<T>, List<Vertex<T>>> secondGraphMap = new LinkedHashMap<>();
		for(Vertex<T> node : nodes) {
			secondGraphMap.put(node, transposeGraph.getEdges(node));
		}
		Graph<T> secondGraph = new AdjacencyListGraph<>(secondGraphMap);
		DFS<T> dfs2 = new DFS<T>(secondGraph) {
			
			private List<Vertex<T>> list;
			
			@Override
			public void beforeNewSource(Vertex<T> node) {
				list = new ArrayList<>();
			}
			
			public void beforeStart(Vertex<T> node) {
				list.add(node);
			}
			
			public void afterNewSource(Vertex<T> node) {
				sccs.add(list);
			}
		};
		dfs2.calculate();
	}
	
	public List<List<Vertex<T>>> getSccs() {
		return sccs;
	}
}
