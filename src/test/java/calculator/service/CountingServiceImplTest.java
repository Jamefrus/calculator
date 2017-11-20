package calculator.service;

import calculator.domain.ComplexObject;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitParamsRunner.class)
public class CountingServiceImplTest {
    private static final ComplexObject[] SIMPLE = {
            new ComplexObject(1, 2),
            new ComplexObject(3, 4)
    };
    private static final ComplexObject[] ZERO_IN_DIVIDED_A = {
            new ComplexObject(3, 0),
            new ComplexObject(3, 2)
    };
    private static final ComplexObject[] ZERO_IN_DIVIDED_B = {
            new ComplexObject(0, 3),
            new ComplexObject(3, 2)
    };
    private static final ComplexObject[] ZERO_IN_DIVIDER_A = {
            new ComplexObject(3, 4),
            new ComplexObject(0, 23)
    };
    private static final ComplexObject[] ZERO_IN_DIVIDER_B = {
            new ComplexObject(2, 6),
            new ComplexObject(4, 0)
    };
    private static final ComplexObject[] WITH_NEGATIVE = {
            new ComplexObject(-4, 5),
            new ComplexObject(14, -3)
    };
    private static final String ALL_SETS = "dividable, undividable";
    private CountingService service = new CountingServiceImpl();

    public Collection<ComplexObject[]> dividable() {
        return Arrays.asList(SIMPLE, WITH_NEGATIVE, ZERO_IN_DIVIDED_A, ZERO_IN_DIVIDED_B);
    }

    public Collection<ComplexObject[]> undividable() {
        return Arrays.asList(ZERO_IN_DIVIDER_A, ZERO_IN_DIVIDER_B);
    }


    @Test @Parameters(method = ALL_SETS)
    public void addsValues(ComplexObject objectA, ComplexObject objectB) {
        int resultA = objectA.getValueA() + objectB.getValueA();
        int resultB = objectA.getValueB() + objectB.getValueB();

        ComplexObject result = service.add(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test @Parameters(method = ALL_SETS)
    public void subtractsValues(ComplexObject objectA, ComplexObject objectB) throws Exception {
        int resultA = objectA.getValueA() - objectB.getValueA();
        int resultB = objectA.getValueB() - objectB.getValueB();

        ComplexObject result = service.subtract(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test @Parameters(method = ALL_SETS)
    public void multipliesValues(ComplexObject objectA, ComplexObject objectB) throws Exception {
        int resultA = objectA.getValueA() * objectB.getValueA();
        int resultB = objectA.getValueB() * objectB.getValueB();

        ComplexObject result = service.multiply(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test @Parameters(method = "dividable")
    public void dividesValues(ComplexObject objectA, ComplexObject objectB) throws Exception {
        assumeTrue("Ignore zero dividing for objectB.valueA", objectB.getValueA() != 0);
        assumeTrue("Ignore zero dividing for objectB.valueB", objectB.getValueB() != 0);
        int resultA = objectA.getValueA() / objectB.getValueA();
        int resultB = objectA.getValueB() / objectB.getValueB();

        ComplexObject result = service.divide(objectA, objectB);

        then(result)
                .returns(resultA, ComplexObject::getValueA)
                .returns(resultB, ComplexObject::getValueB);
    }

    @Test @Parameters(method = "undividable")
    public void failsForZeroDividing(ComplexObject objectA, ComplexObject objectB) throws Exception {
        assertThatCode(() -> service.divide(objectA, objectB)).isInstanceOf(ArithmeticException.class);
    }
}
