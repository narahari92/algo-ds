package com.techstuff.algods.sort;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort<T extends Comparable<T>> {

    private List<T> sequence = new ArrayList<>();
    
    public InsertionSort(List<T> sequence) {
        for(T elem : sequence) {
            this.sequence.add(elem);
        }
    }
    
    public void sort(boolean nonDescending) {
        int direction = nonDescending  ? 1 : -1;
        for(int j = 1 ; j < sequence.size() ; j++) {
            int i = j - 1;
            T current = sequence.get(j);
            while(i >= 0 && sequence.get(i).compareTo(current) * direction > 0) {
                sequence.set(i + 1, sequence.get(i));
                i--;
            }
            sequence.set(i + 1, current);
        }
    }
    
    public List<T> getSequence() {
        return sequence;
    }
}
