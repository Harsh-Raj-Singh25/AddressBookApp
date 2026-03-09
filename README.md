# Address Book App - UC 12: Field-Based Sorting

## Overview
>Implemented advanced sorting capabilities to organize address book entries by geographic location (City, State, or Zip).

## Features Implemented
- **Multi-Field Sorting**: Added functions to sort entries dynamically based on user-requested fields.
- **Stream API Integration**: Utilized `Comparator.comparing()` and `stream().sorted()` for clean, functional sorting logic.
- **Dictionary Management**: Integrated with the existing Address Book Dictionary system (UC 6) to sort entries within specific books.

## Technical Details
- **Logic**: `stream().sorted(Comparator.comparing(Contact::getCity))`.
- **API Endpoint**: `GET /contacts/{bookName}/sort/{field}`