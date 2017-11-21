package calculator.impl;

import calculator.domain.ComplexObject;
import calculator.service.Service;
import calculator.service.ValidationService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(JUnitParamsRunner.class)
public class ValidationServiceImplTest {

    public static final String ANY_MESSAGE = "any requirement";
    @InjectMocks
    private ValidationService validationService = new ValidationServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void approvesValuesForTrueService() throws Exception {
        Service service = Mockito.mock(Service.class);
        given(service.isValid(any())).willReturn(true);
        ComplexObject object = object(service);

        assertThatCode(() -> validationService.validate(object)).doesNotThrowAnyException();
    }

    private ComplexObject object(Service service) {
        ComplexObject object = new ComplexObject();
        object.setService(service);
        return object;
    }

    @Test
    public void disapprovesValuesForFalseService() throws Exception {
        Service service = Mockito.mock(Service.class);
        given(service.isValid(any())).willReturn(false);
        given(service.getRequirement()).willReturn(ANY_MESSAGE);
        ComplexObject object = object(service);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> validationService.validate(object))
                .withMessageContaining(ANY_MESSAGE);
    }

    @Test
    public void disapprovesValuesForNullService() throws Exception {
        ComplexObject object = object(null);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> validationService.validate(object))
                .withMessageContaining("service");
    }
}