# Address Book App - UC 18: Date-Based Retrieval

## Overview
>Enhanced the system to allow users to filter and retrieve contact information based on the date they were added to the database.

## Features Implemented
- **Schema Evolution**: Introduced the `date_added` field to the database table to support temporal tracking.
- **Range Querying**: Implemented JDBC logic using `PreparedStatement` to execute SQL `BETWEEN` queries safely.
- **Data Integrity**: Maintained the TDD approach by validating that filtered results match exactly what is stored in the DB for a specific period.

## Technical Logic
- **SQL Component**: `SELECT * FROM contact WHERE date_added BETWEEN ? AND ?`.
- **Java Component**: Utilized `java.sql.Date` or String-formatted dates for parameter passing via JDBC.