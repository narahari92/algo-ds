package com.techstuff.algods.graphs.flownetwork;

public class FlowProperty {

	private int capacity;
	
	private int flow;
	
	public FlowProperty(int capacity) {
		this.capacity = capacity;
		this.flow = 0;
	}
	
	public FlowProperty(FlowProperty other) {
		this.capacity = other.capacity;
		this.flow = other.flow;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}
}
