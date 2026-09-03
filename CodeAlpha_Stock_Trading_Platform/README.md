# CodeAlpha Stock Trading Platform

A simple Java-based command-line stock trading simulation developed for the CodeAlpha internship program. This project demonstrates a basic trading workflow with user registration, authentication, market display, and portfolio operations backed by a MySQL database.

## Features

- User registration and login
- Display market (stocks) data
- View user portfolio (balance, stock value, transaction history)
- Buy and sell stocks (records transactions and updates user balance/stock value)
- Uses JDBC and MySQL for persistence

## Technology

- Language: Java
- Database: MySQL
- JDBC driver: mysql-connector-java-8.0.18.jar

## Project structure

CodeAlpha_Stock_Trading_Platform/
- mainApp.java             - Application entry point and CLI menu
- Users.java               - User registration and authentication
- Stocks.java              - Display market data (stocks table)
- Portfolio.java           - Buy/sell logic and portfolio viewing
- mysql-connector-java-8.0.18.jar  - MySQL JDBC driver (included)
- stock_trading_platform-0.0.1-SNAPSHOT.jar - Prebuilt application JAR (included)

## Requirements

- Java JDK 8 or newer
- MySQL server
- mysql-connector-java-8.0.18.jar (included in the project directory)

## Database setup

The application expects a MySQL database named `trading_stocks` with the following tables (example SQL to create them):

```sql
CREATE DATABASE IF NOT EXISTS trading_stocks;
USE trading_stocks;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  balance DOUBLE DEFAULT 0,
  stockvalue DOUBLE DEFAULT 0
);

CREATE TABLE stocks (
  id INT AUTO_INCREMENT PRIMARY KEY,
  symbol VARCHAR(50) NOT NULL UNIQUE,
  companyname VARCHAR(255),
  price DOUBLE NOT NULL
);

CREATE TABLE portfolio (
  id INT AUTO_INCREMENT PRIMARY KEY,
  useremail VARCHAR(255) NOT NULL,
  stocksymbol VARCHAR(50) NOT NULL,
  buyorsell VARCHAR(50),
  quantity INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample data for testing
INSERT INTO stocks (symbol, companyname, price) VALUES
('RELIANCE', 'Reliance Industries', 2550),
('TCS', 'Tata Consultancy Services', 3300),
('INFY', 'Infosys Limited', 1400);

-- Optionally create a test user
INSERT INTO users (name, email, password, balance, stockvalue) VALUES
('Test User', 'test@example.com', 'password', 100000, 0);
```

Adjust sample values and add more stocks as needed.

## Configuration

By default the database connection is configured in `mainApp.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/trading_stocks";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "Its private";
```

Update these values to match your local MySQL credentials. For production use, do not hardcode credentials—use environment variables or a configuration file.

## Build and run

You can either run the prebuilt JAR shipped with the repo, or compile from source.

1) Run prebuilt JAR (may need connector on classpath):

Linux / macOS:

```bash
# If the fat JAR does not include the MySQL connector, run:
java -cp "stock_trading_platform-0.0.1-SNAPSHOT.jar:mysql-connector-java-8.0.18.jar" mypackage.mainApp

# Or if the JAR is executable and contains dependencies:
java -jar stock_trading_platform-0.0.1-SNAPSHOT.jar
```

Windows (PowerShell/CMD):

```powershell
# Use semicolon separator on Windows
java -cp "stock_trading_platform-0.0.1-SNAPSHOT.jar;mysql-connector-java-8.0.18.jar" mypackage.mainApp
```

2) Compile and run from source:

Linux / macOS:

```bash
javac -cp "mysql-connector-java-8.0.18.jar" CodeAlpha_Stock_Trading_Platform/*.java
java -cp ".:mysql-connector-java-8.0.18.jar" mypackage.mainApp
```

Windows:

```powershell
javac -cp "mysql-connector-java-8.0.18.jar" CodeAlpha_Stock_Trading_Platform\*.java
java -cp ".;mysql-connector-java-8.0.18.jar" mypackage.mainApp
```

Note: Classpath separators differ between OSes (`:` on Unix-like, `;` on Windows). The application's package is `mypackage` so the main class is `mypackage.mainApp`.

## Usage

- Start the app and choose `Create a new account` to register (or use the test user in the sample SQL).
- Login with email and password.
- Use the menu to display market data, view portfolio, buy, and sell stocks.

## Security and improvements

This is a demonstration application and should not be used as-is in production. Suggested improvements:

- Do not store plaintext passwords; hash (e.g., bcrypt) instead.
- Remove hardcoded DB credentials and use environment variables or a config file.
- Add input validation and better error handling (currently many exceptions are swallowed).
- Track per-user holdings (aggregate `portfolio` entries or maintain a holdings table) so that selling verifies owned quantities.
- Add unit tests and logging, and consider using a connection pool (HikariCP) for production.
- Implement MVC or a proper architecture (instead of mixing JDBC logic in business classes).

## License

This repository does not include an explicit license. If you want to open-source it, consider adding an appropriate license (for example, MIT).

## Contact

For questions about this project, open an issue in the repository or contact the maintainer.
