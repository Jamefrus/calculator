package calculator.service;

import calculator.domain.ComplexObject;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("unused")
public class CountingServiceProvider {
    static final String DIVIDABLE = "dividable";
    static final String UNDIVIDABLE = "undividable";
    static final String ALL_SETS = DIVIDABLE + ", " + UNDIVIDABLE;
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

    public static Collection<ComplexObject[]> dividable() {
        return Arrays.asList(SIMPLE, WITH_NEGATIVE, ZERO_IN_DIVIDED_A, ZERO_IN_DIVIDED_B);
    }

    public static Collection<ComplexObject[]> undividable() {
        return Arrays.asList(ZERO_IN_DIVIDER_A, ZERO_IN_DIVIDER_B);
    }
}
