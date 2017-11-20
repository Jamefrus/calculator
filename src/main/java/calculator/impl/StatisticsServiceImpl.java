package calculator.impl;

import calculator.domain.ArithmeticOperation;
import calculator.service.StatisticsService;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsServiceImpl implements StatisticsService {
    private EnumMap<ArithmeticOperation, AtomicInteger> counters;

    public StatisticsServiceImpl() {
        counters = new EnumMap<>(ArithmeticOperation.class);
        for (ArithmeticOperation operation : ArithmeticOperation.values()) {
            counters.put(operation, new AtomicInteger());
        }
    }

    @Override
    public void peek(ArithmeticOperation operation) {
        counters.get(operation).incrementAndGet();
    }

    @Override
    public int count(ArithmeticOperation operation) {
        return counters.get(operation).get();
    }
}
