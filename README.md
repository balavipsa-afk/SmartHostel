# SmartHostel - Java Hostel Management System

## Overview

SmartHostel is a simple hostel management application made using Java.

I built this project as a way to put the Java concepts we learn in class into one actual application instead of using them only in separate lab programs. The system covers some of the basic things involved in hostel management, like keeping track of students, rooms, payments and complaints.

The project is completely based on Core Java. I have used Java Swing for the interface and Java's built-in features for storing and handling the data, so there is no MySQL, Maven or any external framework involved.

## Features

- Add and manage student details
- Manage hostel rooms
- Check room availability
- Allocate rooms to students
- Record and manage payments
- Register and process complaints
- Store data using files
- Validate user input
- Handle errors using custom exceptions
- Use multiple threads for complaint processing
- Use Java Collections to manage records
- Simple desktop interface using Java Swing
- Basic hostel management operations

## Technologies Used

- Java
- Java Swing
- Java Collections
- Java I/O
- Multithreading
- Exception Handling
- Object-Oriented Programming

The project does not require a separate database or any external Java framework.

## Java Concepts Used

One of the main reasons for making this project was to understand how different Java concepts can work together in a single application.

### OOP

The project uses:

- Classes and Objects
- Constructors
- Encapsulation
- Inheritance
- Method Overloading
- Method Overriding
- Runtime Polymorphism
- Abstract Classes
- Interfaces
- `this` keyword
- `super` keyword
- `final` keyword

### Exception Handling

Custom exceptions are used to handle situations where something goes wrong instead of letting the program stop unexpectedly.

For example:

- Trying to allocate an unavailable room
- Entering invalid payment details
- Invalid input

### Collections

Java Collections are used to keep track of the different records in the system, such as:

- Students
- Rooms
- Payments
- Complaints

This also makes it easier to add, remove and search through records.

### Multithreading

Multithreading is used in the complaint processing part of the application.

This was also included to get practical experience with creating and working with threads in Java.

### File Handling

Since the project does not use MySQL, Java file handling is used to save and retrieve the required information.

This keeps the project simple and makes it possible to run it without setting up a database.

### GUI

The application uses Java Swing for the desktop interface.

The GUI provides a simple way to interact with the different hostel management features without having to use the command line for everything.

## Project Structure

```text
SmartHostel-Java-Management-System/
│
├── SmartHostel.java    # Complete Java application
├── README.md           # Project documentation
└── statement.md        # Problem statement
