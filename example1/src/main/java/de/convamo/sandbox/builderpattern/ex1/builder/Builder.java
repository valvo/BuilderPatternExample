package de.convamo.sandbox.builderpattern.ex1.builder;

/**
 * Created by valeriusv on 25.02.16.
 */
public interface Builder<T> {

    public T build() throws BuilderValidatorException;

    public T build(Builder<T> builder) throws BuilderValidatorException;
}
