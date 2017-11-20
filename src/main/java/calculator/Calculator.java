package calculator;

import calculator.domain.ArithmeticOperation;
import calculator.domain.ComplexObject;
import calculator.service.CountingService;
import calculator.service.StatisticsService;

public class Calculator {

    private CountingService countingService;
    private StatisticsService statisticsService;

    public void setCountingService(CountingService countingService) {
        this.countingService = countingService;
    }

    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public ComplexObject add(ComplexObject param1, ComplexObject param2) {
        validate(param1, param2);
        peek(ArithmeticOperation.ADD);
        return countingService.add(param1, param2);
    }

    public ComplexObject subtract(ComplexObject param1, ComplexObject param2) {
        validate(param1, param2);
        peek(ArithmeticOperation.SUBTRACT);
        return countingService.subtract(param1, param2);
    }

    public ComplexObject multiply(ComplexObject param1, ComplexObject param2) {
        validate(param1, param2);
        peek(ArithmeticOperation.MULTIPLY);
        return countingService.multiply(param1, param2);
    }

    public ComplexObject divide(ComplexObject param1, ComplexObject param2) {
        validate(param1, param2);
        peek(ArithmeticOperation.DIVIDE);
        return countingService.divide(param1, param2);
    }
    private void peek(ArithmeticOperation operation) {
        if (statisticsService != null){
            statisticsService.peek(operation);
        }
    }

    private void validate(ComplexObject param1, ComplexObject param2){
        if(param1.getService() != param2.getService()){
            throw new IllegalArgumentException("Different Services");
        }
    }
}
