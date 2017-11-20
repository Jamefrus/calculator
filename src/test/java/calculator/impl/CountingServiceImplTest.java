package calculator.impl;

import calculator.domain.ComplexObject;
import calculator.service.CountingService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static calculator.impl.CountingServiceProvider.DIVIDABLE;
import static calculator.impl.CountingServiceProvider.UNDIVIDABLE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class CountingServiceImplTest {
    private CountingService service = new CountingServiceImpl();

    @Test
    @Parameters(source = CountingServiceProvider.class, method = CountingServiceProvider.ALL_SETS)
    public void addsValues(ComplexObject objectA, ComplexObject objectB) {
        int resultA = objectA.getValueA() + objectB.getValueA();
        int resultB = objectA.getValueB() + objectB.getValueB();

        ComplexObject result = service.add(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test
    @Parameters(source = CountingServiceProvider.class, method = CountingServiceProvider.ALL_SETS)
    public void subtractsValues(ComplexObject objectA, ComplexObject objectB) throws Exception {
        int resultA = objectA.getValueA() - objectB.getValueA();
        int resultB = objectA.getValueB() - objectB.getValueB();

        ComplexObject result = service.subtract(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test
    @Parameters(source = CountingServiceProvider.class, method = CountingServiceProvider.ALL_SETS)
    public void multipliesValues(ComplexObject objectA, ComplexObject objectB) throws Exception {
        int resultA = objectA.getValueA() * objectB.getValueA();
        int resultB = objectA.getValueB() * objectB.getValueB();

        ComplexObject result = service.multiply(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test
    @Parameters(source = CountingServiceProvider.class, method = DIVIDABLE)
    public void dividesValues(ComplexObject objectA, ComplexObject objectB) throws Exception {
        int resultA = objectA.getValueA() / objectB.getValueA();
        int resultB = objectA.getValueB() / objectB.getValueB();

        ComplexObject result = service.divide(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test
    @Parameters(source = CountingServiceProvider.class, method = UNDIVIDABLE)
    public void failsForZeroDividing(ComplexObject objectA, ComplexObject objectB) throws Exception {
        assertThatCode(() -> service.divide(objectA, objectB)).isInstanceOf(ArithmeticException.class);
    }
}
