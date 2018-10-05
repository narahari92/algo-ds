package com.techstuff.algods.sort;

import java.util.ArrayList;
import java.util.List;

import com.techstuff.algods.ds.Heap;

public class HeapSort<T extends Comparable<T>> {

    private List<T> sequence = new ArrayList<>();
    
    public HeapSort(List<T> sequence) {
        for(T elem : sequence) {
            this.sequence.add(elem);
        }
    }
    
    public void sort(boolean nonDescending) {
        Heap<T> heap = new Heap<>(sequence, !nonDescending);
        for(int i = 0 ; i < sequence.size() - 1 ; i++) {
            heap.exchange(0, heap.heapSize() - 1);
            heap.decrementHeapSize();
            heap.heapify(0);
        }
        this.sequence = heap.backingSequence();
    }
    
    public List<T> getSequence() {
        return sequence;
    }
}
