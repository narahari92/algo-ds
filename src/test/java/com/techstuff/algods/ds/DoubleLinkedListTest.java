package com.techstuff.algods.ds;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoubleLinkedListTest {

    @Test
    public void testInsert() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.insert("hari");
        list.insert("potter");
        assertEquals("potter", list.get(0));
        assertEquals("hari", list.get(1));
        assertEquals(null, list.get(2));
    }
    
    @Test
    public void testDelete() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.delete(2);
        assertEquals(new Integer(3), list.get(0));
        assertEquals(new Integer(1), list.get(1));
        list.delete(3);
        assertEquals(new Integer(1), list.get(0));
    }
}
