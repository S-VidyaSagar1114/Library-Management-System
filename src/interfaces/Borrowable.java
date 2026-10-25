package interfaces;

import model.Loan;

public interface Borrowable {
  Loan issueBook(int bookId, int memberId);
  void returnBook(int loanId);
}
