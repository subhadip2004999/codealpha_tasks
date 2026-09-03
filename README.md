# codealpha_tasks

Internship program at CodeAlpha. Java programming projects.

## Projects

- CodeAlpha_Stock_Trading_Platform/ - A Java project implementing a basic stock trading platform. See: https://github.com/subhadip2004999/codealpha_tasks/tree/main/CodeAlpha_Stock_Trading_Platform
- CodeAlpha_Student_Grade_Tracker/ - Student grade tracker project (see its own README)

## About

This repository contains Java projects developed for the CodeAlpha internship program. The projects demonstrate core Java concepts, basic system design, and simple application architectures (CLI, Swing/JavaFX, or Spring Boot depending on the project).

## Requirements

- Java 11 or newer (OpenJDK or Oracle JDK)
- Maven or Gradle (if the project uses a build tool)

## Build & Run (general)

1. Inspect the project folder (for example, `CodeAlpha_Stock_Trading_Platform`) to see whether it uses Maven (`pom.xml`) or Gradle (`build.gradle` / `build.gradle.kts`).

2. If it uses Maven:

   ```bash
   cd CodeAlpha_Stock_Trading_Platform
   mvn clean package
   # Then run the generated jar (replace with actual jar name):
   java -jar target/your-app.jar
   ```

3. If it uses Gradle:

   ```bash
   cd CodeAlpha_Stock_Trading_Platform
   ./gradlew build
   # Then run the generated jar (replace with actual jar name):
   java -jar build/libs/your-app.jar
   ```

4. If there is no build tool and it's a simple Java project with a `src` directory:

   ```bash
   cd CodeAlpha_Stock_Trading_Platform
   javac -d out $(find src -name "*.java")
   java -cp out com.example.MainClass
   ```

Replace `com.example.MainClass` with the actual main class. If you are unsure which main class to run, search the project for `public static void main(String[] args)`.

## Running Tests

If the project includes tests:

- Maven: `mvn test`
- Gradle: `./gradlew test`

## Contributing

1. Fork the repository and create a feature branch.
2. Add code or tests, and ensure they build and pass locally.
3. Open a pull request with a clear description of changes.

Please follow Java best practices and include unit tests for new functionality.

## License

If you want a license added, I can add an MIT or Apache-2.0 LICENSE file for you.

## Maintainer

@subhadip2004999
