package com.nology;

import com.nology.exceptions.InvalidExpressionException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackCalculator extends Calculator {
    private boolean isOperator(char c){
        return c == '/' || c == '*' || c == '+' || c == '-' || c == '^';
    }

    private int getPrecedence(char c){
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*' || c == '/')
            return 2;
        else if (c == '^')
            return 3;
        else
            return -1;
    }

    private boolean hasLeftAssociativity(char c){
        return c == '/' || c == '*' || c == '+' || c == '-';
    }

    private String[] infixToRPN(String expression){
        Stack<Character> operatorStack = new Stack<>();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++){
            if (isOperator(expression.charAt(i))){
                if (expression.charAt(i) == '-' && i > 0 && isOperator(expression.charAt(i-1))){
                    int end = expression.length();
                    for(int j = i+1; j < expression.length(); j++){
                        if (isOperator(expression.charAt(j)) || expression.charAt(j) == '(' || expression.charAt(j) == ')') {
                            end = j;
                            j = expression.length();
                        }
                    }
                    output.add(expression.substring(i,end));
                    i = end-1;
                } else {
                    while (!operatorStack.isEmpty() && getPrecedence(expression.charAt(i)) <= getPrecedence(operatorStack.peek()) && hasLeftAssociativity(expression.charAt(i))) {
                        output.add(operatorStack.pop() + "");
                    }
                    operatorStack.push(expression.charAt(i));
                }
            } else if (expression.charAt(i) == '(') {
                operatorStack.push(expression.charAt(i));
            } else if (expression.charAt(i) == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek()!= '('){
                    output.add(operatorStack.pop()+"");
                }
                operatorStack.pop();
            } else {
                int end = expression.length();
                for(int j = i; j < expression.length(); j++){
                    if (isOperator(expression.charAt(j)) || expression.charAt(j) == '(' || expression.charAt(j) == ')') {
                        end = j;
                        j = expression.length();
                    }
                }
                output.add(expression.substring(i,end));
                i = end-1;
            }
        }

        while(!operatorStack.isEmpty()){
            if (operatorStack.peek() == '('){
                throw new InvalidExpressionException("Bad brackets");
            }
            output.add(operatorStack.pop()+"");
        }
        return output.toArray(new String[0]);
    }

    public String power(String s1, String s2){
        return new BigDecimal(Math.pow(Double.parseDouble(s1), Double.parseDouble(s2))).toString();
    }

    private String clean (String expression){
        return expression.replaceAll("\\s", "").replaceAll("\\+\\+", "+").replaceAll("\\+-", "-").replaceAll("--", "+").replaceAll("\\*\\+", "*").replaceAll("/\\+", "/");
    }
    @Override
    public String evaluate(String expression){
        String[] rpn = infixToRPN(clean(expression));
        Stack<String> stack = new Stack<>();
        String x,y,operator;
        for (String s: rpn) {
            if (s.length()==1 && isOperator(s.charAt(0))){
                operator = s;
            } else {
                stack.push(s);
                continue;
            }

            switch (s){
                case "+":
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(add(x,y));
                    break;
                case "-":
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(subtract(y,x));
                    break;
                case "/":
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(divide(y,x));
                    break;
                case "*":
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(multiply(x,y));
                    break;
                case "^":
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(power(y,x));
                    break;
                default:
                    continue;
            }
        }
        return stack.pop();
    }
}

