# Address Book App - UC 16: JDBC Integration

## Overview
>Successfully migrated the data retrieval layer from local files (CSV/JSON) to a MySQL Database using JDBC (Java Database Connectivity).

## Implementation Highlights
- **Database Connectivity**: Established connection using `DriverManager` with MySQL Connector/J.
- **Data Retrieval**: Implemented SQL query execution to fetch all contact records into the Java application.
- **TDD Integration**: Followed Test-Driven Development by implementing JUnit tests to validate database counts and entry accuracy.
- **ER Model Mapping**: Ensured the database schema matches the `Contact` entity fields (First Name, Last Name, City, etc.).

## Technical Details
- **Driver**: `com.mysql.cj.jdbc.Driver`
- **Query**: `SELECT * FROM contact`