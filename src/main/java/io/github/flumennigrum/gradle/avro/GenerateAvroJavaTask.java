package io.github.flumennigrum.gradle.avro;

import org.apache.avro.compiler.specific.SpecificCompiler;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

public abstract class GenerateAvroJavaTask extends DefaultTask {

    @InputFiles
    public abstract ConfigurableFileCollection getSource();

    @OutputDirectory
    public abstract DirectoryProperty getOutputDir();

    @TaskAction
    public void generate() throws IOException {
        File outputDirectory = getOutputDir().get().getAsFile();
        
        for (File schemaFile : getSource().getFiles()) {
            if (schemaFile.getName().endsWith(".avsc")) {
                getLogger().info("Generating Java classes for Avro schema: {}", schemaFile.getName());
                SpecificCompiler.compileSchema(schemaFile, outputDirectory);
            }
        }
    }
}
