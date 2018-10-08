package com.techstuff.algods.ds;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StackTest {

    @Test
    public void testEmpty() {
        Stack<String> stack = new Stack<>(5);
        assertEquals(true, stack.isEmpty());
        stack.push("first");
        assertEquals(false, stack.isEmpty());
        stack.pop();
        assertEquals(true, stack.isEmpty());
    }
    
    @Test
    public void testPushPop() {
        Stack<Integer> stack = new Stack<>(5);
        stack.push(50);
        stack.push(20);
        stack.push(15);
        assertEquals(new Integer(15), stack.pop());
        assertEquals(new Integer(20), stack.pop());
    }
    
    @Test(expected=RuntimeException.class)
    public void testStackUnderflow() {
        Stack<Long> stack = new Stack<>(10);
        stack.push(20l);
        stack.pop();
        stack.pop();
    }
    
    @Test(expected=RuntimeException.class)
    public void testStackOverflow() {
        Stack<Long> stack = new Stack<>(3);
        stack.push(1l);
        stack.push(2l);
        stack.push(3l);
        stack.push(4l);
    }
}
