package com.techstuff.algods.ds;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> {

    private List<T> sequence = new ArrayList<>();
    
    private int direction;
    
    private int heapSize;
    
    public Heap(List<T> sequence, boolean nonDescending) {
        for(T elem : sequence) {
            this.sequence.add(elem);
        }
        this.direction = nonDescending ? -1 : 1;
        this.heapSize = this.sequence.size();
        buildHeep();
    }
    
    public int leftIndex(int index) {
        return 2 * index + 1;
    }
    
    public int rightIndex(int index) {
        return 2 * index + 2;
    }
    
    public int parentIndex(int index) {
        return (index - 1) / 2;
    }
    
    public int heapSize() {
        return heapSize;
    }
    
    public void decrementHeapSize() {
        heapSize--;
    }
    
    public void incrementHeapSize() {
        heapSize++;
    }
    
    public void exchange(int from, int to) {
        T fromElem = sequence.get(from);
        sequence.set(from, sequence.get(to));
        sequence.set(to, fromElem);
    }
    
    public void heapify(int index) {
        int left = leftIndex(index);
        int right = rightIndex(index);
        int xchIndex;
        if(left < heapSize() && sequence.get(index).compareTo(sequence.get(left)) * direction < 0) {
            xchIndex = left;
        } else {
            xchIndex = index;
        }
        if(right < heapSize() && sequence.get(xchIndex).compareTo(sequence.get(right)) * direction < 0) {
            xchIndex = right;
        }
        if(xchIndex != index) {
            exchange(index, xchIndex);
            heapify(xchIndex);
        }
    }
    
    public void buildHeep() {
        for(int i = heapSize() / 2 - 1 ; i >= 0 ; i--) {
            heapify(i);
        }
    }
    
    public List<T> backingSequence() {
        return sequence;
    }
}
