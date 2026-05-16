package com.codingnow.lecture.modern22;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FixtureTest {
    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("(setUp)");
    }

    @AfterEach
    void tearDown() {
        System.out.println("(tearDown)");
    }

    @Test
    void test_add() { assertEquals(7, calc.add(3, 4)); }

    @Test
    void test_sub() { assertEquals(1, calc.subtract(4, 3)); }
}
