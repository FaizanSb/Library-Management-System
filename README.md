# Library-Management-System

This project is a Library Management System implemented in Java. It uses a linked list from scratch to manage the various functionalities of the system.

## Features
- Add a book to the library
- Remove a book from the library
- Allocate a book to a borrower
- Return a book from a borrower
- Check the number of available books
- Calculate fines for overdue books

## Linked List Implementation
The linked list is implemented from scratch to manage the collection of books in the library. Each node in the linked list represents a book, with details such as the title, author, and status (available or allocated).

## Classes and Methods

### Book
- `String title`
- `String author`
- `boolean isAvailable`

### Node
- `Book book`
- `Node next`

### Library
- `Node head`

#### Methods
- `void addBook(String title, String author)`: Adds a new book to the library.
- `void removeBook(String title)`: Removes a book from the library.
- `boolean allocateBook(String title)`: Allocates a book to a borrower.
- `boolean returnBook(String title)`: Returns a book from a borrower.
- `int countAvailableBooks()`: Returns the number of available books in the library.
- `double calculateFine(String title, int overdueDays)`: Calculates the fine for an overdue book.

## Usage
1. Clone the repository:
2. Navigate to the project directory:
3. Compile and run the project:

## Example
```java
Library library = new Library();
library.addBook("The Great Gatsby", "F. Scott Fitzgerald");
library.addBook("1984", "George Orwell");

library.allocateBook("1984");

System.out.println("Available books: " + library.countAvailableBooks());

library.returnBook("1984");

System.out.println("Available books: " + library.countAvailableBooks());

double fine = library.calculateFine("1984", 5);
System.out.println("Fine for 1984: " + fine);
