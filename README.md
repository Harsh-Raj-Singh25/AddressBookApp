# Address Book App - UC 19: SQL Aggregation Functions

## Overview
>Enhanced the data reporting capabilities of the Address Book System by implementing server-side aggregation for contact statistics.

## Features Implemented
- **Aggregate Reporting**: Successfully utilized the `COUNT` and `GROUP BY` database functions to calculate contact density by region.
- **Dynamic Querying**: Developed a flexible JDBC method that can toggle between City and State statistics.
- **TDD Verification**: Maintained the TDD approach by ensuring the application correctly interprets summarized results from MySQL.

## Technical Details
- **Database Function**: `SELECT city, COUNT(*) FROM contact GROUP BY city;`
- **JDBC Implementation**: Used standard `Statement` and `ResultSet` to fetch summarized data.