package model;

import java.util.ArrayList;

// Abstract class representing a general library member
public abstract class Member {
    // private fields for data encapsulation
    private int memberId;
    private String name;
    // ArrayList with generics to store borrowed books
    private ArrayList<Book> borrowedBooks;

    // constructor to initialize member details
    public Member(int memberId, String name) {
        // this keyword refers to the current object's fields
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<Book>();
    }

    // returns the member ID
    public int getMemberId() {
        return memberId;
    }

    // returns the member's name
    public String getName() {
        return name;
    }

    // returns the list of borrowed books
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // abstract method, child classes must define the maximum books allowed
    public abstract int getMaxBooks();

    // abstract method, child classes must define the loan period in days
    public abstract int getLoanDays();

    // abstract method, child classes must define the member type name
    public abstract String getMemberType();

    // adds a book to the borrowed books list
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    // removes a book from the borrowed books list
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    // checks if the member can borrow more books
    public boolean canBorrowMore() {
        return borrowedBooks.size() < getMaxBooks();
    }

    // method overriding of Object.toString() to show member info
    @Override
    public String toString() {
        return "Member ID: " + memberId + " | Name: " + name + " | Type: " + getMemberType();
    }
}
