package com.nology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackCalculatorTest {
    StackCalculator calculator = new StackCalculator();

    @Test
    @DisplayName("Correctly evaluate a given expression")
    void evaluate_ValidInput_CorrectReturn(){
        assertEquals("7",calculator.evaluate("1+2*3"));
        assertEquals("1",calculator.evaluate("(4/2)-1"));
        assertEquals("8",calculator.evaluate("2*2^2"));
        assertEquals("0",calculator.evaluate("2-4^0.5"));
    }
}