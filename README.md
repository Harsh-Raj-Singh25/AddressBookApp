# Address Book App - UC 4: Delete Contact Functionality

## Overview
> This Use Case implements the ability to remove a contact from the Address Book system using the person's first and last name.

## Features Implemented
- **Contact Removal**: Integrated `removeIf` logic to identify and purge a specific contact from the `ArrayList`.
- **Success Feedback**: The system now provides a confirmation message if the contact was deleted or an error if the contact didn't exist.

## Technical Details
- **Method**: `deleteContact(String firstName, String lastName)`
- **Core Logic**: Used Java Streams/Lambda Predicate to filter and remove the target object.

## Git Progress
- **Branch**: `feature-uc4`
- **Status**: Completed and Merged to `dev`