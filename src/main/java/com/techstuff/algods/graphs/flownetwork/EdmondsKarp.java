package com.techstuff.algods.graphs.flownetwork;

import java.util.List;
import java.util.Stack;

import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

public class EdmondsKarp<K> {
	
	private final String parentKey = "parent";
	
	private final String colorKey = "color";

	private WeightedDirectedGraph<FlowProperty, K> flowNetwork;
	
	public EdmondsKarp(WeightedDirectedGraph<FlowProperty, K> flowNetwork) {
		this.flowNetwork = flowNetwork;
	}
	
	public void calculate(Vertex<K> source, Vertex<K> destination) {
		WeightedDirectedGraph<FlowProperty, K> residualGraph = createResidualGraph();
		// True if there exists residual path from source to destination
		while(bfs(residualGraph, source, destination)) {
			int minCapacity = getMinCapactiyOnResidualPath(residualGraph, source, destination);
			augmentResidualPath(residualGraph, source, destination, minCapacity);
			clearParentReferences(residualGraph);
		}
	}
	
	private WeightedDirectedGraph<FlowProperty, K> createResidualGraph() {
		WeightedDirectedGraph<FlowProperty, K> residualGraph = new WeightedDirectedGraph<>();
		for(Vertex<K> node : flowNetwork.getNodes()) {
			List<Tuple2<FlowProperty, Vertex<K>>> edges = flowNetwork.getEdges(node);
			for(Tuple2<FlowProperty, Vertex<K>> edge : edges) {
				residualGraph.addEdge(node, edge.getSecond(), new FlowProperty(edge.getFirst()));
			}
		}
		return residualGraph;
	}
	
	private boolean bfs(WeightedDirectedGraph<FlowProperty, K> residualGraph, Vertex<K> source, Vertex<K> destination) {
		Stack<Vertex<K>> nodes = new Stack<>();
		source.setAttribute(colorKey, "gray");
		nodes.add(source);
		while(!nodes.isEmpty()) {
			Vertex<K> node = nodes.pop();
			for(Tuple2<FlowProperty, Vertex<K>> edge : residualGraph.getEdges(node)) {
				if(edge.getSecond().getAttribute(colorKey) != null &&
						edge.getSecond().getAttribute(colorKey).equals("gray")) {
					continue;
				}
				// Storing edge capacity(for easy retrieval) and parent node.
				edge.getSecond().setAttribute(parentKey, new Tuple2<Integer, Vertex<K>>(edge.getFirst().getCapacity(), node));
				if(edge.getSecond().equals(destination)) {
					return true;
				}
				edge.getSecond().setAttribute(colorKey, "gray");
				nodes.add(edge.getSecond());
			}
		}
		return false;
	}
	
	private void clearParentReferences(WeightedDirectedGraph<FlowProperty, K> residualGraph) {
		for(Vertex<K> node : residualGraph.getNodes()) {
			node.setAttribute(parentKey, null);
			node.setAttribute(colorKey, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	private int getMinCapactiyOnResidualPath(WeightedDirectedGraph<FlowProperty, K> residualGraph, 
			Vertex<K> source, Vertex<K> destination) {
		int minCapacity = Integer.MAX_VALUE;
		Vertex<K> toNode = destination;
		while(!toNode.equals(source)) {
			Tuple2<Integer, Vertex<K>> from = (Tuple2<Integer, Vertex<K>>)toNode.getAttribute(parentKey);
			if(minCapacity > from.getFirst()) {
				minCapacity = from.getFirst();
			}
			toNode = from.getSecond();
		}
		return minCapacity;
	}
	
	@SuppressWarnings("unchecked")
	private void augmentResidualPath(WeightedDirectedGraph<FlowProperty, K> residualGraph, 
			Vertex<K> source, Vertex<K> destination, int minCapacity) {
		Vertex<K> toNode = destination;
		while(!toNode.equals(source)) {
			Tuple2<Integer, Vertex<K>> from = (Tuple2<Integer, Vertex<K>>)toNode.getAttribute(parentKey);
			int newForwardCapacity = from.getFirst() - minCapacity;
			if(newForwardCapacity == 0) {
				residualGraph.deleteEdge(from.getSecond(), toNode);
			} else {
				residualGraph.modifyEdge(from.getSecond(), toNode, new FlowProperty(newForwardCapacity));
			}
			// Reverse edge already exists in residual graph
			if(residualGraph.edgeExists(toNode, from.getSecond())) {
				FlowProperty edgeAttr = new FlowProperty(from.getFirst() + minCapacity);
				residualGraph.modifyEdge(toNode, from.getSecond(), edgeAttr);
			} else {
				residualGraph.addEdge(toNode, from.getSecond(), new FlowProperty(minCapacity));
			}
			if(flowNetwork.edgeExists(from.getSecond(), toNode)) {
				FlowProperty edge = flowNetwork.getEdgeAttribute(from.getSecond(), toNode);
				edge.setFlow(edge.getFlow() + minCapacity);
			} else {
				FlowProperty edge = flowNetwork.getEdgeAttribute(toNode, from.getSecond());
				edge.setFlow(edge.getFlow() - minCapacity);
			}
			toNode = from.getSecond();
		}
	}
}
