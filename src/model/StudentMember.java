package model;

// Represents a student library member, inherits from Member
public class StudentMember extends Member {

    // constructor takes student member details and calls parent constructor
    public StudentMember(int memberId, String name) {
        // super keyword invokes the constructor of the parent class Member
        super(memberId, name);
    }

    // method overriding to specify student book borrowing limit
    @Override
    public int getMaxBooks() {
        return 3;
    }

    // method overriding to specify student loan period in days
    @Override
    public int getLoanDays() {
        return 14;
    }

    // method overriding to return the member type
    @Override
    public String getMemberType() {
        return "Student";
    }
}
