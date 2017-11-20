package calculator;

import calculator.domain.ComplexObject;
import calculator.service.CountingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    @Mock
    private CountingService countingService;

    @InjectMocks
    private Calculator calculator = new Calculator();

    @Test
    public void addTest() {
        // Given
        ComplexObject objectA = new ComplexObject(1, 2);
        ComplexObject objectB = new ComplexObject(3, 4);

        ComplexObject expectedResult = new ComplexObject(0, 0);

        Mockito.when(countingService.add(objectA, objectB)).thenReturn(expectedResult);
        // When

        ComplexObject result = calculator.add(objectA, objectB);

        // Then
        Assert.assertEquals(expectedResult, result);
        Mockito.verify(countingService).add(objectA, objectB);
    }

}
