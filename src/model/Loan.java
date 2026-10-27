package model;

import java.util.Calendar;
import java.util.Date;

// Represents a book loan transaction in the library
public class Loan {


    private int loanId;
    private Book book;
    private Member member;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;

// Constructor 1: creates a new loan
    public Loan(Book book, Member member) {
        this(0, book, member, new Date(), null, null);

    Calendar cal = Calendar.getInstance();
    cal.setTime(this.issueDate);
    cal.add(Calendar.DAY_OF_MONTH, member.getLoanDays());
    this.dueDate = cal.getTime();
}

// Constructor 2: creates a loan with explicitly provided dates
public Loan(Book book, Member member, Date issueDate,
            Date dueDate, Date returnDate) {
    this(0, book, member, issueDate, dueDate, returnDate);
}

// Constructor 3: creates a loan with an explicit loan ID
public Loan(int loanId, Book book, Member member, Date issueDate,
            Date dueDate, Date returnDate) {
    this.loanId = loanId;
    this.book = book;
    this.member = member;
    this.issueDate = issueDate;
    this.dueDate = dueDate;
    this.returnDate = returnDate;
}

public int getLoanId() {
    return loanId;
}

public void setLoanId(int loanId) {
    this.loanId = loanId;
}

public Book getBook() {
    return book;
}

public Member getMember() {
    return member;
}

public Date getIssueDate() {
    return issueDate;
}

public Date getDueDate() {
    return dueDate;
}

public Date getReturnDate() {
    return returnDate;
}

public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
}

public boolean isReturned() {
    return returnDate != null;
}

@Override
public String toString() {
  
  String status;

    if (isReturned()) {
        status = "Returned on " + returnDate;
    } else {
        status = "Due by " + dueDate;
    }

    return "Loan ID: " + loanId
            + " | Book: " + book.getTitle()
            + " | Member: " + member.getName()
            + " | Issued: " + issueDate
            + " | " + status;
}


}
