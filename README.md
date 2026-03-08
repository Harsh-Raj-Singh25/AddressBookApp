# Address Book App - UC 10: Count by City or State

## Overview
>Implemented statistical reporting to retrieve the total number of contact persons categorized by their geographic location (City or State).

## Features Implemented
- **Categorized Counting**: The system provides a summary of how many contacts reside in each unique City or State.
- **Java Streams (Collectors.counting)**: Utilized the functional programming approach to aggregate and count data efficiently across the entire system.
- **Cross-Book Aggregation**: Like previous UCs, this count is system-wide, spanning all named Address Books.

## Technical Details
- **Methodology**: `Collectors.groupingBy(Field, Collectors.counting())`.
- **Endpoints**:
    - `GET /system/count/by-city`
    - `GET /system/count/by-state`