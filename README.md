# Cashier System Project - Object-Oriented Design

> This is a fork of the original group project developed for the CS151 Object-Oriented Design course at San Jose State University.  
> *Original repository link: https://github.com/galacticmice/151cashier.git*

## Project Overview

This project is a **Java-based cashier system** designed and implemented using **Object-Oriented Design (OOD)** principles. It was built as a group assignment for our OOD class, applying concepts such as:

- **Inheritance & Encapsulation**
- **Model-View-Controller (MVC) architecture**
- **GUI design using Java AWT and Java Swing (frames, panels, layouts, components, buttons)**
- **JSON file integration** for dynamic inventory management

## Key Features

The system supports three major functional modules:

### 1. Cashier Shift & Inventory Management
- Record cashier working shifts
- Load and update inventory from a `.json` file
- Display and manage inventory (item name, code, quantity, price)
- Search inventory by **item code**

### 2. Cart & Checkout
- Add or remove items from a shopping cart
- Calculate total price including **tax** and **discount**
- Validate inventory before checkout
- Clean, user-friendly cart interface

### 3. Receipt Generation
- Generate and display formatted receipts after checkout
- Include itemized list, totals, tax, discount details, store and cashier information

## My Contributions

I was primarily responsible for the **cashier shift tracking** and **inventory management module**, which included:

- Designing and implementing the **cashier and inventory UI frame**
- Handling **inventory input/output with JSON**
- Developing logic for:
  - Reading inventory data from a JSON file
  - Displaying inventory in a table format
  - Searching by item or code
  - adding to or removing item from the cart
- Ensuring data encapsulation between cashier and customer views using **separate model layers** in the MVC architecture

## Architecture

The system uses the **Model-View-Controller (MVC)** pattern:

- **Model**: Manages inventory, cart data, and receipt generation
- **View**: Separate GUI frames for cashier and customer perspectives
- **Controller**: Handles user interactions and connects view to model (the connection between the shift and inventory, cart, and receipt frames)

This structure allowed us to isolate responsibilities, reuse logic, and maintain cleaner code.

## Technologies Used

- Java (JDK 17)
- Swing (Java GUI components)
- JSON (via `org.json` library)
- IntelliJ IDEA

### See My Commits:
You can view all my contributions to the original project here:  
[Commits by my school GitHub account](https://github.com/galacticmice/151cashier/commits/main/?author=AnanranG)


## Purpose of This Fork

This fork serves as a **portfolio showcase** of my contribution to a collaborative software engineering project. It highlights my experience in:

- Applying OOD principles in a real-world problem
- Building maintainable UI with Swing
- Handling structured data input via JSON
- Designing modular systems with MVC

---

If you’d like to see specific parts of the code related to my contribution, you can view my commits or check the relevant files in the repository.
