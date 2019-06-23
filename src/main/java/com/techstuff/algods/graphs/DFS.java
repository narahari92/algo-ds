package com.techstuff.algods.graphs;

//Depth First Search
public class DFS<T> {
	
	private Graph<T> graph;
	
	private int time = 0;
	
	public DFS(Graph<T> graph) {
		this.graph = graph;
	}
	
	public void calculate() {
		for(Vertex<T> node : graph.getNodes()) {
			node.setAttribute("color", "white");
		}
		
		for(Vertex<T> node : graph.getNodes()) {
			if(node.getAttribute("color").equals("white")) {
				dfsVisit(node);
			}
		}
	}
	
	private void dfsVisit(Vertex<T> node) {
		node.setAttribute("color", "gray");
		node.setAttribute("start", ++time);
		for(Vertex<T> neighbour : graph.getEdges(node)) {
			if(neighbour.getAttribute("color").equals("white")) {
				neighbour.setAttribute("ancestor", node);
				dfsVisit(neighbour);
			}
		}
		node.setAttribute("color", "black");
		node.setAttribute("end", ++time);
	}

}
