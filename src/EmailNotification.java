public class EmailNotification implements Notification{

    @Override
    public void sendNotification(String message, Patron patron) {
        String email= patron.getEmail();
        System.out.println("Notfication sent on email id: "+ email);
    }
}
