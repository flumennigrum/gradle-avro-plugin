# Contributing to Gradle Avro Plugin

First off, thank you for considering contributing to the Gradle Avro Plugin! It's people like you who make this project better for everyone.

This document provides guidelines for contributing to the project. These are mostly guidelines, not rules. Use your best judgment and feel free to propose changes to this document in a pull request.

## How Can I Contribute?

### Reporting Bugs

This section guides you through submitting a bug report. Following these guidelines helps maintainers and the community understand your report, reproduce the behavior, and find related reports.

Before creating bug reports, please **check existing issues** as you might find that you are not the first person to experience the problem.

When you are creating a bug report, please include as many details as possible:

*   **Use a clear and descriptive title** for the issue to identify the problem.
*   **Describe the exact steps which reproduce the problem** in as many details as possible.
*   **Explain which behavior you expected to see and why**, and what you actually saw instead.
*   **Include your environment details**: Gradle version, Java version, and OS.
*   **Provide a minimal reproducible example**, if possible (e.g., a small `.avsc` file and your `build.gradle.kts` configuration).

### Suggesting Enhancements

This section guides you through submitting an enhancement suggestion, including completely new features and minor improvements to existing functionality.

Before creating enhancement suggestions, please **check existing issues** to see if the enhancement has already been suggested.

When creating an enhancement suggestion, please:

*   **Use a clear and descriptive title** for the issue to identify the suggestion.
*   **Provide a step-by-step description of the suggested enhancement** in as many details as possible.
*   **Explain why this enhancement would be useful** to most users.

## Development Setup

To build and test the plugin locally, you will need:

*   **JDK 11** or higher.
*   A clone of this repository.

### Build the Project

You can build the project using the Gradle wrapper:

```bash
./gradlew build
```

### Run Tests

To run the unit tests:

```bash
./gradlew test
```

## Pull Request Process

1.  **Fork the repository** and create your branch from `main`.
2.  **Ensure any install or build dependencies are removed** before the end of the layer when doing a build.
3.  **Update the documentation** if you're making changes to the UI or API.
4.  **Add tests** for any new functionality or bug fixes.
5.  **Ensure all tests pass** locally before submitting your PR.
6.  **Use clear and concise commit messages**.
7.  Submit your Pull Request with a clear description of the changes and the problem they solve.

Once your PR is submitted, it will be reviewed by the maintainers. We might suggest some changes or improvements before merging.

Thank you for your contribution!
