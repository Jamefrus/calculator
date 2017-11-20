package calculator;

import calculator.domain.ComplexObject;
import calculator.domain.Service;
import calculator.service.CountingService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {
    private static final ComplexObject OBJECT_A = new ComplexObject(1, 2);
    private static final ComplexObject OBJECT_B = new ComplexObject(3, 4);
    private static final ComplexObject EXPECTED_RESULT = new ComplexObject(0, 0);
    
    @Mock
    private CountingService countingService;

    @InjectMocks
    private Calculator calculator = new Calculator();

    @Test
    public void addsByCountingService() {
        given(countingService.add(OBJECT_A, OBJECT_B)).willReturn(EXPECTED_RESULT);

        ComplexObject result = calculator.add(OBJECT_A, OBJECT_B);

        then(countingService).should().add(OBJECT_A, OBJECT_B);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void subtractsByCountingService() {
        given(countingService.subtract(OBJECT_A, OBJECT_B)).willReturn(EXPECTED_RESULT);

        ComplexObject result = calculator.subtract(OBJECT_A, OBJECT_B);

        then(countingService).should().subtract(OBJECT_A, OBJECT_B);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void multipliesByCountingService() {
        given(countingService.multiply(OBJECT_A, OBJECT_B)).willReturn(EXPECTED_RESULT);

        ComplexObject result = calculator.multiply(OBJECT_A, OBJECT_B);

        then(countingService).should().multiply(OBJECT_A, OBJECT_B);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void dividesByCountingService() {
        given(countingService.divide(OBJECT_A, OBJECT_B)).willReturn(EXPECTED_RESULT);

        ComplexObject result = calculator.divide(OBJECT_A, OBJECT_B);

        then(countingService).should().divide(OBJECT_A, OBJECT_B);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void validatesServiceFieldEquality_ADD() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.add(objectA, objectB));
    }


    @Test
    public void validatesServiceFieldEquality_SUBTRACT() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.subtract(objectA, objectB));
    }

    @Test
    public void validatesServiceFieldEquality_DIVIDE() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.divide(objectA, objectB));
    }


    @Test
    public void validatesServiceFieldEquality_MULTIPLY() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.multiply(objectA, objectB));
    }
}
