package interfaces;

import java.util.List;
import model.Book;

public interface Searchable {
  Book searchById(int bookId);
  List<Book> searchBooksByTitle(String title);
  List<Book> searchBookByAuthor(String author);
}
