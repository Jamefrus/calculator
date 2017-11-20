package calculator.service;

import calculator.domain.ComplexObject;
import calculator.domain.Service;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@RunWith(JUnitParamsRunner.class)
public class ValidationServiceImplTest {

    @InjectMocks
    private ValidationService validationService = new ValidationServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Parameters(method = "anyValues")
    public void approvesAnyValuesForService_ANY(ComplexObject object) throws Exception {
        assertThatCode(() -> validationService.validate(object)).doesNotThrowAnyException();
    }

    public Object[] anyValues() {
        Service service = Service.ANY;
        return new ComplexObject[]{
                obj(5, 4, service),
                obj(0, 0, service),
                obj(-5, -4, service),
                obj(-4, 5, service),
                obj(0, 5, service),
                obj(-5, 4, service)
        };
    }

    private ComplexObject obj(int valueA, int valueB, Service service) {
        ComplexObject obj = new ComplexObject();
        obj.setValueA(valueA);
        obj.setValueB(valueB);
        obj.setService(service);
        return obj;
    }

    @Test
    @Parameters(method = "zeroOrNegativeValues")
    public void disapprovesZeroAndNegativeValuesForService_POSITIVE(ComplexObject object) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> validationService.validate(object))
                .withMessageContaining("positive");
    }

    public Object[] zeroOrNegativeValues() {
        Service service = Service.POSITIVE;
        return new ComplexObject[]{
                obj(0, 0, service),
                obj(-5, -4, service),
                obj(-4, 5, service),
                obj(0, 5, service),
                obj(-5, 4, service),
                obj(4, 0, service)
        };
    }

    @Test
    public void approvesPositiveValuesForService_POSITIVE() throws Exception {
        ComplexObject obj = obj(4, 3, Service.POSITIVE);
        assertThatCode(() -> validationService.validate(obj)).doesNotThrowAnyException();
    }

    @Test @Parameters(method = "nonEsotericValues")
    public void disapprovesOutOfRangeValueA_AndZeroOrPositiveValueB_ForService_ESOTERIC(ComplexObject object) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> validationService.validate(object))
                .withMessageContaining("[-100, +50]")
                .withMessageContaining("negative");
    }

    public Object[] nonEsotericValues(){
        Service service = Service.ESOTERIC;
        return new Object[]{
                obj(-101, -10, service),
                obj(-10020, -40, service),
                obj(-50, 40, service),
                obj(51, -40, service),
                obj(40, 40, service),
                obj(-1004, 40003, service),
                obj(0, 0, service),
                obj(-40, 0, service),
        };
    }

    @Test @Parameters(method = "esotericValues")
    public void approvesInRangeValueA_AndNegativeValueB_ForService_ESOTERIC(ComplexObject object) throws Exception {
        assertThatCode(() -> validationService.validate(object)).doesNotThrowAnyException();
    }

    public Object[] esotericValues(){
        Service service = Service.ESOTERIC;
        return new Object[]{
                obj(-100, -1, service),
                obj(50, -1, service),
                obj(0, -10, service),
                obj(22, -100034400, service)
        };
    }

    @Test
    public void failsOnNullService() throws Exception {
        ComplexObject object = new ComplexObject();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> validationService.validate(object));
    }
}