# Address Book App - UC 1: Contact Model & Unit Testing

## Overview
> This is the foundational stage of the Address Book Application. In this Use Case, we established the core data structure for a Contact and verified its integrity through automated unit testing.

## Features Implemented
- **Contact Model**: Created a robust `Contact` class with the following attributes:
  - First Name, Last Name
  - Address, City, State, Zip
  - Phone Number
  - Email
- **Object-Oriented Concepts**: 
  - Overrode `equals()` to compare contacts based on Name (identifying duplicates).
  - Overrode `toString()` for clean console logging.
  - Custom Constructor for data initialization.
- **Lombok Integration**: Used `@Data` to handle boilerplate code like Getters and Setters.

## Technical Details
- **Framework**: Spring Boot 3.x
- **Build Tool**: Maven
- **Testing**: JUnit 5
- **Library**: Project Lombok

## How to Run Tests
To verify the implementation, run the following command in your terminal:
```bash
mvn test