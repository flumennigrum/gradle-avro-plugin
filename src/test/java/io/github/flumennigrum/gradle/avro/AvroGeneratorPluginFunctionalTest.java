package io.github.flumennigrum.gradle.avro;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvroGeneratorPluginFunctionalTest {

    @TempDir
    File testProjectDir;
    private File buildFile;

    @BeforeEach
    public void setup() throws IOException {
        buildFile = new File(testProjectDir, "build.gradle.kts");
    }

    @Test
    public void testCodeGeneration() throws IOException {
        String buildFileContent = "plugins {\n" +
                "    id(\"io.github.flumennigrum.gradle.avro\")\n" +
                "}\n" +
                "repositories {\n" +
                "    mavenCentral()\n" +
                "}\n" +
                "dependencies {\n" +
                "    implementation(\"org.apache.avro:avro:1.11.3\")\n" +
                "}\n";
        writeFile(buildFile, buildFileContent);

        File avroDir = new File(testProjectDir, "src/main/avro");
        avroDir.mkdirs();

        File schemaFile = new File(avroDir, "user.avsc");
        String schemaContent = "{\n" +
                "  \"namespace\": \"example.avro\",\n" +
                "  \"type\": \"record\",\n" +
                "  \"name\": \"User\",\n" +
                "  \"fields\": [\n" +
                "    {\"name\": \"name\", \"type\": \"string\"},\n" +
                "    {\"name\": \"age\",  \"type\": [\"int\", \"null\"]}\n" +
                "  ]\n" +
                "}";
        writeFile(schemaFile, schemaContent);

        // Run the build
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments("build", "--info")
                .withPluginClasspath()
                .forwardOutput()
                .build();

        assertEquals(TaskOutcome.SUCCESS, result.task(":generateAvroJava").getOutcome());
        assertEquals(TaskOutcome.SUCCESS, result.task(":compileJava").getOutcome());

        // Verify generated file
        File generatedFile = new File(testProjectDir, "build/generated/source/avro/main/example/avro/User.java");
        assertTrue(generatedFile.exists());
        
        // Check if class file was generated indicating successful compilation
        File classFile = new File(testProjectDir, "build/classes/java/main/example/avro/User.class");
        assertTrue(classFile.exists());
    }

    private void writeFile(File destination, String content) throws IOException {
        Files.writeString(destination.toPath(), content);
    }
}
