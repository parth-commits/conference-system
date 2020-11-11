package Entities;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Message {

    //sender of the message, make sure this is the name of the person, not its id!!
    private String sender;

    //receipent of the message, make sure this is the name of the person, not its id!!
    private String recipient;

    //the actual text of the message
    private String context;

    //the date that the message was sent
    private Date time;

    //date formatter for formatting the date
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Message(String sender, String recipient, String context){
        this.sender = sender;
        this.recipient = recipient;
        this.context = context;
        this.time = new Date();
    }

    //gets the sender of the message
    public String getSender() {
        return sender;
    }

    //gets the recipeint of the message
    public String getRecipient() {
        return recipient;
    }

    //gets the actual text of the message
    public String getContext() {
        return context;
    }

    //gets the time of the message
    public Date getTime() {
        return time;
    }

    //converts the message to a string form
    @Override
    public String toString() {
        return "[" + formatter.format(getTime()) + "] " + sender + ": " + context;
    }
}
