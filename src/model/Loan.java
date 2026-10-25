package model;

import java.util.Calendar;
import java.util.Date;

// Represents a book loan transaction in the library
public class Loan {
    // private fields for data encapsulation
    private Book book;
    private Member member;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;

    // Constructor 1: creates a loan for an issued book with calculated due date
    public Loan(Book book, Member member) {
        // this keyword assigns parameters to object fields
        this.book = book;
        this.member = member;
        this.issueDate = new Date();
        // Calendar from java.util package is used to calculate due date
        Calendar cal = Calendar.getInstance();
        cal.setTime(issueDate);
        cal.add(Calendar.DAY_OF_MONTH, member.getLoanDays());
        this.dueDate = cal.getTime();
        this.returnDate = null;
    }

    // Constructor 2: creates a loan with all dates explicitly provided
    // constructor overloading
    public Loan(Book book, Member member, Date issueDate, Date dueDate, Date returnDate) {
        // this keyword refers to the current object's fields
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    // returns the borrowed book
    public Book getBook() {
        return book;
    }

    // returns the member who borrowed the book
    public Member getMember() {
        return member;
    }

    // returns the date the book was issued
    public Date getIssueDate() {
        return issueDate;
    }

    // returns the due date for returning the book
    public Date getDueDate() {
        return dueDate;
    }

    // returns the date the book was returned
    public Date getReturnDate() {
        return returnDate;
    }

    // sets the return date when the book is returned
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // returns true if the book has been returned
    public boolean isReturned() {
        return returnDate != null;
    }

    // method overriding of Object.toString() to display loan details
    @Override
    public String toString() {
        String status;
        if (isReturned()) {
            status = "Returned on " + returnDate;
        } else {
            status = "Due by " + dueDate;
        }
        return "Loan [Book: " + book.getTitle() + " | Member: " + member.getName() + " | Issued: " + issueDate + " | " + status + "]";
    }
}
