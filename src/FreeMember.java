public class FreeMember extends Patron{
    static final int BORROW_LIMIT=2;
    public FreeMember(String name, int id, String email, long mobileNumber) {
        super(name, id, email, mobileNumber);
    }

    public int getMaxBooksAllowed(){
        return BORROW_LIMIT;
    }
}
