import model.Book;
import model.FacultyMember;
import model.Loan;
import model.Member;
import model.StudentMember;

// Main entry point for the Library Management System
public class Main {
    public static void main(String[] args) {
        // temporary test code for Model classes, will be replaced by the interactive menu later

        // creating 3 Book objects using constructor 1
        Book book1 = new Book(101, "Introduction to Java Programming", "Y. Daniel Liang");
        Book book2 = new Book(102, "Data Structures and Algorithms in Java", "Robert Lafore");
        Book book3 = new Book(103, "Clean Code", "Robert C. Martin");

        // creating a student member and a faculty member
        StudentMember student = new StudentMember(1, "Rahul Sharma");
        FacultyMember faculty = new FacultyMember(2, "Dr. Sunita Rao");

        // print the initial details of books and members
        System.out.println("--- Books in Library ---");
        System.out.println(book1.toString());
        System.out.println(book2.toString());
        System.out.println(book3.toString());

        System.out.println("\n--- Members Registered ---");
        System.out.println(student.toString());
        System.out.println(faculty.toString());

        // student borrows a book: update availability, add to member's list, and create a loan
        book1.setAvailable(false);
        student.addBorrowedBook(book1);
        Loan loan1 = new Loan(book1, student);

        System.out.println("\n--- Loan Created ---");
        System.out.println(loan1.toString());

        System.out.println("\n--- Updated Book and Member Status ---");
        System.out.println(book1.toString());
        System.out.println("Can student borrow more books? " + student.canBorrowMore());
        System.out.println("Books currently borrowed by student: " + student.getBorrowedBooks().size());

        // demonstrating polymorphism using an array of parent abstract class Member
        System.out.println("\n--- Demonstrating Polymorphism with Member Array ---");
        Member[] members = new Member[2];
        members[0] = student;
        members[1] = faculty;

        // plain for loop iterating through members
        for (int i = 0; i < members.length; i++) {
            Member m = members[i];
            // dynamic method dispatch calls the child class implementation at runtime
            System.out.println(m.getName() + " (" + m.getMemberType() + "): Max Books = " + m.getMaxBooks() + ", Loan Days = " + m.getLoanDays());
        }
    }
}
