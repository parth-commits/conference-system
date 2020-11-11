package Entities;
import java.util.ArrayList;
public class Chat {
    private String id1;
    private String id2;
    private ArrayList<Message> messages;

    public Chat(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;
        messages = new ArrayList<>();
    }

    public String getId1() {
        return id1;
    }

    public String getId2() {
        return id2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessages(String sender, String recipient, String context){
        Message newMessage = new Message(sender, recipient, context);
        messages.add(newMessage);
    }

    //Message search???
}
