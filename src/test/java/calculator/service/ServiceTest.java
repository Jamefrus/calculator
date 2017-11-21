package calculator.service;

import calculator.domain.ComplexObject;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class ServiceTest {

    private static void shouldApprove(Service service, ComplexObject object) {
        assertTrue(service.name() + " should approve " + object, service.isValid(object));
    }

    private static void shouldDisapprove(Service service, ComplexObject object) {
        assertFalse(service.name() + " should disapprove " + object, service.isValid(object));
    }

    @RunWith(JUnitParamsRunner.class)
    public static class ForAny {

        @Test
        @Parameters(method = "anyValues")
        public void approvesAnyValuesForService_ANY(ComplexObject object) throws Exception {
            shouldApprove(Service.ANY, object);
        }

        public Object[] anyValues() {
            Service service = Service.ANY;
            return new ComplexObject[]{
                    new ComplexObject(5, 4, service),
                    new ComplexObject(0, 0, service),
                    new ComplexObject(-5, -4, service),
                    new ComplexObject(-4, 5, service),
                    new ComplexObject(0, 5, service),
                    new ComplexObject(-5, 4, service)
            };
        }

    }

    @RunWith(JUnitParamsRunner.class)
    public static class ForPositive {
        @Test
        @Parameters(method = "zeroOrNegativeValues")
        public void disapprovesZeroAndNegativeValuesForService_POSITIVE(ComplexObject object) throws Exception {
            shouldDisapprove(Service.POSITIVE, object);
        }

        public Object[] zeroOrNegativeValues() {
            Service service = Service.POSITIVE;
            return new ComplexObject[]{
                    new ComplexObject(0, 0, service),
                    new ComplexObject(-5, -4, service),
                    new ComplexObject(-4, 5, service),
                    new ComplexObject(0, 5, service),
                    new ComplexObject(-5, 4, service),
                    new ComplexObject(4, 0, service)
            };
        }

        @Test
        public void approvesPositiveValuesForService_POSITIVE() throws Exception {
            ComplexObject object = new ComplexObject(4, 3, Service.POSITIVE);
            shouldApprove(Service.POSITIVE, object);
        }
    }

    @RunWith(JUnitParamsRunner.class)
    public static class ForEsoteric {
        @Test
        @Parameters(method = "nonEsotericValues")
        public void disapprovesOutOfRangeValueA_AndZeroOrPositiveValueB_ForService_ESOTERIC(ComplexObject object) throws Exception {
            shouldDisapprove(Service.ESOTERIC, object);
        }

        public Object[] nonEsotericValues() {
            Service service = Service.ESOTERIC;
            return new Object[]{
                    new ComplexObject(-101, -10, service),
                    new ComplexObject(-10020, -40, service),
                    new ComplexObject(-50, 40, service),
                    new ComplexObject(51, -40, service),
                    new ComplexObject(40, 40, service),
                    new ComplexObject(-1004, 40003, service),
                    new ComplexObject(0, 0, service),
                    new ComplexObject(-40, 0, service),
            };
        }

        @Test
        @Parameters(method = "esotericValues")
        public void approvesInRangeValueA_AndNegativeValueB_ForService_ESOTERIC(ComplexObject object) throws Exception {
            shouldApprove(Service.ESOTERIC, object);
        }

        public Object[] esotericValues() {
            Service service = Service.ESOTERIC;
            return new Object[]{
                    new ComplexObject(-100, -1, service),
                    new ComplexObject(50, -1, service),
                    new ComplexObject(0, -10, service),
                    new ComplexObject(22, -100034400, service)
            };
        }
    }

}