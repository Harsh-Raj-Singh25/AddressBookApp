# Address Book App - UC 7: Duplicate Entry Prevention

## Overview
 >Implemented logic to ensure that no two persons with the same name (First Name + Last Name) can exist within the same Address Book.

## Features Implemented
- **Overridden equals()**: The `Contact` model now compares equality based on `firstName` and `lastName` (case-insensitive).
- **Java Streams Integration**: Used `stream().anyMatch()` to search for existing contacts before performing an add operation.
- **Service Validation**: `ContactService` now returns a validation message if a duplicate is detected.

## Technical Details
- **Search Method**: Collection API with Java Streams.
- **Validation Criteria**: Comparison of `firstName` and `lastName`.