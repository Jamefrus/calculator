package calculator;

import calculator.domain.ComplexObject;
import calculator.service.Service;
import calculator.service.CountingService;
import calculator.service.ValidationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {
    private static final ComplexObject OBJECT_A = new ComplexObject(1, 2);
    private static final ComplexObject OBJECT_B = new ComplexObject(3, 4);
    private static final ComplexObject EXPECTED_RESULT = new ComplexObject(0, 0);
    
    @Mock
    private CountingService countingService;

    @Mock
    private ValidationService validationService;

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

        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.add(objectA, objectB));
    }


    @Test
    public void validatesServiceFieldEquality_SUBTRACT() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.subtract(objectA, objectB));
    }

    @Test
    public void validatesServiceFieldEquality_DIVIDE() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.divide(objectA, objectB));
    }


    @Test
    public void validatesServiceFieldEquality_MULTIPLY() throws Exception {
        ComplexObject objectA = new ComplexObject(3, 2);
        objectA.setService(Service.ESOTERIC);
        ComplexObject objectB = new ComplexObject(3, 2);
        objectA.setService(Service.ANY);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.multiply(objectA, objectB));
    }

    @Test
    public void validatesByValidationService_ADD() throws Exception {
        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_A));
        assertThatThrownBy(() -> calculator.add(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_A);

        reset(validationService);

        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_B));
        assertThatThrownBy(() -> calculator.add(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_B);
    }

    @Test
    public void validatesByValidationService_SUBTRACT() throws Exception {
        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_A));
        assertThatThrownBy(() -> calculator.subtract(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_A);

        reset(validationService);

        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_B));
        assertThatThrownBy(() -> calculator.subtract(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_B);
    }

    @Test
    public void validatesByValidationService_DIVIDE() throws Exception {
        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_A));
        assertThatThrownBy(() -> calculator.divide(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_A);

        reset(validationService);

        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_B));
        assertThatThrownBy(() -> calculator.divide(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_B);
    }

    @Test
    public void validatesByValidationService_MULTIPLY() throws Exception {
        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_A));
        assertThatThrownBy(() -> calculator.multiply(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_A);

        reset(validationService);

        willThrow(UnsupportedOperationException.class).given(validationService).validate(same(OBJECT_B));
        assertThatThrownBy(() -> calculator.multiply(OBJECT_A, OBJECT_B)).isExactlyInstanceOf(UnsupportedOperationException.class);
        then(validationService).should().validate(OBJECT_B);
    }

}
