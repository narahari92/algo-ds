package com.techstuff.algods.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort<T extends Comparable<T>> {

    private List<T> sequence = new ArrayList<>();
    
    private boolean randomize;
    
    public QuickSort(List<T> sequence, boolean randomize) {
        for(T elem : sequence) {
            this.sequence.add(elem);
        }
        this.randomize = randomize;
    }
    
    public void sort(boolean nonDescending) {
        int direction = nonDescending ? 1 : -1;
        sort(direction, 0, sequence.size() - 1);
    }
    
    private void sort(int direction, int p, int r) {
        if(p < r) {
            int q = partition(direction, p, r);
            sort(direction, p, q - 1);
            sort(direction, q + 1, r);
        }
    }
    
    private int partition(int direction, int p, int r) {
        if(randomize) {
            int randIndex = p + new Random().nextInt(r - p + 1);
            exchange(randIndex, r);
        }
        T pivot = sequence.get(r);
        int i = p - 1;
        for(int j = p ; j < r ; j++) {
            if(sequence.get(j).compareTo(pivot) * direction < 0) {
                exchange(++i, j);
            }
        }
        exchange(++i, r);
        return i;
    }
    
    private void exchange(int from, int to) {
        T temp = sequence.get(from);
        sequence.set(from, sequence.get(to));
        sequence.set(to, temp);
        
    }
    
    public List<T> getSequence() {
        return sequence;
    }
}
