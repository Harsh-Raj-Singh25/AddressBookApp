# Address Book App - UC 3: Edit Contact Functionality

## Overview
This Use Case adds the ability to search for an existing contact by their full name and update their contact information (Address, City, State, etc.).

## Features Implemented
- **Contact Search**: Implemented logic to iterate through the `ArrayList` and locate a specific contact using `firstName` and `lastName`.
- **Data Update**: Leveraged setter methods to modify the existing object's state in memory.
- **Case-Insensitive Matching**: Used `equalsIgnoreCase` to ensure user input matches regardless of capitalization.

## Technical Details
- **Method**: `editContact(String firstName, String lastName, Contact updatedData)`
- **Logic**: Iterative search followed by field-level updates.

## Git Progress
- **Branch**: `feature-uc3`
- **Status**: Completed and Merged to `dev`