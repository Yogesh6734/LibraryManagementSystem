public class WhatsAppNotification implements  Notification{

    @Override
    public void sendNotification(String message, Patron patron) {
        long mobileNumber= patron.getMobileNumber();
        System.out.println("Notification sent on whatsapp: "+ mobileNumber);
    }
}
