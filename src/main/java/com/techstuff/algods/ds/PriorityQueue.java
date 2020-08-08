package com.techstuff.algods.ds;

import java.util.List;

public class PriorityQueue<T extends Comparable<T>> {

    private Heap<T> heap;
    
    public PriorityQueue(List<T> sequence) {
        this.heap = new Heap<>(sequence, false);
    }
    
    public T getMaximum() {
        return heap.backingSequence().get(0);
    }
    
    public T extractMaximum() {
        if(heap.heapSize() < 1) {
            throw new RuntimeException("Heap Underflow");
        }
        T max = heap.backingSequence().get(0);
        heap.backingSequence().set(0, heap.backingSequence().get(heap.heapSize() - 1));
        heap.decrementHeapSize();
        heap.heapify(0);
        return max;
    }
    
    public void increaseKey(int index, T key) {
        if(heap.backingSequence().get(index).compareTo(key) > 0) {
            throw new RuntimeException("current value is greater than supplied value");
        }
        heap.backingSequence().set(index, key);
        while(index > 0 && heap.backingSequence().get(heap.parentIndex(index)).compareTo(key) < 0) {
            heap.exchange(index, heap.parentIndex(index));
            index = heap.parentIndex(index);
        }
    }
    
    public void insertKey(T key) {
        if(heap.backingSequence().size() == heap.heapSize()) {
            heap.backingSequence().add(key);
        } else {
            heap.backingSequence().set(heap.heapSize(), key);
        }
        heap.incrementHeapSize();
        int index = heap.heapSize() - 1;
        while(index > 0 && heap.backingSequence().get(heap.parentIndex(index)).compareTo(key) < 0) {
            heap.exchange(index, heap.parentIndex(index));
            index = heap.parentIndex(index);
        }
    }
    
    //For testing
    Heap<T> getHeap() {
        return heap;
    }
}
