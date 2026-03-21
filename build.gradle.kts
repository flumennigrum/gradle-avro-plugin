plugins {
    `java-gradle-plugin`
    `maven-publish`
}

group = "io.github.flumennigrum.gradle"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Avro compiler to generate Java code from schemas directly
    implementation("org.apache.avro:avro-compiler:1.11.3")

    // Logging dependency if needed, Avro uses SLF4J
    implementation("org.slf4j:slf4j-api:2.0.12")
    
    // Test dependencies
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

gradlePlugin {
    plugins {
        create("avroPlugin") {
            id = "io.github.flumennigrum.gradle.avro"
            implementationClass = "io.github.flumennigrum.gradle.avro.AvroGeneratorPlugin"
            displayName = "Gradle Avro Plugin"
            description = "A Gradle plugin that wraps Apache Avro to generate Java classes from .avsc files."
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11)) // Recommended baseline for Gradle 8/9
    }
}
