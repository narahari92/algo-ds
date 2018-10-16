package com.techstuff.algods.util;

public class Tuple2<T, K> {
    
    private T first;
    
    private K second;
    
    public Tuple2(T first, K second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() {
        return first;
    }
    
    public K getSecond() {
        return second;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Tuple2)) {
            return false;
        }
        Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
        return this.first.equals(other.first) && this.second.equals(other.second);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 3;
        int prime = 31;
        hashCode = prime * hashCode + first.hashCode();
        hashCode = prime * hashCode + second.hashCode();
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "[" + first + ", " + second + "]";
    }

}
