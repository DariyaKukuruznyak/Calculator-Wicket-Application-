package com.kukuruznyak.wicketapp.controller;

import com.kukuruznyak.wicketapp.modelandview.enums.ArithmeticOperations;
import com.kukuruznyak.wicketapp.modelandview.enums.Numbers;
import com.kukuruznyak.wicketapp.modelandview.enums.OtherOperations;
import com.kukuruznyak.wicketapp.service.CalculatorService;

import java.io.Serializable;

public class HomePageController implements Serializable {
    private boolean isResetDisplay = false;
    private boolean isLastInputWasArithmeticOperation = false;
    private CalculatorService service = new CalculatorService();

    public void inputNumber(Numbers number) {
        if (isResetDisplay) {
            service.clearDisplay();
            isResetDisplay = false;
        }
        service.inputNumber(number);
        isLastInputWasArithmeticOperation = false;
    }

    public void inputArithmeticOperation(ArithmeticOperations arithmeticOperations) {
        service.inputArithmeticOperation(arithmeticOperations, isLastInputWasArithmeticOperation);
        if (arithmeticOperations.toString().equals(ArithmeticOperations.EQUAL.toString())) {
            service.clearOperands();
            isLastInputWasArithmeticOperation = false;
        } else {
            service.setOperation(arithmeticOperations);
            isLastInputWasArithmeticOperation = true;
        }
        isResetDisplay = true;
    }

    public void inputOtherOperation(OtherOperations otherOperation) {
        service.inputOtherOperation(otherOperation);
    }

    public StringBuilder getDisplay() {
        return service.getDisplay();
    }
}

