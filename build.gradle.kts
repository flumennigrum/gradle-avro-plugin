import org.gradle.plugin.compatibility.compatibility

plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "2.1.1"
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
    website.set("https://github.com/flumennigrum/gradle-avro-plugin")
    vcsUrl.set("https://github.com/flumennigrum/gradle-avro-plugin.git")
    plugins {
        create("avroPlugin") {
            id = "io.github.flumennigrum.gradle.avro"
            implementationClass = "io.github.flumennigrum.gradle.avro.AvroGeneratorPlugin"
            displayName = "Gradle Avro Plugin"
            description = "A Gradle plugin that wraps Apache Avro Compiler to generate Java classes from .avsc files."
            tags.set(listOf("avro", "code-generation", "java"))
            compatibility {
                features {
                    configurationCache = true
                }
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
