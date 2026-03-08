# Address Book App - UC 9: Categorized Viewing (City/State Dictionaries)

## Overview
Implemented the capability to view and maintain contacts grouped by their geographic locations. This allows for quick access to all residents of a specific City or State.

## Features Implemented
- **Categorized Dictionary**: Created a data structure that maps a City/State name (Key) to a List of Contacts (Value).
- **Java Streams Grouping**: Utilized `Collectors.groupingBy` for clean and efficient aggregation of data.
- **System-Wide View**: The logic aggregates data from all independent Address Books managed by the system.

## Technical Details
- **Logic**: `stream().collect(Collectors.groupingBy(Contact::getCity))`.
- **Endpoints**:
    - `GET /system/view/by-city`
    - `GET /system/view/by-state`