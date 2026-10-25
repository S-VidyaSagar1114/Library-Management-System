package model;

// Represents a book in the library
public class Book {
    // private fields for data encapsulation
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor 1: creates a new book with default availability as true
    public Book(int bookId, String title, String author) {
        // this keyword calls another constructor in the same class
        this(bookId, title, author, true);
    }

    // Constructor 2: creates a book with all fields specified
    // constructor overloading
    public Book(int bookId, String title, String author, boolean isAvailable) {
        // this keyword refers to the current object's fields
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    // returns the book ID
    public int getBookId() {
        return bookId;
    }

    // returns the title of the book
    public String getTitle() {
        return title;
    }

    // returns the author of the book
    public String getAuthor() {
        return author;
    }

    // returns true if the book is available for borrowing
    public boolean isAvailable() {
        return isAvailable;
    }

    // updates the availability status of the book
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // method overriding of Object.toString() to display book details
    @Override
    public String toString() {
        String status;
        if (isAvailable) {
            status = "Available";
        } else {
            status = "Not Available";
        }
        return "ID: " + bookId + " | " + title + " | Author: " + author + " | " + status;
    }
}
