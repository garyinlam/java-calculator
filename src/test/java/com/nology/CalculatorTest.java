package com.nology;

import com.nology.exceptions.InvalidExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    @DisplayName("Add two numbers")
    void add_ValidInput_CorrectReturn(){
        assertEquals("3",calculator.add("1","2"));
        assertEquals("-1",calculator.add("1","-2"));
        assertEquals("0.3",calculator.add("0.1","0.2"));
    }

    @Test
    @DisplayName("Subtract two numbers")
    void subtract_ValidInput_CorrectReturn(){
        assertEquals("-1",calculator.subtract("1","2"));
        assertEquals("3",calculator.subtract("1","-2"));
        assertEquals("-0.1",calculator.subtract("0.1","0.2"));
    }

    @Test
    @DisplayName("Multiply two numbers")
    void multiply_ValidInput_CorrectReturn(){
        assertEquals("2",calculator.multiply("1","2"));
        assertEquals("-2",calculator.multiply("1","-2"));
        assertEquals("0.02",calculator.multiply("0.1","0.2"));
    }

    @Test
    @DisplayName("Divide two numbers")
    void divide_ValidInput_CorrectReturn(){
        assertEquals("0.5",calculator.divide("1","2"));
        assertEquals("-0.5",calculator.divide("1","-2"));
        assertEquals("0.5",calculator.divide("0.1","0.2"));
    }

    @Test
    @DisplayName("Square a number")
    void square_ValidInput_CorrectReturn(){
        assertEquals("4",calculator.square("2"));
        assertEquals("4",calculator.square("-2"));
        assertEquals("0.04",calculator.square("0.2"));
    }

    @Test
    @DisplayName("Square root a number")
    void sqrt_ValidInput_CorrectReturn(){
        assertEquals("2",calculator.sqrt("4"));
        assertEquals("3",calculator.sqrt("9"));
        assertEquals("0.2",calculator.sqrt("0.04"));
    }

    @Test
    @DisplayName("Square root a negative number throws exception")
    void sqrt_InvalidInput_ThrowsError(){
        assertThrows( IllegalArgumentException.class, () -> {calculator.sqrt("-1");});
    }

    @Test
    @DisplayName("Correctly evaluate a given expression")
    void evaluate_ValidInput_CorrectReturn(){
        assertEquals("7",calculator.evaluate("1+2*3"));
        assertEquals("1",calculator.evaluate("(4/2)-1"));
        assertEquals("8",calculator.evaluate("2sq2"));
        assertEquals("0",calculator.evaluate("2-rt4"));
    }

    @Test
    @DisplayName("Return true for a valid expression")
    void validate_ValidInput_ReturnsTrue(){
        assertTrue(calculator.validate("1+2"));
    }

    @Test
    @DisplayName("Return false for a invalid expression")
    void validate_InvalidInput_ThrowsError(){
        assertThrows( InvalidExpressionException.class, () -> {calculator.validate("(1+2");});
        assertThrows( InvalidExpressionException.class, () -> {calculator.validate("1+*2");});
        assertThrows( InvalidExpressionException.class, () -> {calculator.validate("abc1+2");});
        assertThrows( InvalidExpressionException.class, () -> {calculator.validate(")(1+2)");});
        assertThrows( InvalidExpressionException.class, () -> {calculator.validate("sq1a");});
        assertThrows( InvalidExpressionException.class, () -> {calculator.validate("((1-1)");});


    }
}