package calculator.service;

import calculator.domain.ComplexObject;
import calculator.domain.Service;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validate(ComplexObject object) {
        if(object.getService() == Service.POSITIVE){
            require("positive value", isPositiveValue(object));
        } else if(object.getService() == Service.ESOTERIC){
            require("valueA in range [-100, +50] and negative valueB", isEsotericValue(object));
        } else if(object.getService() != Service.ANY){
            throw new IllegalArgumentException("Missing service: " + object.getService());
        }
    }

    private boolean isEsotericValue(ComplexObject object) {
        boolean inRange = object.getValueA() >= -100 && object.getValueA() <= 50;
        return inRange && object.getValueB() < 0;
    }

    private boolean isPositiveValue(ComplexObject object) {
        return object.getValueA() > 0 && object.getValueB() > 0;
    }

    private void require(String description, boolean requirement) {
        if(!requirement) {
            throw new IllegalArgumentException("required " + description);
        }
    }

}
