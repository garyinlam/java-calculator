package com.nology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    @DisplayName("Add two numbers")
    void add_ValidInput_CorrectReturn(){
        assertEquals(calculator.add("1","2") ,"3");
        assertEquals(calculator.add("1","-2") ,"-1");
        assertEquals(calculator.add("0.1","0.2") ,"0.3");
    }

    @Test
    @DisplayName("Subtract two numbers")
    void subtract_ValidInput_CorrectReturn(){
        assertEquals(calculator.subtract("1","2") ,"-1");
        assertEquals(calculator.subtract("1","-2") ,"3");
        assertEquals(calculator.subtract("0.1","0.2") ,"-0.1");
    }

    @Test
    @DisplayName("Multiply two numbers")
    void multiply_ValidInput_CorrectReturn(){
        assertEquals(calculator.multiply("1","2") ,"2");
        assertEquals(calculator.multiply("1","-2") ,"-2");
        assertEquals(calculator.multiply("0.1","0.2") ,"0.02");
    }

    @Test
    @DisplayName("Divide two numbers")
    void divide_ValidInput_CorrectReturn(){
        assertEquals(calculator.divide("1","2") ,"0.5");
        assertEquals(calculator.divide("1","-2") ,"-0.5");
        assertEquals(calculator.divide("0.1","0.2") ,"0.5");
    }

    @Test
    @DisplayName("Square a number")
    void square_ValidInput_CorrectReturn(){
        assertEquals(calculator.square("2") ,"4");
        assertEquals(calculator.square("-2") ,"4");
        assertEquals(calculator.square("0.2") ,"0.04");
    }

    @Test
    @DisplayName("Square root a number")
    void sqrt_ValidInput_CorrectReturn(){
        assertEquals(calculator.sqrt("4") ,"2");
        assertEquals(calculator.sqrt("9") ,"3");
        assertEquals(calculator.sqrt("0.04") ,"0.2");
    }

    @Test
    @DisplayName("Square root a negative number throws exception")
    void sqrt_InvalidInput_ThrowsError(){
        assertThrows( IllegalArgumentException.class, () -> {calculator.sqrt("-1");});
    }

    @Test
    @DisplayName("Correctly evaluate a given expression")
    void evaluate_ValidInput_CorrectReturn(){
        assertEquals(calculator.evaluate("1+2*3"), "7");
        assertEquals(calculator.evaluate("(4/2)-1"), "1");
    }
}