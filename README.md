# Address Book App - UC 11: Alphabetical Sorting of Contacts by Name

## Overview
Implemented alphabetical sorting of address book entries based on the Person's name to improve data organization and readability.

## Features Implemented
- **Alphabetical Sorting**: Entries are sorted primarily by `firstName` and secondarily by `lastName`.
- **Java Streams & Collection Library**: Leveraged `stream().sorted()` for clean and efficient sorting logic.
- **Custom Console Output**: Overrode the `toString()` method in the `Contact` model to provide a standardized format when printing entries to the console.

## Technical Details
- **Comparator**: `Comparator.comparing(Contact::getFirstName)`
- **Output Method**: Overridden `toString()` for console logging.
- **Endpoint**: `GET /contacts/{bookName}/sorted`

## Validation for UC 11
- **Unit Testing**: Implemented JUnit tests to verify alphabetical order (A-Z) of contacts retrieved from the service.
- **Manual Verification**: Use the `/contacts/{bookName}/sorted` endpoint to view the JSON output or check the console logs to see the formatted `toString()` output.