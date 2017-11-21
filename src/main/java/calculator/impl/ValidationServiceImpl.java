package calculator.impl;

import calculator.domain.ComplexObject;
import calculator.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validate(ComplexObject object) {
        if (object.getService() == null) {
            throw new IllegalArgumentException("Missing service: " + object);
        }
        if (!object.getService().isValid(object)) {
            throw new IllegalArgumentException("Required " + object.getService().getRequirement());
        }
    }

}
