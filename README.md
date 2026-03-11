# Address Book App - UC 17: Database Synchronization

## Overview
>Implemented real-time synchronization between the application memory and the MySQL database during update operations.

## Key Features
- **Secure Updates**: Utilized `PreparedStatement` to perform CRUD operations, preventing SQL injection.
- **Memory Sync**: Ensured that once the database is updated, the internal `List<Contact>` is immediately refreshed to match the DB state.
- **Validation**: Implemented `equals()` in the Contact entity to facilitate JUnit testing for data consistency.

## Technical Logic
- **SQL**: `UPDATE contact SET city = ? WHERE first_name = ?`.
- **Verification**: `assertEquals()` checks that the retrieved DB entity matches the expected local state.