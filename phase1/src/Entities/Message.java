package Entities;
import Java.util.Date;
import java.util.String;

public class Message {
    private int sender;
    private int recipient;
    private String context;
    private Date time;

    public Message(int sender, int recipient, String context){
        this.sender = sender;
        this.recipient = recipient;
        this.context = context;
        this.time = Date();
    }

    public int getSender() {
        return sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public String getContext() {
        return context;
    }

    public Time getTime() {
        return time;
    }
}
