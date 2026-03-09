# Address Book App - UC 15: GSON Persistence

## Overview
>Implemented a modern data persistence layer using JSON (JavaScript Object Notation). This allows the entire system state, including multiple address books, to be saved and reloaded efficiently.

## Implementation Details
- **Library**: Integrated Google's **GSON** for high-performance JSON processing.
- **Formatting**: Enabled `setPrettyPrinting()` to ensure the generated JSON is human-readable for debugging.
- **Data Integrity**: The system serializes the entire `Map<String, AddressBook>`, ensuring that book names and their respective contact lists are perfectly preserved.

## API Endpoints
- **Save to JSON**: `POST http://localhost:8080/system/json/write`
- **Load from JSON**: `GET http://localhost:8080/system/json/read`