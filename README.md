# Java Property Purchase System
 
## Introduction
The Property Purchase System is a Java console application that simulates the process of buying residential properties such as houses, apartments, villas, and studios. Designed with object-oriented principles, the project demonstrates inheritance, abstraction, and polymorphism, while integrating real-world entities like buyers and contracts.

## Technologies Used
This project is built using Java 19 and managed with Maven for dependency management. It uses JDBC (Java Database Connectivity) to interact with a PostgreSQL database, allowing seamless data persistence. Development and testing were done in IntelliJ IDEA,

## Structure
The system connects to a PostgreSQL database using JDBC, enabling CRUD operations on persistent data through cleanly separated layers:

### Model Layer (model) – defines domain entities such as buildings and sellers.

- Building (abstract): Base class for all types of buildings, including shared attributes like address, surface area, price, and status.

- House, Studio, Villa, Apartment: Extend Building, each with specific attributes (e.g., ViewType for villas, floors/rooms for houses).

- ViewType, BuildingType (enums): Define property-specific enums like view types (SEA, CITY) and types of buildings.

- Seller: Represents a property seller with personal details.

- BuildingInputHelper: Utility for reading and constructing building instances based on user input.

### Repository Layer (repository) – handles data access using interfaces and their implementations.
  
- BuildingRepository, BuildingRepositoryImpl: Interface and implementation for performing CRUD operations on buildings in the database.

- SellerRepository, SellerRepositoryImpl: Interface and implementation for managing sellers in the database.

- AuditService: Logs actions performed in the app for audit/tracking purposes.

### Service Layer (service) – provides business logic for managing buildings and sellers.

- BuildingService: Core logic for managing building operations (adding, displaying, buying, etc.).

- SellerService: Handles logic related to seller management.

### Config Layer (config) – provides the database connection configuration.

The entry point of the app is in Main.java .


## Flow
The application starts by launching a singleton Menu instance that guides the user through various options in a console interface.The user can check available properties, add new properties of any type (Apartment, Studio, House, Villa), register new sellers into the system and delete properties. One of the core functionalities is the ability to purchase a property, which updates its status to SOLD directly in the PostgreSQL database. 
<p align="center">
    <img src="https://github.com/mariaxadina/ASP.NET-Core-Application-FlashMedia/blob/main/images/1.png" width="70%" />
</p>

## Conclusion
This project is a simple real estate management system built with Java 19, PostgreSQL, JDBC, and Maven. It allows users to manage different types of properties and sellers, connect to a database, and perform actions like adding, viewing, buying, and deleting properties. The code is organized into clear layers and uses a menu system to make everything easy to use.
