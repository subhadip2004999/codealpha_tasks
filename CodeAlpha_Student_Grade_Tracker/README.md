# CodeAlpha Student Grade Tracker

A simple Java Spring Boot application for tracking student grades. This project was created as part of the CodeAlpha internship program and demonstrates a basic CRUD application structure for managing student records and their grades.

## Features

- Create, read, update, and delete student records
- Store and calculate grades for students
- Simple REST API endpoints (Spring Boot)

## Technologies

- Java
- Spring Boot
- Maven (or Gradle if configured)

## Project Structure

- src/main/java - application source code
- src/test/java - tests
- target or build output (JAR)

## Getting Started

Prerequisites:
- Java 11+ (or the version used by the project)
- Maven (if the project uses Maven)

Run locally:

1. Build the project:

   mvn clean package

2. Run the application:

   java -jar target/student_grade_tracker-0.0.1-SNAPSHOT.jar

3. The API should be available at http://localhost:8080 (default Spring Boot port)

Note: Adjust commands if the repository uses Gradle or a different build setup.

## Tests

Run the test suite with:

    mvn test

## Notes

- The repository has a compiled JAR and some compiled class files checked in. Consider removing build artifacts and adding a proper .gitignore to keep the repo source-only.
- There's an incorrectly named README file `README>md` — this README replaces that with a properly named `README.md`.

## License

Include a license as appropriate (e.g., MIT). Replace this section with a specific license if desired.

## Author

Created by CodeAlpha internship contributor.
