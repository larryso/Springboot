package com.larry.test;

/**
 * Simple calculatgor to perform basic mathmatical operations
 */
public class Calculator {
    public double calculate(double firstOperand, double secondOperand, char operator){
        switch(operator){
            case '+':
                return add(firstOperand, secondOperand);
            case '-':
                return subtract(firstOperand, secondOperand);
            case '*':
                return multiply(firstOperand, secondOperand);
            case '/':
                return divide(firstOperand, secondOperand);
            default:
                throw new IllegalArgumentException("Unsupported operator!");
        }
    }

    private double add(double firstOperand, double secondOperand){
        return firstOperand + secondOperand;
    }
    private double divide(double firstOperand, double secondOperand){
        return firstOperand / secondOperand;
    }
    private double multiply(double firstOperand, double secondOperand){
        return firstOperand * secondOperand;
    }
    private double subtract(double firstOperand, double secondOperand){
        return firstOperand - secondOperand;
    }
}
