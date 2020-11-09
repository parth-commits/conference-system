package Entities;
import java.util.ArrayList;

public class Chat {
    private int id1;
    private int id2;
    private ArrayList<Message> messages;

    public Chat(int id1, int id2){
//        if (id1 > id2){
//            this.id1 = id2;
//            this.id2 = id1;
//        }
//        else{
//            this.id1 = id1;
//            this.id2 = id2;
//        }
        this.id1 = id1;
        this.id2 = id2;
        messages = new ArrayList<>();
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessages(int sender, int recipient, String context){
        Message newMessage = new Message(sender, recipient, context);
        messages.add(newMessage);
    }

    //Message search???
}
