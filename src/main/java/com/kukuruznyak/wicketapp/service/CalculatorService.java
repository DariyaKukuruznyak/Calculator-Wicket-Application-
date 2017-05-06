package com.kukuruznyak.wicketapp.service;

import com.kukuruznyak.wicketapp.modelandview.enums.ArithmeticOperations;
import com.kukuruznyak.wicketapp.modelandview.enums.Numbers;
import com.kukuruznyak.wicketapp.modelandview.enums.OtherOperations;

import java.io.Serializable;

public class CalculatorService implements Serializable {
    private final int OPERATOR_MAX_LENGTH = 15;
    private final char MINUS_SIGN = 45;
    private StringBuilder display = new StringBuilder();
    private String operator;
    private ArithmeticOperations operation;

    public void inputNumber(Numbers number) {

        if (display.length() < OPERATOR_MAX_LENGTH) {
            display.append(number.toString());
        }

        // if was added second dot
        if (display.indexOf(Numbers.DOT.toString()) != display.lastIndexOf(Numbers.DOT.toString())) {
            display.delete(display.length() - 1, display.length());
        }
    }

    public void inputArithmeticOperation(ArithmeticOperations arithmeticOperations, boolean isLastInputWasArithmeticOperation) {
        if (display.toString().equals(String.valueOf(Double.NaN))) {
            return;
        }
        if (operator == null) {
            if (operation == null) {
                operator = display.toString();
            }
        } else {
            if (!isLastInputWasArithmeticOperation) {
                operator = calculate(operator, display.toString(), operation);
                display.delete(0, display.length());
                display.append(operator);
            }
        }
    }

    public void inputOtherOperation(OtherOperations otherOperation) {
        switch (otherOperation) {
            case BACKSPACE:
                if (display.length() > 0 && !display.toString().equals(String.valueOf(Double.NaN))) {
                    display.delete(display.length() - 1, display.length());
                }
                break;
            case CLEAR:
                clearOperands();
                clearDisplay();
                break;
            case CHANGE_SIGN:
                if (display.toString().equals(String.valueOf(Double.NaN))) {
                    break;
                }
                if (display.length() == 0) {
                    display.append(MINUS_SIGN);
                    break;
                }
                if (display.charAt(0) == MINUS_SIGN) {
                    display.delete(0, 1);
                } else {
                    display.insert(0, MINUS_SIGN);
                }
                break;
        }
    }

    private String calculate(String firstOperator, String secondOperator, ArithmeticOperations operation) {
        double result = 0;
        double operator1 = 0;
        double operator2 = 0;

        try {
            operator1 = Double.parseDouble(firstOperator);
            operator2 = Double.parseDouble(secondOperator);
        } catch (NumberFormatException ex) {
            // this exception can be called
            // if user input more than one operation successively
            return String.valueOf(Double.NaN);
        }

        switch (operation) {
            case ADD:
                result = operator1 + operator2;
                break;
            case MINUS:
                result = operator1 - operator2;
                break;
            case MULTIPLY:
                result = operator1 * operator2;
                break;
            case DIVIDE:
                if (operator2 != 0) {
                    result = operator1 / operator2;
                } else {
                    return String.valueOf(Double.NaN);
                }
                break;
        }
        return deleteZeroInTail(result);
    }

    private String deleteZeroInTail(double value) {
        StringBuilder string = new StringBuilder(String.valueOf(value));
        while (true) {
            if (string.indexOf(Numbers.DOT.toString()) != -1 && (string.charAt(string.length() - 1) == 48 || string.charAt(string.length() - 1) == 46)) {
                string.delete(string.length() - 1, string.length());
            } else {
                return string.toString();
            }
        }
    }

    public StringBuilder getDisplay() {
        return display;
    }

    public void setOperation(ArithmeticOperations operation) {
        this.operation = operation;
    }

    public void clearDisplay() {
        display.delete(0, display.length());
    }

    public void clearOperands() {
        operator = null;
        operation = null;
    }
}
