package calculator.service;

import calculator.domain.ArithmeticOperation;

public interface StatisticsService {
    void peek(ArithmeticOperation operation);
    int count(ArithmeticOperation operation);
}
