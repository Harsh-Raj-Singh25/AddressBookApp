# Address Book App - UC 2: Service Layer & List Management

## Overview
> In this Use Case, we moved beyond creating a single object to building a functional system capable of adding and storing multiple contacts. We introduced the **Service Layer** to handle business logic and used the **Spring Application Context** to manage object lifecycles.

## Features Implemented
- **Service Layer Architecture**: Created `AddressBookService` to decouple data management from the main application logic.
- **In-Memory Storage**: Implemented a `List<Contact>` (ArrayList) to maintain multiple contact entries during the application runtime.
- **Dependency Injection**: Used Spring's `ApplicationContext` to retrieve the Service bean, demonstrating the **Inversion of Control (IoC)** principle.
- **Contact Creation Logic**: Added functionality to instantiate new contacts and persist them into the collection.

## Technical Details
- **Annotations**: 
  - `@Service`: Marks the class as a Spring-managed service.
  - `@SpringBootApplication`: Used to bootstrap the application and perform component scanning.
- **Collections**: Utilized `java.util.ArrayList` for dynamic data storage.
- **Design Pattern**: Implemented the **Singleton Pattern** (via Spring) ensuring a single instance of the address book exists throughout the app.

## How to Run
1. Run the `AddressBookMain.java` as a Java Application.
2. The console will display the "Welcome to Address Book" message.
3. The program will automatically add a sample contact to the service and print the updated list to the console.

## Test Cases Covered
- **testAddContactToList**: 
  - Verifies that adding a contact increases the list size.
  - Ensures the data retrieved from the list matches the data that was input.

## Git Progress
- **Branch**: `feature-uc2`
- **Status**: Completed and Merged to `dev`