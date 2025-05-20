import java.util.List;

public class Main {

    public static void main(String[] args) {
        Book book1= new Book("Harry Potter","J.K Rowling",1234,"2000");
        Book book2= new Book("400 Days","Chetan Bhagat",2345,"2001");
        Book book3= new Book("Hard Things About Hard Things","Ben Horowitz",3456,"2010");
        Book book4= new Book("Gulliver's Travel","Jonathan Swift",4567,"2008");

        Patron yogesh= new PaidMember("Yogesh",1,"yogesh.bhati6734@gmail.com",9873210463L);
        Patron ram= new FreeMember("Ram",2,"ram.134@gmail.com",9873210461L);
        Patron jack= new FreeMember("Jack",3,"jack@gmail.com",9873210463L);
        Patron phill= new PaidMember("Phill",3,"phill@gmail.com",9873210464L);

        Notification notification = new EmailNotification();
        Library library =new Library(notification);


        library.addBook(book1,10);
        library.addBook(book2,5);
        library.addBook(book3,1);
        library.addBook(book4,2);

        List<Book> foundByTitle = library.searchBook("title", "Harry Potter");
        List<Book> foundByAuthor = library.searchBook("author", "Chetan Bhagat");
        List<Book> foundByISBN = library.searchBook("isbn", "4567");

        library.addPatron(yogesh);
        library.addPatron(ram);
        library.addPatron(phill);

        library.issueBooks(book1,jack);
        library.issueBooks(book3,yogesh);
        library.issueBooks(book2,yogesh);
        library.issueBooks(book1,ram);
        library.issueBooks(book4,ram);
        library.issueBooks(book3,phill);
        library.issueBooks(book2,ram);

        library.returnBooks(book3,yogesh);

        library.displayBorrowedBooks();
        library.displayAvailableBooks();
        library.getPatronBorrowingHistory(yogesh);
    }

}
