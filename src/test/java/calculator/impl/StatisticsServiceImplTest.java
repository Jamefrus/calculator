package calculator.impl;

import calculator.domain.ArithmeticOperation;
import calculator.service.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StatisticsServiceImplTest {

    @Parameterized.Parameter
    public ArithmeticOperation operation;
    private StatisticsService statisticsService = new StatisticsServiceImpl();
    private Iterator<ArithmeticOperation> anotherOperations;

    @Before
    public void setUp() throws Exception {
        anotherOperations = Stream.generate(ArithmeticOperation::values)
                .flatMap(Arrays::stream)
                .filter(operation1 -> operation1 != operation)
                .iterator();
    }

    @Parameterized.Parameters(name = "{0}")
    public static ArithmeticOperation[] data() {
        return ArithmeticOperation.values();
    }

    @Test
    public void peeksAndCountsOperation() throws Exception {
        assertEquals(0, statisticsService.count(operation));

        statisticsService.peek(operation);
        assertEquals(1, statisticsService.count(operation));

        statisticsService.peek(operation);
        assertEquals(2, statisticsService.count(operation));

        statisticsService.peek(operation);
        statisticsService.peek(operation);
        assertEquals(4, statisticsService.count(operation));

        statisticsService.peek(operation);
        statisticsService.peek(operation);
        statisticsService.peek(operation);
        assertEquals(7, statisticsService.count(operation));
    }

    @Test
    public void ignoresAnotherOperation() throws Exception {
        assertEquals(0, statisticsService.count(operation));

        statisticsService.peek(operation);
        assertEquals(1, statisticsService.count(operation));


        statisticsService.peek(anotherOperations.next());
        assertEquals(1, statisticsService.count(operation));


        statisticsService.peek(anotherOperations.next());
        assertEquals(1, statisticsService.count(operation));


        statisticsService.peek(anotherOperations.next());
        assertEquals(1, statisticsService.count(operation));


        statisticsService.peek(anotherOperations.next());
        assertEquals(1, statisticsService.count(operation));
    }
}