package com.techstuff.algods.ds;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QueueTest {

    @Test
    public void testEmpty() {
        Queue<Integer> queue = new Queue<>(3);
        assertEquals(true, queue.isEmpty());
        queue.enqueue(1);
        assertEquals(false, queue.isEmpty());
        queue.enqueue(2);
        queue.dequeue();
        assertEquals(false, queue.isEmpty());
        queue.dequeue();
        assertEquals(true, queue.isEmpty());
    }
    
    @Test
    public void testFull() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(3);
        queue.enqueue(2);
        assertEquals(false, queue.isFull());
        queue.enqueue(4);
        assertEquals(true, queue.isFull());
        queue.dequeue();
        assertEquals(false, queue.isFull());
        queue.enqueue(1);
        assertEquals(true, queue.isFull());
    }
    
    @Test
    public void testEnqueueDequeue() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(new Integer(3), queue.dequeue());
    }
    
    @Test(expected=RuntimeException.class)
    public void testQueueSimpleOverflow() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
    }
    
    @Test(expected=RuntimeException.class)
    public void testQueueRollOverflow() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        queue.enqueue(5);
    }
    
    @Test(expected=RuntimeException.class)
    public void testQueueUnderflow() {
        Queue<Integer> queue = new Queue<>(3);
        queue.dequeue();
    }
    
    @Test(expected=RuntimeException.class)
    public void testQueueRollUnderflow() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }
}
