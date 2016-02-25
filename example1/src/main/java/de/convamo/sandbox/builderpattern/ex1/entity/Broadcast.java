package de.convamo.sandbox.builderpattern.ex1.entity;

import de.convamo.sandbox.builderpattern.ex1.builder.BuilderValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriusv on 25.02.16.
 */
public class Broadcast {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class BroadcastBuilder {

        public static final BroadcastBuilder BROADCAST_SIMPLE = new Broadcast.BroadcastBuilder()
                .setName("simple Broadcast");

        private Long nestedId;
        private String nestedName;
        private List<BuilderValidator> validatorList = new ArrayList<>();

        public BroadcastBuilder setId(Long id) {
            this.nestedId = id;
            return this;
        }

        public BroadcastBuilder setName(String name) {
            this.nestedName = name;
            return this;
        }



        public Broadcast create(Broadcast broadcast) {
            Broadcast newBroadcast = broadcast;
            if (newBroadcast == null) {
                newBroadcast = new Broadcast();
            }

            if (this.nestedId != null) {
                newBroadcast.setId(nestedId);
            }
            if (nestedName != null) {
                newBroadcast.setName(nestedName);
            }

            return newBroadcast;
        }

        public Broadcast build() {
            return this.create(null);
        }

        public Broadcast build(BroadcastBuilder broadcastBuilder) {
            return this.create(broadcastBuilder.build());
        }
    }
}
