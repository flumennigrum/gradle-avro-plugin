# WORK IN PROGRESS

Still not published to Maven Central.

# Gradle Avro Plugin

A Gradle plugin that wraps Apache Avro to generate Java classes from `.avsc` files.

## Usage

### Applying the Plugin

Add the plugin to your `build.gradle.kts` file:

```kotlin
plugins {
    id("io.github.flumennigrum.gradle.avro") version "0.1.0"
}
```

### Configuration

Default values:

- input directory: `src/main/avro`
- output directory: `build/generated/source/avro/main`

If your project structure follows the default values, you're good to go, no more configuration needed!

If you need to change the default values, you can configure the plugin using the `avro` extension block:

```kotlin
avro {
    // Input directory for Avro schema files (.avsc)
    source.from("src/main/custom-avro-directory")
    
    // Output directory for generated Java sources
    outputDir.set(layout.buildDirectory.dir("generated/source/custom-avro-directory/main"))
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

### Gradle compatibility

This plugin is compatible with Gradle 7.3 and later.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for details.
PRs and issues are welcome!

## License

This project is licensed under the Apache License, Version 2.0.
