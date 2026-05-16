package com.codingnow.lecture.modern22;

/** 단순 계산기. */
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("/ zero");
        return a / b;
    }
}
