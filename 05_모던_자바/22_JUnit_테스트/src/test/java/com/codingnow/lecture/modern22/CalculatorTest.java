package com.codingnow.lecture.modern22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    @DisplayName("add 는 두 수의 합을 반환")
    void add는_합을_반환() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
    }

    @Test
    void subtract_정상() {
        assertEquals(2, calc.subtract(5, 3));
    }

    @Test
    void divide_by_zero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
