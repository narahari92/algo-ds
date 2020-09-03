package com.techstuff.algods.graphs;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T> {
	
	protected T payload;

	protected Map<String, Object> attributes = new HashMap<>();
	
	public Vertex(T payload) {
		this.payload = payload;
	}
	
	public T getPayload() {
		return payload;
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}
	
	// We neglect attributes while checking for equality
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertex<?>)) {
			return false;
		}
		Vertex<?> other = (Vertex<?>)obj;
		if(this.payload == null || other.payload == null) {
			return this.payload == null && other.payload == null;
		}
		return this.payload.equals(other.payload) ;
	}
	
	// We neglect attributes while calculating hashCode
	@Override
	public int hashCode() {
		if(this.payload == null) {
			return 0;
		}
		return this.payload.hashCode();
	}
}
