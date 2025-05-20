public class PaidMember extends Patron{
    static final int BORROW_LIMIT=50;
    public PaidMember(String name, int id, String email, long mobileNumber) {
        super(name, id, email, mobileNumber);

    }

    public int getMaxBooksAllowed(){
        return BORROW_LIMIT;
    }

}
