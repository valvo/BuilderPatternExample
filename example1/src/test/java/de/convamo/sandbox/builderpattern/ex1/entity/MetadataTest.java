package de.convamo.sandbox.builderpattern.ex1.entity;

import de.convamo.sandbox.builderpattern.ex1.builder.BuilderValidatorException;
import de.convamo.sandbox.builderpattern.ex1.entity.Broadcast;
import de.convamo.sandbox.builderpattern.ex1.entity.MediaType;
import de.convamo.sandbox.builderpattern.ex1.entity.Metadata;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valeriusv on 25.02.16.
 */
public class MetadataTest {

    @Test
    public void testCreateMetadata() throws BuilderValidatorException {
        Metadata metadata = new Metadata.MetadataBuilder()
                .setId(99L)
                .setDescription("description")
                .build();

        assertEquals((long) 99L,(long) metadata.getId());
        assertEquals("description",metadata.getDescription());
    }

    @Test
    public void testVideoMetadata() throws BuilderValidatorException {
        Metadata metadata = new Metadata.MetadataBuilder().build(Metadata.MetadataBuilder.METADATA_VIDEO);
        assertEquals(MediaType.Video,metadata.getMediaType());
    }

    @Test
    public void testVideoMetadata_withBroadcast() throws BuilderValidatorException {
        Metadata metadata = new Metadata.MetadataBuilder()
                .addBroadcast(new Broadcast())
                .build(Metadata.MetadataBuilder.METADATA_VIDEO);
        assertEquals(MediaType.Video,metadata.getMediaType());
        assertEquals(1,metadata.getBroadcasts().size());
    }

    @Test
    public void testAudioMetadata_withBroadcast() throws BuilderValidatorException {

    Broadcast broadcast =  new Broadcast.BroadcastBuilder()
                .setName("einfacher Broadcast")
                .build(Broadcast.BroadcastBuilder.BROADCAST_SIMPLE);

        Metadata metadata = new Metadata.MetadataBuilder()
                .addBroadcast(broadcast)
                .setId(101L)
                .build(Metadata.MetadataBuilder.METADATA_AUDIO);
        assertEquals(MediaType.Audio,metadata.getMediaType());
        assertEquals(1,metadata.getBroadcasts().size());
        assertEquals((long)101L,(long)metadata.getId());
        assertEquals("einfacher Broadcast",metadata.getBroadcasts().stream().findFirst().get().getName());
    }

    /**
     * Der Test wirft wirft eine Exception, weil einem Video-Metadata ein AudioFormat zugewiesen wird
     * @throws BuilderValidatorException
     */
    @Test(expected = BuilderValidatorException.class)
    public void testValidationFail() throws BuilderValidatorException {
        Metadata metadata = new Metadata.MetadataBuilder()
                .addBroadcast(new Broadcast())
                .setFormat(new AudioFormat())//Fehler!! Grund für die Exception: VideoMetadata dürfen kein AudioFormat besitzen
                .build(Metadata.MetadataBuilder.METADATA_VIDEO);
    }
}