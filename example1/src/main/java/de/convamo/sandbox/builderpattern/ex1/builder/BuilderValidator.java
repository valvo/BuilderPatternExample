package de.convamo.sandbox.builderpattern.ex1.builder;

/**
 * Created by valeriusv on 25.02.16.
 */
public interface BuilderValidator<T> {
    public boolean predicate();
    public ValidationError validate(final T validationObject);
}
