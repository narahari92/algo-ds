package com.techstuff.algods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderFinder<T extends Comparable<T>> {

    private List<T> sequence = new ArrayList<>();
    
    public OrderFinder(List<T> sequence) {
        for(T elem : sequence) {
            this.sequence.add(elem);
        }
    }
    
    public T findOrder(int i, boolean nonMax) {
        return findOrder(i - 1, nonMax ? 1 : -1, 0, sequence.size() - 1);
    }
    
    private T findOrder(int i, int direction, int p, int r) {
        if(p == r) {
            return sequence.get(p);
        }
        int q = partition(p, r, direction);
        if(i == q) {
            return sequence.get(q);
        }
        if(i < q) {
            return findOrder(i, direction, p, q - 1); 
        } else {
            return findOrder(i, direction, q + 1, r);
        }
    }
    
    private int partition(int p, int r, int direction) {
        int randIndex = p + new Random().nextInt(r - p + 1);
        exchange(randIndex, r);
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
}
