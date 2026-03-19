package io.github.flumennigrum.gradle.avro;

import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.DirectoryProperty;

public abstract class AvroExtension {
    /**
     * The input directory for Avro schema files (.avsc).
     */
    public abstract ConfigurableFileCollection getSource();

    /**
     * The output directory for generated Java sources.
     */
    public abstract DirectoryProperty getOutputDir();
}
