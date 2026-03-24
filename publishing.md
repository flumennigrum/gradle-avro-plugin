# Publishing to the Gradle Plugin Portal

To publish `io.github.flumennigrum.gradle.avro` to the Gradle Plugin Portal, follow these steps:

## 1. Create an Account
If you haven't already, create an account on the [Gradle Plugin Portal](https://plugins.gradle.org/).

## 2. Obtain API Keys
Once logged in, go to your profile and click on **API Keys**. You will find your:
- **API Key**
- **API Secret**

## 3. Configure Credentials
Add these keys to your user-specific `gradle.properties` file (usually located at `~/.gradle/gradle.properties`) to keep them private and avoid committing them to version control.

```properties
gradle.publish.key=<your-api-key>
gradle.publish.secret=<your-api-secret>
```

## 4. Run the Publication Task
Once your credentials are set up, you can publish the plugin by running the following command from the project root:

```bash
./gradlew publishPlugins
```

## 5. Verification
After the task completes successfully, your plugin will be submitted for review. You can track its status on the Gradle Plugin Portal. Once approved, it will be available for everyone to use!

> [!TIP]
> You can perform a dry run to check for configuration errors without actually publishing:
> ```bash
> ./gradlew publishPlugins --dry-run
> ```
