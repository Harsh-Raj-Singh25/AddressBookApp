# Address Book App - UC 20: Transactional DB Insertion

## Overview
>Implemented the capability to persist new contacts into the MySQL database while ensuring data integrity through formal transaction management.

## Features Implemented
- **JDBC Persistence**: Added logic to perform SQL `INSERT` operations using `PreparedStatement`.
- **Transaction Management**: Configured manual `commit()` and `rollback()` logic to ensure that if multiple tables were ever impacted, the database remains in a consistent state.
- **Atomic Operations**: Used `setAutoCommit(false)` to wrap the insertion process, adhering to ACID properties.

## Technical Highlights
- **Transaction Control**: Implementation ensures that partial data is never saved if an error occurs during the insertion process.
- **Date Handling**: Automatically assigns the current system date to the `date_added` field during insertion.