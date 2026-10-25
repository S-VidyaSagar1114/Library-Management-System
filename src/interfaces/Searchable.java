package interfaces;

import java.util.List;
import model.Book;
import exception.BookNotFoundException;

public interface Searchable {
  Book searchBookById(int bookId) throws BookNotFoundException;
  List<Book> searchBooksByTitle(String title);
  List<Book> searchBooksByAuthor(String author);
}
