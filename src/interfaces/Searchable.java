package interfaces;

import java.util.List;
import model.Book;

public interface Searchable {
  Book searchBookById(int bookId);
  List<Book> searchBooksByTitle(String title);
  List<Book> searchBooksByAuthor(String author);
}
