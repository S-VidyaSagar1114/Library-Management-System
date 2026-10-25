package model;

// Represents a faculty library member, inherits from Member
public class FacultyMember extends Member {

    // constructor takes faculty member details and calls parent constructor
    public FacultyMember(int memberId, String name) {
        // super keyword invokes the constructor of the parent class Member
        super(memberId, name);
    }

    // method overriding to specify faculty book borrowing limit
    @Override
    public int getMaxBooks() {
        return 10;
    }

    // method overriding to specify faculty loan period in days
    @Override
    public int getLoanDays() {
        return 30;
    }

    // method overriding to return the member type
    @Override
    public String getMemberType() {
        return "Faculty";
    }
}
