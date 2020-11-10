package Entities;
import java.util.Date;


public class Message {
    private String sender;
    private String recipient;
    private String context;
    private Date time;

    public Message(String sender, String recipient, String context){
        this.sender = sender;
        this.recipient = recipient;
        this.context = context;
        this.time = new Date();
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContext() {
        return context;
    }

    public Date getTime() {
        return time;
    }
}
