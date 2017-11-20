package calculator.impl;

import calculator.domain.ComplexObject;

public class ReverseCountingServiceImpl extends CountingServiceImpl {

    @Override
    public ComplexObject add(ComplexObject param1, ComplexObject param2) {
        ComplexObject result = new ComplexObject();
        result.setValueA(param1.getValueA() + param2.getValueB());
        result.setValueB(param1.getValueB() + param2.getValueA());
        return result;
    }

    @Override
    public ComplexObject subtract(ComplexObject param1, ComplexObject param2) {
        ComplexObject result = new ComplexObject();
        result.setValueA(param1.getValueA() - param2.getValueB());
        result.setValueB(param1.getValueB() - param2.getValueA());
        return result;
    }

    @Override
    public ComplexObject multiply(ComplexObject param1, ComplexObject param2) {
        ComplexObject result = new ComplexObject();
        result.setValueA(param1.getValueA() * param2.getValueB());
        result.setValueB(param1.getValueB() * param2.getValueA());
        return result;
    }

    @Override
    public ComplexObject divide(ComplexObject param1, ComplexObject param2) {
        ComplexObject result = new ComplexObject();
        result.setValueA(param1.getValueA() / param2.getValueB());
        result.setValueB(param1.getValueB() / param2.getValueA());
        return result;
    }
}
