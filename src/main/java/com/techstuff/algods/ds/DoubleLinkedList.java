package com.techstuff.algods.ds;

public class DoubleLinkedList<T> {
    
    private Node<T> head;
    
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        if(head != null) {
            head.prev = newNode;
        }
        head = newNode;
    }
    
    public void delete(T data) {
        Node<T> node = search(data);
        if(node == null) {
            return;
        }
        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }
    }
    
    public T get(int index) {
        Node<T> node = head;
        int i = 0;
        while(i < index && node != null) {
            node = node.next;
            i++;
        }
        return node == null ? null : node.data;
    }
    
    private Node<T> search(T data) {
        Node<T> node = head;
        while(node != null && node.data != data) {
            node = node.next;
        }
        return node;
    }

    private class Node<T> {
        
        Node(T data) {
            this.data = data;
        }
        
        private T data;
        
        private Node<T> next;
        
        private Node<T> prev;
    }
}
