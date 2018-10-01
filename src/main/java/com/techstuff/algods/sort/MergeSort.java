package com.techstuff.algods.sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> {
    
private List<T> sequence = new ArrayList<>();
    
    public MergeSort(List<T> sequence) {
        for(T elem : sequence) {
            this.sequence.add(elem);
        }
    }

    public void sort(boolean nonDescending) {
        int direction = nonDescending ? 1 : -1;
        sequence = sort(sequence, 0, sequence.size() - 1, direction);
    }
    
    private List<T> sort(List<T> seq, int l, int h, int direction) {
        if(l == h) {
            List<T> ret = new ArrayList<>();
            ret.add(seq.get(l));
            return ret;
        }
        int mid = (l + h) / 2;
        List<T> first = sort(seq, l, mid, direction);
        List<T> second = sort(seq, mid + 1, h, direction);
        return combine(first, second, direction);
    }
    
    private List<T> combine(List<T> first, List<T> second, int direction) {
        List<T> ret = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < first.size() && j < second.size()) {
            if(first.get(i).compareTo(second.get(j)) * direction < 0) {
                ret.add(first.get(i));
                i++;
            } else {
                ret.add(second.get(j));
                j++;
            }
        }
        for(; i < first.size() ; i++) {
            ret.add(first.get(i));
        }
        for(; j < second.size() ; j++) {
            ret.add(second.get(j));
        }
        return ret;
    }
    
    public List<T> getSequence() {
        return sequence;
    }
}
