package de.convamo.sandbox.builderpattern.ex1.entity;

/**
 * Created by valeriusv on 25.02.16.
 */
public class VideoFormat implements Format{

    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
