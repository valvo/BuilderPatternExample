package de.convamo.sandbox.builderpattern.ex1.builder;

import java.util.List;

/**
 * Created by valeriusv on 25.02.16.
 */
public class BuilderValidatorException extends Exception {
    List<ValidationError> validationErrors = null;

    public BuilderValidatorException(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
