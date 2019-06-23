package com.techstuff.algods.graphs;

import java.util.List;

import com.techstuff.algods.ds.Queue;

public class BFS<T> {

	private Graph<T> graph;
	
	public BFS(Graph<T> graph) {
		this.graph = graph;
	}
	
	public Graph<T> getGraph() {
		return graph;
	}
	
	public void calculate(Vertex<T> source) {
		Queue<Vertex<T>> queue = new Queue<>(graph.getNodes().size());
		for(Vertex<T> node : graph.getNodes()) {
			if(source.equals(node)) {
				node.setAttribute("color", "gray");
				node.setAttribute("distance", 0);
				node.setAttribute("ancestor", null);
				queue.enqueue(node);
				continue;
			}
			node.setAttribute("color", "white");
			node.setAttribute("distance", -1);
		}
		
		while (!queue.isEmpty()) {
			Vertex<T> node = queue.dequeue();
			List<Vertex<T>> neighbours = graph.getEdges(node);
			for(Vertex<T> neighbour : neighbours) {
				if(neighbour.getAttribute("color").equals("white")) {
					neighbour.setAttribute("color", "gray");
					neighbour.setAttribute("distance", Integer.parseInt(node.getAttribute("distance").toString()) + 1);
					neighbour.setAttribute("ancestor", node);
					queue.enqueue(neighbour);
				}
			}
			node.setAttribute("color", "black");
		}
	}
}
