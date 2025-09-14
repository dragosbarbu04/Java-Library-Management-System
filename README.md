# Java Library Management System

> **Note:** This project was developed for a university object-oriented programming module. It serves as a practical demonstration of Java, the Command design pattern, and GUI development with Swing.

This is a library management system built entirely in **Java**. The application allows for the management of books, patrons, and loans through two distinct interfaces: a **command-line interface (CLI)** and a **graphical user interface (GUI)** built with Swing. The system handles data persistence by reading from and writing all data to local `.txt` files.

---

## Key Features

The application is structured using the Command design pattern, where each user action is encapsulated as a command object.

* **Dual Interface:**
    * **Command-Line Interface (CLI):** Allows users to interact with the library by typing commands directly into the console (e.g., `listbooks`, `addpatron`, `borrow`).
    * **Graphical User Interface (GUI):** Provides a user-friendly window with menus for managing books and patrons. Can be launched with the `loadgui` command from the CLI.
* **Book Management:**
    * Add new books to the library (both CLI and GUI).
    * List all books currently in the library.
* **Patron Management:**
    * Add new patrons to the library.
    * List all patrons registered in the system.
* **Loan Management System:**
    * **Borrow:** Assign a book to a patron, creating a loan with a set due date.
    * **Renew:** Extend the due date for a book that is already on loan.
    * **Return:** Mark a book as returned to the library, ending the loan.
* **Data Persistence:**
    * The system loads all book, patron, and loan data from `.txt` files on startup.
    * All changes (new books, new patrons, new loans) are saved back to the `.txt` files when the program exits.

---

## Technologies Used

* **Language:** Java
* **GUI Toolkit:** Java Swing

---

## How to Run 

There are two ways to run this application:

### 1. Command-Line Version

Compile and run the `Main.java` class. This will start the CLI in your terminal. You can then use commands like `listbooks`, `addpatron`, `borrow 1 1`, or type `help` to see all available commands. To launch the GUI from the CLI, type `loadgui`.

### 2. GUI Version (Directly)

To run the GUI directly, you can run the `MainWindow.java` class. This class contains a commented-out `main` method that can be uncommented to start the GUI application immediately.

---