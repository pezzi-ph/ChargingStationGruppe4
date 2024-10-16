package businessobjects;

public class Notification {

    private String message;
    private String timestamp;

    public Notification(String message) {
        this.message = message;
        // You can generate a timestamp here
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
