package io.github.flumennigrum.gradle.avro;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskProvider;

public class AvroGeneratorPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        // Ensure the Java plugin is applied so we can attach to its lifecycle and source sets
        project.getPluginManager().apply("java");

        // Register the extension to allow users to configure the plugin via an 'avro' block
        AvroExtension extension = project.getExtensions().create("avro", AvroExtension.class);
        
        // Define default values for the extension
        extension.getSource().from(project.fileTree("src/main/avro").matching(pattern -> pattern.include("**/*.avsc")));
        extension.getOutputDir().convention(project.getLayout().getBuildDirectory().dir("generated/source/avro/main"));

        TaskProvider<GenerateAvroJavaTask> generateAvroTask = project.getTasks().register("generateAvroJava", GenerateAvroJavaTask.class, task -> {
            task.setGroup("build");
            task.setDescription("Generates Java classes from Avro .avsc schemas.");
            
            // Link the task's properties to the extension's properties
            task.getSource().from(extension.getSource());
            task.getOutputDir().set(extension.getOutputDir());
        });

        // Add the generated directory to the main Java source set
        JavaPluginExtension javaExtension = project.getExtensions().getByType(JavaPluginExtension.class);
        SourceSetContainer sourceSets = javaExtension.getSourceSets();
        sourceSets.named(SourceSet.MAIN_SOURCE_SET_NAME, sourceSet -> {
            sourceSet.getJava().srcDir(generateAvroTask.flatMap(GenerateAvroJavaTask::getOutputDir));
        });

        // Ensure compileJava depends on the Avro code generation task
        project.getTasks().named("compileJava").configure(task -> {
            task.dependsOn(generateAvroTask);
        });
    }
}
