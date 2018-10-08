package com.techstuff.algods.ds;

public class Queue<T> {

    private Object[] elements;
    
    private int head;
    
    private int tail;
    
    public Queue(int size) {
        this.elements = new Object[size];
        this.head = -1;
        this.tail = -1;
    }
    
    public void enqueue(T elem) {
        if(isFull()) {
            throw new RuntimeException("Queue is full");
        }
        if(head == -1) {
            head = 0;
        }
        tail = (tail + 1) % elements.length;
        elements[tail] = elem;
    }
    
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if(isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T elem = (T) elements[head];
        if(head == tail) {
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % elements.length;
        }
        return elem;
    }
    
    public boolean isEmpty() {
        return head == -1;
    }
    
    public boolean isFull() {
        return (head == 0 && tail == elements.length - 1) || (tail + 1 == head);
    }
}
