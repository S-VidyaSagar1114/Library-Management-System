package interfaces;

import model.Loan;
import exception.BookNotFoundException;
import exception.MemberNotFoundException;
import exception.BookUnavailableException;
import exception.LoanNotFoundException;
public interface Borrowable {
  Loan issueBook(int bookId, int memberId) throws BookNotFoundException,
                                                  MemberNotFoundException,
                                                  BookUnavailableException;
  void returnBook(int loanId) throws LoanNotFoundException;
}
