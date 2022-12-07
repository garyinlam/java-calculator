package com.nology;

import com.nology.exceptions.InvalidExpressionException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

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
        if(s.contains("(")){
            int open = s.indexOf('(');
            if (open != 0){
                if (isNumeric(s.charAt(open-1))){
                    s = s.substring(0, open)+"*"+s.substring(open);
                }
            }
            int close = open;
            int bracketLevel = 0;
            for (int i = open+1; i < s.length(); i++) {
                if (s.charAt(i) == '('){
                    bracketLevel++;
                } else if (s.charAt(i) == ')' && bracketLevel > 0) {
                    bracketLevel--;
                } else if (s.charAt(i) == ')' && bracketLevel == 0) {
                    close = i;
                    i = s.length();
                }
            }
            return evaluate(s.substring(0, open)+evaluate(s.substring(open+1, close))+s.substring(close+1));
        } else if (s.contains("+")) {
            int plus = s.lastIndexOf('+');
            if(plus == 0){
                return evaluate(s.substring(1));
            } else {
                if (isOperator(s.charAt(plus-1))){
                    return evaluate(s.substring(0, plus)+s.substring(plus+1));
                } else {
                    return add(evaluate(s.substring(0, plus)), evaluate(s.substring(plus+1)));
                }
            }
        } else if (s.contains("-")) {
            int minus = s.lastIndexOf('-');
            if (minus != 0) {
                if (isOperator(s.charAt(minus-1))){
                    switch (s.charAt(minus-1)){
                        case '+':
                            return add(evaluate(s.substring(0,minus-1)), evaluate(s.substring(minus)));
                        case '-':
                            return subtract(evaluate(s.substring(0,minus-1)), evaluate(s.substring(minus)));
                        case '*':
                            return multiply(evaluate(s.substring(0,minus-1)), evaluate(s.substring(minus)));
                        case '/':
                            return divide(evaluate(s.substring(0,minus-1)), evaluate(s.substring(minus)));
                        case 'q':
                            if(minus > 2){
                                if (isOperator(s.charAt(minus-3))){
                                    return evaluate(s.substring(0,minus-2)+square(evaluate(s.substring(minus))));
                                } else {
                                    return multiply(evaluate(s.substring(0, minus-2)), square(evaluate(s.substring(minus))));
                                }
                            }
                            return square(evaluate(s.substring(minus)));
                        case 't':
                            return sqrt(evaluate(s.substring(minus)));
                    }
                } else {
                    return subtract(evaluate(s.substring(0,minus)),evaluate(s.substring(minus+1)));
                }
            } else {
                return subtract("0", evaluate(s.substring(1)));
            }
        } else if (s.contains("*")) {
            int multiply = s.lastIndexOf('*');
            return multiply(evaluate(s.substring(0,multiply)), evaluate(s.substring(multiply+1)));
        } else if (s.contains("/")) {
            int divide = s.lastIndexOf('/');
            return divide(evaluate(s.substring(0,divide)), evaluate(s.substring(divide+1)));
        } else if (s.contains("sq")) {
            int square = s.lastIndexOf('q');
            if (square != 1){
                if(isOperator(s.charAt(square-2))){
                    return evaluate(s.substring(0,square-1)+square(evaluate(s.substring(square+1))));
                } else {
                    return multiply(evaluate(s.substring(0, square-1)), square(evaluate(s.substring(square+1))));
                }
            } else {
                return square(evaluate(s.substring(square+1)));
            }
        } else if (s.contains("rt")) {
            int root = s.lastIndexOf('t');
            if (root != 1) {
                if (isOperator(s.charAt(root - 2))) {
                    return evaluate(s.substring(0, root - 1) + sqrt(evaluate(s.substring(root + 1))));
                } else {
                    return multiply(evaluate(s.substring(0, root - 1)), sqrt(evaluate(s.substring(root + 1))));
                }
            } else {
                return sqrt(evaluate(s.substring(root + 1)));
            }
        }
        return s;
    }

    public boolean validate(String s){
        if (s.contains("+*") || s.contains("-*") || s.contains("+/") || s.contains("-/") || s.contains("*/") || s.contains("/*") || s.contains("**") || s.contains("//")){
            throw new InvalidExpressionException("Invalid operator chain");
        }
        if (s.charAt(0) =='*' || s.charAt(0) == '/'){
            throw new InvalidExpressionException("Bad operator at start of expression");
        }
        if (s.charAt(s.length()-1) =='*' || s.charAt(s.length()-1) == '/'){
            throw new InvalidExpressionException("Bad operator at end of expression");
        }
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                brackets.push('(');
            } else if (s.charAt(i) == ')') {
                if (!brackets.isEmpty()){
                    brackets.pop();
                } else {
                    throw new InvalidExpressionException("Mismatched brackets");
                }
            } else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                if (i < s.length()-1) {
                    if ((s.charAt(i) == 's' && s.charAt(i+1) != 'q') || (s.charAt(i) == 'r' && s.charAt(i+1) != 't') || (s.charAt(i) != 'q' || s.charAt(i) != 'r' || s.charAt(i) != 's' || s.charAt(i) != 't')) {
                        throw new InvalidExpressionException("Cannot contain letter sequence that is not sq or rt");
                    }
                }
            }
        }
        if (!brackets.isEmpty()){
            throw new InvalidExpressionException("Missing brackets");
        }
        return true;
    }

    private boolean isNumeric(char c){
        return c >= '0' && c <= '9';
    }

    private boolean isMultiOperator(char c){
        return c == '*' || c == '/';
    }

    private boolean isOperator(char c){
        return isMultiOperator(c) || c == '+' || c == '-' || c == 'q' || c == 't';
    }
}
