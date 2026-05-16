package com.codingnow.lecture.modern22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssertionsTest {
    @Test
    void variety() {
        assertTrue(2 + 2 == 4);
        assertFalse("hi".isEmpty());
        assertNotNull("");
        assertNull(null);
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertIterableEquals(List.of("a"), List.of("a"));
    }
}
