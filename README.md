# Address Book App - UC 14: OpenCSV Integration

## Overview
Implemented professional-grade data persistence using the OpenCSV library. This allows for structured data interchange, making the Address Book entries compatible with external tools like Microsoft Excel.

## Features Implemented
- **Structured Storage**: Contacts are stored with clear headers and comma-separated columns.
- **Bean Mapping**: Automated the conversion between Java `Contact` objects and CSV rows using `StatefulBeanToCsv`.
- **Path Reliability**: Maintained the standard of saving to `src/main/resources` with directory auto-creation.

## API Usage
- **Write**: `POST http://localhost:8080/system/csv/write`
- **Read**: `GET http://localhost:8080/system/csv/read`