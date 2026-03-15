# WORK IN PROGRESS

Still not published to Maven Central.

# Gradle Avro Plugin

A Gradle plugin that wraps Apache Avro to generate Java classes from `.avsc` files.

## Usage

### Applying the Plugin

Add the plugin to your `build.gradle.kts` file:

```kotlin
plugins {
    id("io.github.flumennigrum.gradle.avro") version "VERSION_HERE"
}
```

### Configuration

Configure the plugin using the `avro` extension block:

```kotlin
avro {
    // Input directory for Avro schema files (.avsc)
    source.from("src/main/avro")
    
    // Output directory for generated Java sources
    outputDir.set(layout.buildDirectory.dir("generated/source/avro/main"))
}
```

### Task

The plugin registers a task named `generateAvroJava`:

```bash
./gradlew generateAvroJava
```

This task will:
1. Find all `.avsc` files in the configured source directory.
2. Generate Java classes using Avro's `SpecificCompiler`.
3. Add the generated sources to the main Java source set.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for details.
PRs and issues are welcome!

## License

This project is licensed under the Apache License, Version 2.0.
