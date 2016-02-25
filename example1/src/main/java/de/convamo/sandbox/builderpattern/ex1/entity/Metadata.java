package de.convamo.sandbox.builderpattern.ex1.entity;

import de.convamo.sandbox.builderpattern.ex1.builder.Builder;
import de.convamo.sandbox.builderpattern.ex1.builder.BuilderValidator;
import de.convamo.sandbox.builderpattern.ex1.builder.BuilderValidatorException;
import de.convamo.sandbox.builderpattern.ex1.builder.ValidationError;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * a Entity
 * Created by valeriusv on 25.02.16.
 */
public class Metadata {

    private Long id;
    private String title;
    private String description;
    private Set<Broadcast> broadcasts;
    private MediaType mediaType;
    private Format format;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(Set<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }


    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * Builder-Klasse für Metadata-Objekte
     */
    public static class MetadataBuilder implements Builder<Metadata> {

        /**
         * vordefinierte MetadataBuilder
         */
        public static final MetadataBuilder METADATA_VIDEO = new MetadataBuilder()
                .setMediaType(MediaType.Video);

        public static final MetadataBuilder METADATA_AUDIO = new MetadataBuilder()
                .setId(123L)
                .setMediaType(MediaType.Audio);

        private Long nestedId;
        private String nestedTitle;
        private String nestedDescription;
        private Set<Broadcast> nestedBroadcasts = new HashSet<Broadcast>();
        private MediaType nestedMediaType;
        private Format nestedFormat;

        private List<BuilderValidator> validatorList = new ArrayList<>();


        public MetadataBuilder setId(final Long newId) {
            this.nestedId = newId;
            return this;
        }

        public MetadataBuilder setTitle(String title) {
            this.nestedTitle = title;
            return this;
        }

        public MetadataBuilder setDescription(String description) {
            this.nestedDescription = description;
            return this;
        }


        public MetadataBuilder addBroadcast(Broadcast broadcast) {
            this.nestedBroadcasts.add(broadcast);
            return this;
        }

        public MetadataBuilder setMediaType(MediaType mediaType) {
            this.nestedMediaType = mediaType;
            return this;
        }

        public MetadataBuilder setFormat(Format format) {
            this.nestedFormat = format;
            this.validatorList.add(new BuilderValidator<Metadata>() {
                @Override
                public boolean predicate() {
                    return true; //validate always
                }

                @Override
                public ValidationError validate(final Metadata validationObject) {
                    ValidationError result = null;
                    MediaType mediaType = validationObject.getMediaType();
                    Format validationObjectFormat = validationObject.getFormat();
                    if (MediaType.Audio.equals(mediaType) && VideoFormat.class.isAssignableFrom(validationObject.getClass())) {
                        result = new ValidationError("Es wurde versucht einem Audio ein Videoformat zuzuweisen");
                    } else if (MediaType.Video.equals(mediaType) && AudioFormat.class.isAssignableFrom(validationObject.getClass())) {
                        result = new ValidationError("Es wurde versucht einem Video ein Audioformat zuzuweisen");
                    }
                    return result;
                }
            });

            return this;
        }

        private Metadata create(Metadata metadata) throws BuilderValidatorException {
            Metadata newMetadata = metadata == null ? new Metadata() : metadata;

            if (this.nestedId != null) {
                newMetadata.setId(nestedId);
            }
            if (this.nestedTitle != null) {
                newMetadata.setTitle(nestedTitle);
            }
            if (this.nestedDescription != null) {
                newMetadata.setDescription(nestedDescription);
            }
            if (this.nestedBroadcasts != null) {
                newMetadata.setBroadcasts(this.nestedBroadcasts);
            }
            if (this.nestedMediaType != null) {
                newMetadata.setMediaType(nestedMediaType);
            }
            if (this.nestedFormat != null) {
                newMetadata.setFormat(nestedFormat);
            }

            //Validierung
            List<ValidationError> validationErrors = this.validatorList.stream().map(p ->
                    p.validate(newMetadata)
            ).collect(Collectors.toList());
            if (validationErrors.size() > 0) {
                throw new BuilderValidatorException(validationErrors);
            }

            return newMetadata;
        }

        @Override
        public Metadata build() throws BuilderValidatorException {
            return this.create(null);
        }

        @Override
        public Metadata build(Builder<Metadata> builder) throws BuilderValidatorException {
            return this.create(builder.build());
        }
    }

}
