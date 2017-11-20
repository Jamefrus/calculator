package calculator;

import calculator.domain.ComplexObject;
import calculator.service.CountingService;

public class Calculator {

    private CountingService countingService;

    public void setCountingService(CountingService countingService) {
        this.countingService = countingService;
    }

    public ComplexObject add(ComplexObject param1, ComplexObject param2) {
        return countingService.add(param1, param2);
    }
    public ComplexObject subtract(ComplexObject param1, ComplexObject param2) {
        return countingService.subtract(param1, param2);
    }
    public ComplexObject multiply(ComplexObject param1, ComplexObject param2) {
        return countingService.multiply(param1, param2);
    }
    public ComplexObject divide(ComplexObject param1, ComplexObject param2) {
        return countingService.divide(param1, param2);
    }
}
