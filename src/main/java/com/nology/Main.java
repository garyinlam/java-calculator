package com.nology;

import com.nology.exceptions.InvalidExpressionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean correct = false;
        String exp = "";

        while(!correct){
            System.out.println("Input expression:");
            try {
                exp = br.readLine().toLowerCase().replaceAll("\\s", "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                correct = calculator.validate(exp);
            } catch (InvalidExpressionException e){
                System.err.println(e.getMessage());
                System.err.println("Try again");
            }
        }

        System.out.println(calculator.evaluate(exp));

//        System.out.println(calculator.evaluate("1+2"));
//        System.out.println(calculator.evaluate("1-2"));
//        System.out.println(calculator.evaluate("1*2"));
//        System.out.println(calculator.evaluate("1/2"));
//        System.out.println(calculator.evaluate("sq2"));
//        System.out.println(calculator.evaluate("rt4"));
//        System.out.println(calculator.evaluate("2sq2"));
//        System.out.println(calculator.evaluate("2rt4"));
//        System.out.println(calculator.evaluate("2-sq2"));
//        System.out.println(calculator.evaluate("2-rt4"));
//        System.out.println(calculator.evaluate("1+-2"));
//        System.out.println(calculator.evaluate("1*-2"));
//        System.out.println(calculator.evaluate("1/-2"));
//        System.out.println(calculator.evaluate("1--2"));
//        System.out.println(calculator.evaluate("sq-2"));
//        System.out.println(calculator.evaluate("2sq-2"));
//        System.out.println(calculator.evaluate("1+sq-2"));
//        System.out.println(calculator.evaluate("rt(-2*-2)"));
    }
}