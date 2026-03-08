# Address Book System - UC 8: Cross-Book Search

## Overview
>Implemented a global search capability that allows users to query contacts by **City** or **State** across the entire system, regardless of which specific Address Book they belong to.

## Implementation Logic
The system maintains a `Map<String, AddressBook>` (UC 6). Searching across all books requires traversing this map and filtering the combined contact lists.

### Key Technical Components:
* **Java Streams (flatMap)**: Used to transform the collection of `AddressBook` objects into a single, unified stream of `Contact` objects for easy filtering.
* **Case-Insensitive Filtering**: The search uses `.equalsIgnoreCase()` to ensure results are found regardless of the user's input case.
* **Collection Support**: The search returns a `List<Contact>`, allowing the system to display multiple individuals living in the same area.

## How to Test via API
1.  **Add Data**: Create at least two address books and add contacts with the same City name to both.
2.  **Execute Search**: Perform a **GET** request to the following endpoint:
    * `GET /system/search/city/{cityName}`
    * `GET /system/search/state/{stateName}`
3.  **Expected Result**: A JSON array containing all contacts matching that location from every address book in the system.

## Verification
- Search results correctly aggregate data from multiple books.
- The logic utilizes the modern Java Streams API for optimal performance and readability.