package calculator.domain;

public enum Service {
    ANY("any values") {
        @Override
        public boolean isValid(ComplexObject complexObject) {
            return true;
        }
    },
    POSITIVE("positive values") {
        @Override
        public boolean isValid(ComplexObject complexObject) {
            return complexObject.getValueA() > 0 && complexObject.getValueB() > 0;
        }
    },
    ESOTERIC( "valueA (in range [-100, +50]) and valueB (negative)") {
        @Override
        public boolean isValid(ComplexObject complexObject) {
            boolean inRange = complexObject.getValueA() >= -100 && complexObject.getValueA() <= 50;
            return inRange && complexObject.getValueB() < 0;
        }
    };

    private String requirement;

    Service(String requirement) {
        this.requirement = requirement;
    }

    public String getRequirement() {
        return requirement;
    }

    public abstract boolean isValid(ComplexObject complexObject);
}
