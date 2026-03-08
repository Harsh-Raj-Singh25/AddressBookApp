# Address Book App - UC 5: Multiple Contacts Functionality

## Overview
This Use Case expands the system's capability to handle multiple contact entries simultaneously, moving from single-entry additions to bulk data processing.

## Features Implemented
- **Bulk Addition Endpoint**: Added `/addressbook/add-multiple` to handle a JSON Array of contact objects.
- **Collection Processing**: Enhanced the Service layer to iterate through lists and persist multiple entities to the internal memory.

## Technical Details
- **Method**: `addMultipleContacts(List<Contact> contacts)`
- **Request Type**: `POST`
- **Data Format**: Accepts a JSON Array `[...]` containing multiple contact objects.

## Git Progress
- **Branch**: `feature-uc5`
- **Status**: Completed and Merged to `dev`