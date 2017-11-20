package calculator.service;

import calculator.domain.ComplexObject;

public interface CountingService {
    ComplexObject add(ComplexObject param1, ComplexObject param2);
    ComplexObject subtract(ComplexObject param1, ComplexObject param2);
    ComplexObject multiply(ComplexObject param1, ComplexObject param2);
    ComplexObject divide(ComplexObject param1, ComplexObject param2);
}
