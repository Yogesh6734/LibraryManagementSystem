import java.util.*;

public class Library {

    private Map<Book,Integer> booksList;
    private List<Patron> patronList;
    private Map<Patron,List<Book>> patronBorrowedHistory;
    private Map<Book,Queue<Patron>> notificationQueue;
    private Notification notification;


    public Library(Notification notification) {
        this.booksList = new HashMap<>();
        this.patronList = new ArrayList<>();
        patronBorrowedHistory=new HashMap<>();
        this.notificationQueue= new HashMap<>();
        this.notification=notification;
    }

    public void addBook(Book book, int numberOfBooks){
        booksList.put(book,numberOfBooks);
    }

    public void removeBook(Book book){
        booksList.remove(book);
    }

    public void updateBookAuthor(Book book, String author){
        book.setAuthor(author);
    }

    public void updateBookTitle(Book book, String Title){
        book.setTitle(Title);
    }

    public void updateBookISBN(Book book, long ISBN){
        book.setISBN(ISBN);
    }

    public void updateBookPubYear(Book book, String publicationYear){
        book.setPublicationYear(publicationYear);
    }

    public void addPatron(Patron patron){
        patronList.add(patron);
    }

    public void updatePatronName(Patron patron, String name){
        patron.setName(name);
    }

    public void updatePatronId(Patron patron, int ID){
        patron.setId(ID);
    }

    public void issueBooks(Book book, Patron patron){
        if(!patronList.contains(patron)){
            System.out.println("Please add member before issuing book");
            return;
        }
            if(isBookAvailable(book)){
                List<Book> booksIssuedByPatron =new ArrayList<>(patron.getBooks());
                int maxBooksAllowed=patron.getMaxBooksAllowed();
                if (booksIssuedByPatron.size() == maxBooksAllowed){
                    System.out.println("You can issue more than "+ maxBooksAllowed +" books");
                    return;
                }
                booksList.put(book,booksList.getOrDefault(book,0)-1);
                booksIssuedByPatron.add(book);

                patron.setBooks(booksIssuedByPatron);

                if(!patronBorrowedHistory.containsKey(patron)){
                    patronBorrowedHistory.put(patron,booksIssuedByPatron);
                }else{
                    List<Book> booksIssuedByPatronInPast = patronBorrowedHistory.get(patron);
                    booksIssuedByPatronInPast.add(book);
                }
            }else{
                if(!notificationQueue.containsKey(book)){
                    notificationQueue.put(book,new LinkedList<>());
                }
                Queue<Patron> waitingList = notificationQueue.get(book);
                if (!waitingList.contains(patron)) {
                    waitingList.add(patron);
                    System.out.println("Book is not available. " + patron.getName() + " added to the waiting list for " + book.getTitle());
                } else {
                    System.out.println(patron.getName() + " is already in the waiting list for " + book.getTitle());
                }
            }
    }

    public boolean isBookAvailable(Book book){
       return booksList.get(book) > 0;
    }

    public void returnBooks(Book book, Patron patron){
            booksList.put(book,booksList.getOrDefault(book,0)+1);
            List<Book> booksIssuedByPatron = patron.getBooks();
            booksIssuedByPatron.remove(book);
            patron.setBooks(booksIssuedByPatron);

        if (notificationQueue.containsKey(book)) {
            Queue<Patron> waitingList = notificationQueue.get(book);

            while (!waitingList.isEmpty()) {
                Patron nextPatron = waitingList.poll();
                String message = "Hi "+ nextPatron.getName() +", "+  book.getTitle() + " is now available.";
                notifyPatron(message,nextPatron);

            }
            notificationQueue.remove(book);
        }
    }

    public void displayAvailableBooks(){
        for(Book book:booksList.keySet()){
            int availableBooks= booksList.get(book);
            switch (availableBooks) {
                case 0:
                    System.out.println(book.getTitle()+" is not available");
                    break;
                case 1:
                    System.out.println(book.getTitle() + ": " + availableBooks + " book is available");
                    break;
                default:
                    System.out.println(book.getTitle() + ": " + availableBooks + " books are available");
            }
        }
    }

    public void displayBorrowedBooks(){
        int totalBorrowdBooks=0;
        for(Patron patron: patronList){
            List<Book> bookIssuesByPatron = patron.getBooks();
            totalBorrowdBooks+=bookIssuesByPatron.size();
            for(Book book: bookIssuesByPatron){
                System.out.println(book.getTitle() + " ,publication year "+
                book.getPublicationYear()+ " is borrowed by "+patron.getName()
                +"("+patron.getId()+")");
            }
        }
        System.out.println("Total borrowed books "+totalBorrowdBooks);
    }
    public void getPatronBorrowingHistory(Patron patron){
        if(patronBorrowedHistory.containsKey(patron)){
            List<Book> booksIssuedByPatronInPast = patronBorrowedHistory.get(patron);
            System.out.println(patron.getName()+"("+patron.getId()+") have borrowed below books in past");
            int count=0;
            for(Book book: booksIssuedByPatronInPast){
                System.out.println(++count +". "+ book.getTitle());
            }
        }
    }

    public void notifyPatron(String message, Patron patron) {
        System.out.println(message);
        notification.sendNotification(message, patron);
    }
}
