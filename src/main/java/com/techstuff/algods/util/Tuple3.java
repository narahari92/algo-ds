package com.techstuff.algods.util;

public class Tuple3<K, L, M> {

    private K first;
    
    private L second;
    
    private M third;
    
    public Tuple3(K first, L second, M third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    public K getFirst() {
        return first;
    }
    
    public L getSecond() {
        return second;
    }
    
    public M getThird() {
        return third;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Tuple3)) {
            return false;
        }
        Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) obj;
        return this.first.equals(other.first) && this.second.equals(other.second) && 
                this.third.equals(other.third);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 3;
        int prime = 31;
        hashCode = prime * hashCode + first.hashCode();
        hashCode = prime * hashCode + second.hashCode();
        hashCode = prime * hashCode + third.hashCode();
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "[" + first + ", " + second + ", " + third + "]";
    }
}
