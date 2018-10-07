package com.techstuff.algods;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class OrderFinderTest {

    @Test
    public void testIthMinFind() {
        List<Long> sequence = Arrays.asList(3l, 1l, 5l, 2l, 4l, 8l, 10l, 6l);
        OrderFinder<Long> orderFinder = new OrderFinder<>(sequence);
        Long actual = orderFinder.findOrder(3, true);
        assertEquals(new Long(3l), actual);
    }
    
    @Test
    public void testIthMaxFind() {
        List<String> sequence = Arrays.asList("hari", "harry", "potter", "greg", "raj");
        OrderFinder<String> orderFinder = new OrderFinder<>(sequence);
        String actual = orderFinder.findOrder(2, false);
        assertEquals("potter", actual);
    }
}
