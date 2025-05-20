import java.util.ArrayList;
import java.util.List;

public abstract class Patron {
    private String name;
    private int id;
    private String email;
    private long mobileNumber;
    private List<Book> books;

    public Patron(String name, int id, String email, long mobileNumber) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.books= new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public abstract int getMaxBooksAllowed();
}
