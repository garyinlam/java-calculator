package com.nology;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calculator {
    public String add(String num1, String num2) {
        return new BigDecimal(num1).add(new BigDecimal(num2)).toString();
    }

    public String subtract(String num1, String num2) {
        return new BigDecimal(num1).subtract(new BigDecimal(num2)).toString();
    }

    public String multiply(String num1, String num2) {
        return new BigDecimal(num1).multiply(new BigDecimal(num2)).toString();
    }

    public String divide(String num1, String num2) {
        return new BigDecimal(num1).divide(new BigDecimal(num2)).toString();
    }

    public String square(String num) {
        return new BigDecimal(num).pow(2).toString();

    }

    public String sqrt(String num) {
        if (new BigDecimal(num).compareTo(new BigDecimal(0)) < 0){
            throw new IllegalArgumentException();
        }
        return new BigDecimal(num).sqrt(new MathContext(10)).toString();
    }

    public String evaluate(String s) {
        return "";
    }
}
