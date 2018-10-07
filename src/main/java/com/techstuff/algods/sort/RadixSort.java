package com.techstuff.algods.sort;

import java.util.Arrays;
import java.util.List;

public class RadixSort {

    private Long[] sequence;
    
    public RadixSort(List<Long> sequence) {
        this.sequence = new Long[sequence.size()];
        for(int i = 0 ; i < sequence.size() ; i++) {
            this.sequence[i] = sequence.get(i);
        }
    }
    
    private Long getMax() {
        Long max = sequence[0];
        for(int i = 1 ; i < sequence.length ; i++) {
            if(sequence[i] > max) {
                max = sequence[i];
            }
        }
        return max;
    }
    
    private void countSort(long indexNum, boolean nonDescending) {
        Long[] output = new Long[sequence.length];
        Integer[] count = new Integer[10];
        for(int i = 0 ; i < count.length ; i++) {
            count[i] = 0;
        }
        for(int i = 0 ; i < sequence.length ; i++) {
            int digitNum = new Long((sequence[i] / indexNum) % 10).intValue();
            count[digitNum] = count[digitNum] + 1;
        }
        if(nonDescending) {
            for(int i = 1 ; i < count.length ; i++) {
                count[i] += count[i - 1];
            }
        } else {
            for(int i = count.length - 2 ; i >= 0 ; i--) {
                count[i] += count[i + 1];
            }
        }
        for(int i = sequence.length - 1 ; i >= 0 ; i--) {
            int digitNum = new Long((sequence[i] / indexNum) % 10).intValue();
            output[count[digitNum] - 1] = sequence[i];
            count[digitNum] -= 1;
        }
        sequence = output;
    }
    
    public void sort(boolean nonDescending) {
        Long max = getMax();
        for(long indexNum = 1l ; max / indexNum > 0; indexNum *= 10) {
            countSort(indexNum, nonDescending);
        }
    }
    
    public List<Long> getSequence() {
        return Arrays.asList(sequence);
    }
}
