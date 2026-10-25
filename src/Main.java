import java.util.List;
import java.util.Scanner;

import model.Book;
import model.Member;
import model.StudentMember;
import model.FacultyMember;
import model.Loan;

import service.LibraryService;
import util.InputValidator;

import exception.BookNotFoundException;
import exception.MemberNotFoundException;
import exception.BookUnavailableException;
import exception.LoanNotFoundException;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InputValidator validator =
            new InputValidator(scanner);
    private static final LibraryService library = new LibraryService();

    public static void main(String[] args) {

        initializeData();

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = validator.readMenuChoice("Enter your choice: ", 1, 10);

            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> viewAllBooks();
                    case 3 -> searchBookById();
                    case 4 -> searchBooksByTitle();
                    case 5 -> searchBooksByAuthor();
                    case 6 -> registerMember();
                    case 7 -> viewAllMembers();
                    case 8 -> issueBook();
                    case 9 -> returnBook();
                    case 10 -> {
                        running = false;
                        System.out.println("Exiting Library Management System.");
                    }
                }
            } catch (BookNotFoundException
                     | MemberNotFoundException
                     | BookUnavailableException
                     | LoanNotFoundException e) {
                System.out.println("Operation failed: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Invalid operation: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by ID");
        System.out.println("4. Search Books by Title");
        System.out.println("5. Search Books by Author");
        System.out.println("6. Register Member");
        System.out.println("7. View All Members");
        System.out.println("8. Issue Book");
        System.out.println("9. Return Book");
        System.out.println("10. Exit");
    }

    private static void initializeData() {
        library.addBook(new Book(101, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(102, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(103, "Head First Java", "Kathy Sierra"));

        library.addMember(new StudentMember(1, "Rahul"));
        library.addMember(new FacultyMember(2, "Dr. Kumar"));
    }

    private static void addBook() {
        int id = validator.readPositiveInt("Enter book ID: ");
        String title = validator.readNonEmptyString("Enter book title: ");
        String author = validator.readNonEmptyString("Enter author name: ");

        library.addBook(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private static void viewAllBooks() {
        List<Book> books = library.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void searchBookById()
            throws BookNotFoundException {

        int id = validator.readPositiveInt("Enter book ID: ");
        System.out.println(library.searchBookById(id));
    }

    private static void searchBooksByTitle() {
        String title = validator.readNonEmptyString("Enter book title: ");
        List<Book> books = library.searchBooksByTitle(title);

        displaySearchResults(books);
    }

    private static void searchBooksByAuthor() {
        String author = validator.readNonEmptyString("Enter author name: ");
        List<Book> books = library.searchBooksByAuthor(author);

        displaySearchResults(books);
    }

    private static void displaySearchResults(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void registerMember() {
        System.out.println("1. Student");
        System.out.println("2. Faculty");

        int type = validator.readMenuChoice("Select member type: ", 1, 2);
        int id = validator.readPositiveInt("Enter member ID: ");
        String name = validator.readNonEmptyString("Enter member name: ");

        Member member;

        if (type == 1) {
            member = new StudentMember(id, name);
        } else {
            member = new FacultyMember(id, name);
        }

        library.addMember(member);
        System.out.println("Member registered successfully.");
    }

    private static void viewAllMembers() {
        List<Member> members = library.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static void issueBook()
            throws BookNotFoundException,
                   MemberNotFoundException,
                   BookUnavailableException {

        int bookId = validator.readPositiveInt("Enter book ID: ");
        int memberId = validator.readPositiveInt("Enter member ID: ");

        Loan loan = library.issueBook(bookId, memberId);

        System.out.println("Book issued successfully.");
        System.out.println(loan);
    }

    private static void returnBook()
            throws LoanNotFoundException {

        int loanId = validator.readPositiveInt("Enter loan ID: ");

        library.returnBook(loanId);

        System.out.println("Book returned successfully.");
    }
}

