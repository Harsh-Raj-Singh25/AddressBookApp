# Address Book App - UC 13: File I/O Persistence

## Overview
>Implemented data persistence using Java File I/O. This ensures that the contacts and address books created within the system can be stored in a physical `.txt` file.

## Features Implemented
- **Data Export**: Iterates through the Address Book Dictionary and writes every contact's details into a text file using `BufferedWriter`.
- **Data Import**: Uses `Files.readAllLines()` to retrieve the stored data back into the application.
- **Resource Management**: Utilized **try-with-resources** to ensure that file streams are closed automatically, preventing memory leaks.

## Technical Details
- **File Format**: Plain Text (Standardized via `Contact.toString()`).
- **Storage Path**: Project root directory (`addressbook_data.txt`).
- **Endpoints**:
    - `POST /system/io/write`: Saves current state to disk.
    - `GET /system/io/read`: Displays saved file content.