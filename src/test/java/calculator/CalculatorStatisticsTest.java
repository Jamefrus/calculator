package calculator;

import calculator.Calculator;
import calculator.domain.ArithmeticOperation;
import calculator.domain.ComplexObject;
import calculator.service.CountingService;
import calculator.service.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorStatisticsTest {

    private static final ComplexObject OBJECT_A = new ComplexObject(4, 2);
    private static final ComplexObject OBJECT_B = new ComplexObject(2, 5);

    @Mock
    private CountingService countingService;

    @Mock
    private StatisticsService statistics;

    @InjectMocks
    private Calculator calculator;

    @Test
    public void peeksStatisticsOnAdding() throws Exception {
        calculator.add(OBJECT_A, OBJECT_B);

        then(statistics).should().peek(ArithmeticOperation.ADD);
    }


    @Test
    public void peeksStatisticsOnSubtracting() throws Exception {
        calculator.subtract(OBJECT_A, OBJECT_B);

        then(statistics).should().peek(ArithmeticOperation.SUBTRACT);
    }


    @Test
    public void peeksStatisticsOnDividing() throws Exception {
        calculator.divide(OBJECT_A, OBJECT_B);

        then(statistics).should().peek(ArithmeticOperation.DIVIDE);
    }


    @Test
    public void peeksStatisticsOnMultiplying() throws Exception {
        calculator.multiply(OBJECT_A, OBJECT_B);

        then(statistics).should().peek(ArithmeticOperation.MULTIPLY);
    }
}
