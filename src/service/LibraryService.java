package service;

import model.Book;
import model.Member;
import model.Loan;

import interfaces.Searchable;
import interfaces.Borrowable;

import exception.BookNotFoundException;
import exception.MemberNotFoundException;
import exception.BookUnavailableException;
import exception.LoanNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class LibraryService implements Searchable, Borrowable {


private final List<Book> books = new ArrayList<>();
private final List<Member> members = new ArrayList<>();
private final List<Loan> loans = new ArrayList<>();

private int nextLoanId = 1;

public void addBook(Book book) {
    if (book == null) {
        throw new IllegalArgumentException("Book cannot be null.");
    }

    for (Book existingBook : books) {
        if (existingBook.getBookId() == book.getBookId()) {
            throw new IllegalArgumentException(
                    "Book ID already exists: " + book.getBookId());
        }
    }

    books.add(book);
}

public List<Book> getAllBooks() {
    return new ArrayList<>(books);
}

@Override
public Book searchBookById(int bookId)
        throws BookNotFoundException {

    for (Book book : books) {
        if (book.getBookId() == bookId) {
            return book;
        }
    }

    throw new BookNotFoundException(
            "Book not found with ID: " + bookId);
}

@Override
public List<Book> searchBooksByTitle(String title) {
    List<Book> results = new ArrayList<>();

    if (title == null || title.trim().isEmpty()) {
        return results;
    }

    String searchTitle = title.trim().toLowerCase();

    for (Book book : books) {
        if (book.getTitle() != null
                && book.getTitle().toLowerCase().contains(searchTitle)) {
            results.add(book);
        }
    }

    return results;
}

@Override
public List<Book> searchBooksByAuthor(String author) {
    List<Book> results = new ArrayList<>();

    if (author == null || author.trim().isEmpty()) {
        return results;
    }

    String searchAuthor = author.trim().toLowerCase();

    for (Book book : books) {
        if (book.getAuthor() != null
                && book.getAuthor().toLowerCase().contains(searchAuthor)) {
            results.add(book);
        }
    }

    return results;
}

public void addMember(Member member) {
    if (member == null) {
        throw new IllegalArgumentException("Member cannot be null.");
    }

    for (Member existingMember : members) {
        if (existingMember.getMemberId() == member.getMemberId()) {
            throw new IllegalArgumentException(
                    "Member ID already exists: " + member.getMemberId());
        }
    }

    members.add(member);
}

public List<Member> getAllMembers() {
    return new ArrayList<>(members);
}

private Member findMemberById(int memberId)
        throws MemberNotFoundException {

    for (Member member : members) {
        if (member.getMemberId() == memberId) {
            return member;
        }
    }

    throw new MemberNotFoundException(
            "Member not found with ID: " + memberId);
}

@Override
public Loan issueBook(int bookId, int memberId)
        throws BookNotFoundException,
               MemberNotFoundException,
               BookUnavailableException {

    Book book = searchBookById(bookId);
    Member member = findMemberById(memberId);

    if (!book.isAvailable()) {
        throw new BookUnavailableException(
                "Book is currently unavailable: " + book.getTitle());
    }

    if (!member.canBorrowMore()) {
        throw new BookUnavailableException(
                member.getMemberType()
                        + " cannot borrow more books. Limit: "
                        + member.getMaxBooks());
    }

    Loan loan = new Loan(book, member);
    loan.setLoanId(nextLoanId++);

    book.setAvailable(false);
    member.addBorrowedBook(book);
    loans.add(loan);

    return loan;
}

@Override
public void returnBook(int loanId) throws LoanNotFoundException {
    Loan targetLoan = null;

    for (Loan loan : loans) {
        if (loan.getLoanId() == loanId) {
            targetLoan = loan;
            break;
        }
    }

    if (targetLoan == null) {
        throw new LoanNotFoundException(
                "Loan not found with ID: " + loanId);
    }

    if (targetLoan.isReturned()) {
        throw new IllegalStateException(
                "This loan has already been returned.");
    }

    targetLoan.setReturnDate(new java.util.Date());
    targetLoan.getBook().setAvailable(true);
    targetLoan.getMember().removeBorrowedBook(targetLoan.getBook());
}

public List<Loan> getAllLoans() {
    return new ArrayList<>(loans);
}

public List<Loan> getActiveLoans() {
    List<Loan> activeLoans = new ArrayList<>();

    for (Loan loan : loans) {
        if (!loan.isReturned()) {
            activeLoans.add(loan);
        }
    }

    return activeLoans;
}

public Loan getLoanById(int loanId) throws LoanNotFoundException {
    for (Loan loan : loans) {
        if (loan.getLoanId() == loanId) {
            return loan;
        }
    }

    throw new LoanNotFoundException(
            "Loan not found with ID: " + loanId);
}


}
