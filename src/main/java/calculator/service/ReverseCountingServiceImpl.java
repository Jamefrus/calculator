package calculator.service;

import calculator.domain.ComplexObject;

public class ReverseCountingServiceImpl implements CountingService {

    public ComplexObject add(ComplexObject param1, ComplexObject param2) {
        ComplexObject result = new ComplexObject();
        result.setValueA(param1.getValueA() + param2.getValueB());
        result.setValueB(param1.getValueB() + param2.getValueA());
        return result;
    }

    public ComplexObject subtract(ComplexObject param1, ComplexObject param2) {
        return null;
    }

    public ComplexObject multiply(ComplexObject param1, ComplexObject param2) {
        return null;
    }

    public ComplexObject divide(ComplexObject param1, ComplexObject param2) {
        return null;
    }
}
