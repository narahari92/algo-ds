package com.techstuff.algods.graphs.flownetwork;

import org.junit.Test;

import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple3;

public class EdmondsKarpTest {

	@Test
	public void testOperation() {
		Tuple3<WeightedDirectedGraph<FlowProperty, String>, Vertex<String>, Vertex<String>> flowNetworkAndNodes = createFlowNetwork();
		EdmondsKarp<String> maxFlow = new EdmondsKarp<>(flowNetworkAndNodes.getFirst());
		maxFlow.calculate(flowNetworkAndNodes.getSecond(), flowNetworkAndNodes.getThird());
		for(Edge<FlowProperty, String> edge : flowNetworkAndNodes.getFirst().getEdges()) {
			System.out.println(edge.getSource().getPayload() + " -- " + edge.getAttributes().getFlow() + " -- " + edge.getDestination().getPayload());
		}
		/*
		 * Output should be as below
		 * S -- 12 -- V1
		 * S -- 11 -- V2
		 * V1 -- 12 -- V3
		 * V2 -- 0 -- V1
		 * V2 -- 11 -- V4
		 * V3 -- 0 -- V2
		 * V3 -- 19 -- T
		 * V4 -- 7 -- V3
		 * V4 -- 4 -- T
		 */
	}
	
	private Tuple3<WeightedDirectedGraph<FlowProperty, String>, Vertex<String>, Vertex<String>> createFlowNetwork() {
		WeightedDirectedGraph<FlowProperty, String> flowNetwork = new WeightedDirectedGraph<>();
		Vertex<String> vertexs = new Vertex<>("S");
		Vertex<String> vertexv1 = new Vertex<>("V1");
		Vertex<String> vertexv2 = new Vertex<>("V2");
		Vertex<String> vertexv3 = new Vertex<>("V3");
		Vertex<String> vertexv4 = new Vertex<>("V4");
		Vertex<String> vertext = new Vertex<>("T");
		flowNetwork.addEdge(vertexs, vertexv1, new FlowProperty(16));
		flowNetwork.addEdge(vertexs, vertexv2, new FlowProperty(13));
		flowNetwork.addEdge(vertexv1, vertexv3, new FlowProperty(12));
		flowNetwork.addEdge(vertexv2, vertexv1, new FlowProperty(4));
		flowNetwork.addEdge(vertexv2, vertexv4, new FlowProperty(14));
		flowNetwork.addEdge(vertexv3, vertexv2, new FlowProperty(9));
		flowNetwork.addEdge(vertexv3, vertext, new FlowProperty(20));
		flowNetwork.addEdge(vertexv4, vertexv3, new FlowProperty(7));
		flowNetwork.addEdge(vertexv4, vertext, new FlowProperty(4));
		return new Tuple3<>(flowNetwork, vertexs, vertext);
	}
}
